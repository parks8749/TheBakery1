package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;

public class LeftBelt {

    private final CRServo leftBelt;

    public LeftBelt(CRServo leftBelt) {
        this.leftBelt = leftBelt;
    }

    public void init() {
        leftBelt.setDirection(CRServo.Direction.FORWARD);
    }

    public void update(boolean xPressed) {
        if (xPressed) {
            leftBelt.setPower(-1.0);
        } else {
            leftBelt.setPower(0.0);
        }
    }
}
