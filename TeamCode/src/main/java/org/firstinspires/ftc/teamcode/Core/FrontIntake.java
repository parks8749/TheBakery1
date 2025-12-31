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

    /**
     * Mirrors belts mode:
     * mode == 1 -> power = +1.0
     * mode == 2 -> power = -1.0
     * mode == 0 -> power = 0.0
     */
    public void update(int beltsMode) {
        if (beltsMode == 1) {
            frontIntake.setPower(1.0);
        } else if (beltsMode == 2) {
            frontIntake.setPower(-1.0);
        } else {
            frontIntake.setPower(0.0);
        }
    }

    public void stop() {
        frontIntake.setPower(0.0);
    }
}
