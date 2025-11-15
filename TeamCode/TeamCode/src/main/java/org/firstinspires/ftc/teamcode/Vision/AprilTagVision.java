package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class AprilTagVision {

    private VisionPortal visionPortal;
    private AprilTagProcessor aprilTagProcessor;
    private WebcamName webcam;
    private Thread pollThread;
    private volatile boolean polling = false;
    private long pollIntervalMs = 50L;
    private volatile AprilTagDetection lastSeenTag = null;
    private volatile long lastSeenTimeMs = 0L;
    private volatile List<AprilTagDetection> lastDetectionsSnapshot = Collections.emptyList();
    private final Set<Integer> idsOfInterest = new HashSet<>();
    private long staleThresholdMs = 2000L;

    public AprilTagVision() { }


    public void init(HardwareMap hardwareMap, String webcamConfigName) {
        webcam = hardwareMap.get(WebcamName.class, webcamConfigName);
        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        visionPortal = VisionPortal.easyCreateWithDefaults(webcam, aprilTagProcessor);

        startPolling();
    }
    public synchronized void startPolling() {
        if (polling) return;
        polling = true;
        pollThread = new Thread(() -> {
            while (polling) {
                try {
                    List<AprilTagDetection> detections = new ArrayList<>();
                    if (aprilTagProcessor != null) {
                        List<AprilTagDetection> raw = aprilTagProcessor.getDetections();
                        if (raw != null) detections = new ArrayList<>(raw);
                    }
                    lastDetectionsSnapshot = Collections.unmodifiableList(detections);

                    AprilTagDetection found = null;
                    if (!detections.isEmpty()) {
                        if (idsOfInterest.isEmpty()) {
                            found = detections.get(0);
                        } else {
                            for (AprilTagDetection d : detections) {
                                if (idsOfInterest.contains(d.id)) {
                                    found = d;
                                    break;
                                }
                            }
                        }
                    }

                    if (found != null) {
                        lastSeenTag = found;
                        lastSeenTimeMs = System.currentTimeMillis();
                    }

                    Thread.sleep(pollIntervalMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AprilTagVision-PollThread");
        pollThread.setDaemon(true);
        pollThread.start();
    }
    public synchronized void stop() {
        polling = false;
        if (pollThread != null) {
            try {
                pollThread.interrupt();
                pollThread.join(200);
            } catch (InterruptedException ignored) { Thread.currentThread().interrupt(); }
            pollThread = null;
        }
        try {
            if (visionPortal != null) visionPortal.stopStreaming();
        } catch (Exception ignored) { }
        try {
            if (visionPortal != null) visionPortal.close();
        } catch (Exception ignored) { }

        lastDetectionsSnapshot = Collections.emptyList();
        lastSeenTag = null;
        lastSeenTimeMs = 0L;
    }

    public List<AprilTagDetection> getDetections() {
        if (aprilTagProcessor == null) return Collections.emptyList();
        // prefer the cached snapshot (faster & stable view)
        List<AprilTagDetection> snap = lastDetectionsSnapshot;
        return (snap != null) ? snap : Collections.emptyList();
    }

    public AprilTagDetection getLastSeenTag() {
        return lastSeenTag;
    }

    public long getLastSeenAgeMs() {
        if (lastSeenTimeMs == 0L) return Long.MAX_VALUE;
        return System.currentTimeMillis() - lastSeenTimeMs;
    }

    public int getLastSeenId() {
        AprilTagDetection t = lastSeenTag;
        return (t != null) ? t.id : -1;
    }
    public int getFirstTagIdOfInterest() {
        List<AprilTagDetection> list = getDetections();
        if (list.isEmpty()) return -1;
        if (idsOfInterest.isEmpty()) return list.get(0).id;
        for (AprilTagDetection d : list) {
            if (idsOfInterest.contains(d.id)) return d.id;
        }
        return -1;
    }

    public void setIdsOfInterest(Collection<Integer> ids) {
        idsOfInterest.clear();
        if (ids != null) idsOfInterest.addAll(ids);
    }

    public void setPollIntervalMs(long ms) {
        if (ms < 10L) ms = 10L;
        pollIntervalMs = ms;
    }

    public void setStaleThresholdMs(long ms) {
        staleThresholdMs = ms;
    }

    public boolean hasRecentDetection() {
        return getLastSeenAgeMs() < staleThresholdMs;
    }

    public void clearLastSeen() {
        lastSeenTag = null;
        lastSeenTimeMs = 0L;
    }
}
