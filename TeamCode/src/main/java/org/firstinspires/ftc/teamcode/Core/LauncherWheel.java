package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class LauncherWheel {

    private final CRServo launcherWheel;
    private static final double POWER = 1.0;
    private boolean toggled = false;
    private boolean lastBPressed = false;

    public LauncherWheel(CRServo launcherWheel) {
        this.launcherWheel = launcherWheel;
    }

    public void init() {
        launcherWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        launcherWheel.setPower(0.0);
    }

<<<<<<< HEAD
    public void update(boolean bPressed) {
        if (bPressed && !lastBPressed) {
            toggled = !toggled;
        }
        lastBPressed = bPressed;
        launcherWheel.setPower(toggled ? POWER : 0.0);
    }

=======
    /**
     * @param bPressed toggles full POWER on/off (existing behavior)
     * @param xPressed existing override (runs reversed at -0.7)
     * @param overrideActive new global activation (gamepad2.y) -> run at full POWER
     */
    public void update(boolean bPressed, boolean xPressed, boolean overrideActive) {
        boolean rising = bPressed && !lastBPressed;
        lastBPressed = bPressed;

        if (xPressed) {
            // existing special override: run at reverse moderate power
            launcherWheel.setPower(-0.7);
            return;
        }

        if (overrideActive) {
            // global activation requested -> run launcher forward at full power
            launcherWheel.setPower(POWER);
            return;
        }

        if (rising) {
            toggled = !toggled;
        }
        launcherWheel.setPower(toggled ? POWER : 0.0);
    }

    public void setPowerDirect(double power) {
        launcherWheel.setPower(power);
    }

>>>>>>> local-save
    public void stop() {
        toggled = false;
        launcherWheel.setPower(0.0);
    }
}
