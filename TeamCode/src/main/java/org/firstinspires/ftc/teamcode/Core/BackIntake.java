package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;

public class BackIntake {


    private final CRServo backIntake;

    public BackIntake(CRServo backIntake) {
        this.backIntake = backIntake;
    }

    public void init() {
        backIntake.setDirection(CRServo.Direction.FORWARD);
    }

    public void update(float rightStickY) {
        if (rightStickY > 0) {
            backIntake.setPower(-1.0);
        } else if (rightStickY < 0) {
            backIntake.setPower(1.0);
        } else {
            backIntake.setPower(0.0);
        }
    }
}
