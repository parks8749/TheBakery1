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

    public void update(boolean bPressed, boolean xPressed) {
        boolean rising = bPressed && !lastBPressed;
        lastBPressed = bPressed;

        if (xPressed) {
            // might need to change power
            launcherWheel.setPower(-0.7);
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

    public void stop() {
        toggled = false;
        launcherWheel.setPower(0.0);
    }
}
