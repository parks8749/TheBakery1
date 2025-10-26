package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;


public class LauncherWheel {

    private final CRServo launcherWheel;

    public LauncherWheel(CRServo launcherWheel) {
        this.launcherWheel = launcherWheel;
    }

    public void init() {
        launcherWheel.setDirection(CRServo.Direction.FORWARD);
    }

    public void update(boolean bPressed) {
        if (bPressed) {
            launcherWheel.setPower(1.0);
        } else {
            launcherWheel.setPower(0.0);
        }
    }
}
