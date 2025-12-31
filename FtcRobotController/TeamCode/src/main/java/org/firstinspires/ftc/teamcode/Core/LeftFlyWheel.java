//package org.firstinspires.ftc.teamcode.Core;
//
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//
//public class LeftFlyWheel {
//    private final DcMotor leftFlyWheel;
//    private static final double POWER = 1.0;
//
//    public LeftFlyWheel(DcMotor leftFlyWheel) {
//        this.leftFlyWheel = leftFlyWheel;
//    }
//
//    public void init() {
//        leftFlyWheel.setDirection(DcMotorSimple.Direction.FORWARD);
//        leftFlyWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        leftFlyWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//    }
//
//    public void update(boolean leftBumperPressed) {
//        leftFlyWheel.setPower(leftBumperPressed ? POWER : 0.0);
//    }
//}
