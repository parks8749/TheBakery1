package org.firstinspires.ftc.teamcode.Core;//package org.firstinspires.ftc.teamcode.Core;
//
//import com.qualcomm.robotcore.hardware.Servo;
//
//public class Arm {
//    private final Servo arm;
//    public static final double ARM_DOWN_POSITION = 1.0;
//    public static final double ARM_UP_POSITION   = 0.5;
//
//    private boolean isArmUp = false;
//    private boolean bPreviouslyPressed = false;
//
//    public Arm(Servo arm) {
//        this.arm = arm;
//    }
//
//    public void init() {
//        arm.setDirection(Servo.Direction.FORWARD);
//        arm.setPosition(ARM_DOWN_POSITION);
//    }
//
//    public void update(boolean bPressed) {
//        if (bPressed && !bPreviouslyPressed) {
//            if (isArmUp) {
//                arm.setPosition(ARM_DOWN_POSITION);
//            } else {
//                arm.setPosition(ARM_UP_POSITION);
//            }
//            isArmUp = !isArmUp;
//        }
//        bPreviouslyPressed = bPressed;
//    }
//
//    public double getPosition() {
//        return arm.getPosition();
//    }
//}
