package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class RightBelt {

    private final CRServo rightBelt;

    public RightBelt(CRServo rightBelt) {
        this.rightBelt = rightBelt;
    }

    public void init() {
        rightBelt.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void update(boolean yPressed) {
        if (yPressed) {
            rightBelt.setPower(1.0);
        } else {
            rightBelt.setPower(0.0);
        }
    }
}
