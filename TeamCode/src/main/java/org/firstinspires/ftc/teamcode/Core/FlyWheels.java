package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class FlyWheels {

    private final DcMotor leftFlyWheel;
    private final DcMotor rightFlyWheel;
    private static final double POWER = 1.0;

    public FlyWheels(DcMotor leftFlyWheel, DcMotor rightFlyWheel) {
        this.leftFlyWheel = leftFlyWheel;
        this.rightFlyWheel = rightFlyWheel;
    }

    public void init() {
        if (leftFlyWheel == null || rightFlyWheel == null) {
            throw new IllegalStateException("Flywheel motors not initialized (null).");
        }
        leftFlyWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        leftFlyWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFlyWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        rightFlyWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFlyWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFlyWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void update(boolean leftBumperPressed) {
        double p = leftBumperPressed ? POWER : 0.0;
        leftFlyWheel.setPower(p);
        rightFlyWheel.setPower(p);
    }
}
