package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;
import java.util.List;

public class AprilTagVision {
    private VisionPortal visionPortal;
    private AprilTagProcessor aprilTagProcessor;
    private WebcamName webcam;

    public void init(HardwareMap hardwareMap, String webcamConfigName) {
        webcam = hardwareMap.get(WebcamName.class, webcamConfigName);

        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();

        visionPortal = VisionPortal.easyCreateWithDefaults(webcam, aprilTagProcessor);
    }

    public List<AprilTagDetection> getDetections() {
        if (aprilTagProcessor == null) return new ArrayList<>();
        List<AprilTagDetection> list = aprilTagProcessor.getDetections();
        return (list != null) ? list : new ArrayList<>();
    }

    public void stop() {
        try {
            if (visionPortal != null) visionPortal.stopStreaming();
        } catch (Exception ignored) { }
        try {
            if (visionPortal != null) visionPortal.close();
        } catch (Exception ignored) { }
    }
}
