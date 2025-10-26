package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class RightFlyWheel {
    private final DcMotor rightFlyWheel;
    private static final double POWER = 1.0;

    public RightFlyWheel(DcMotor rightFlyWheel) {
        this.rightFlyWheel = rightFlyWheel;
    }

    public void init() {
        rightFlyWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFlyWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFlyWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void update(boolean rightBumperPressed) {
        rightFlyWheel.setPower(rightBumperPressed ? POWER : 0.0);
    }
}
