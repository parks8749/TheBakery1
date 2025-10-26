package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


public class FrontIntake {
    
    private final CRServo frontIntake;

    public FrontIntake(CRServo frontIntake) {
        this.frontIntake = frontIntake;
    }

    public void init() {
        frontIntake.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void update(float leftStickY) {
        if (leftStickY > 0) {
            frontIntake.setPower(-1.0);
        } else if (leftStickY < 0) {
            frontIntake.setPower(1.0);
        } else {
            frontIntake.setPower(0.0);
        }
    }
}
