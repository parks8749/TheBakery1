package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


public class BackBottom {

    private final CRServo backBottom;

    public BackBottom(CRServo backRoller) {
        this.backBottom = backRoller;
    }

    public void init() {
        backBottom.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void update(boolean aPressed) {
        if (aPressed) {
            backBottom.setPower(-1.0);
        } else {
            backBottom.setPower(0.0);
        }
    }
}
