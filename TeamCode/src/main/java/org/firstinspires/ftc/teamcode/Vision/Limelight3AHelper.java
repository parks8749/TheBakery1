package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.List;

/**
 * Small helper wrapper for Limelight3A that provides:
 * - init(hardwareMap)
 * - a simple "get best seen fiducial id" convenience method
 *
 * Paste as-is into TeamCode/src/main/java/org/firstinspires/ftc/teamcode/Vision/
 */
public class Limelight3AHelper {
    private Limelight3A limelight;
    private LLResult lastResult = null;

    /** Name used in Robot Controller configuration. Keep this name in the RC config. */
    public static final String DEFAULT_NAME = "limelight";

    public void init(HardwareMap hardwareMap) {
        limelight = hardwareMap.get(Limelight3A.class, DEFAULT_NAME);
        limelight.setPollRateHz(50); // adjust if you add heavy processing
        limelight.start();
    }

    /** Call regularly to refresh the cached result */
    public void update() {
        if (limelight == null) return;
        lastResult = limelight.getLatestResult();
    }


    /** Returns true if the last polled result contains a valid target. */
    public boolean hasValidTarget() {
        return lastResult != null && lastResult.isValid();
    }
    private Integer extractIdFromFiducialObject(Object fiducial) {
        if (fiducial == null) return null;

        String[] methodCandidates = {
                "getFiducialId", "getFiducialID", "getId", "getTagId", "getTagID", "getIdentifier"
        };
        String[] fieldCandidates = {
                "id", "fiducialId", "fiducialID", "tagId", "identifier"
        };

        // try method candidates
        for (String mname : methodCandidates) {
            try {
                Method m = fiducial.getClass().getMethod(mname);
                Object val = m.invoke(fiducial);
                Integer parsed = parseNumberLikeToInt(val);
                if (parsed != null) return parsed;
            } catch (NoSuchMethodException ignored) {
                // method not present — that's expected for some SDKs
            } catch (Exception ignored) {
                // method present but invocation failed, continue trying others
            }
        }

        // try field candidates
        for (String fname : fieldCandidates) {
            try {
                Field f = fiducial.getClass().getField(fname);
                Object val = f.get(fiducial);
                Integer parsed = parseNumberLikeToInt(val);
                if (parsed != null) return parsed;
            } catch (NoSuchFieldException ignored) {
                // field not present
            } catch (Exception ignored) {
                // field present but access failed
            }
        }

        // last resort: inspect declared fields (helpful for unknown names) and attempt to parse any numeric-like field
        try {
            Field[] all = fiducial.getClass().getDeclaredFields();
            for (Field ff : all) {
                try {
                    ff.setAccessible(true);
                    Object val = ff.get(fiducial);
                    Integer parsed = parseNumberLikeToInt(val);
                    if (parsed != null) return parsed;
                } catch (Exception ignored) { }
            }
        } catch (Exception ignored) { }

        // nothing found
        return null;
    }

    /** Convert a common numeric type or numeric String to Integer, else return null. */
    private Integer parseNumberLikeToInt(Object val) {
        if (val == null) return null;
        if (val instanceof Integer) return (Integer) val;
        if (val instanceof Short) return ((Short) val).intValue();
        if (val instanceof Long) return (int) ((Long) val).longValue();
        if (val instanceof Byte) return ((Byte) val).intValue();
        if (val instanceof Double) return (int) Math.round((Double) val);
        if (val instanceof Float) return (int) Math.round((Float) val);
        if (val instanceof String) {
            try {
                return Integer.parseInt(((String) val).trim());
            } catch (NumberFormatException ignored) { }
            try {
                double d = Double.parseDouble(((String) val).trim());
                return (int) Math.round(d);
            } catch (NumberFormatException ignored) { }
        }
        return null;
    }

    /**
     * Return the fiducial id of the "best" fiducial, or -1 if none seen.
     * Strategy used here: return the first fiducial in the list (you can change to largest area).
     */
    public int getBestFiducialId() {
        if (!hasValidTarget()) return -1;

        // Use a wildcard list to avoid compile-time dependency on specific FiducialResult methods
        List<?> fiducials = (List<?>) lastResult.getFiducialResults();
        if (fiducials == null || fiducials.isEmpty()) return -1;

        // pick the first fiducial by default; you could iterate to pick largest area / highest confidence
        Object f = fiducials.get(0);

        Integer id = extractIdFromFiducialObject(f);
        return id == null ? -1 : id;
    }

    /** Return the raw LLResult if you need other data (pose, staleness, etc). */
    public LLResult getLastResult() {
        return lastResult;
    }

}
