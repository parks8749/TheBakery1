package org.firstinspires.ftc.teamcode.Core;//package org.firstinspires.ftc.teamcode.Core;
//
//import com.qualcomm.robotcore.hardware.Servo;
//
//public class Claw {
//    private final Servo claw;
//    public static final double CLAW_OPEN_POSITION   = 0.15;
//    public static final double CLAW_CLOSED_POSITION = 0.45;
//
//    private boolean isClawOpen = false;
//    private boolean xPreviouslyPressed = false;
//
//    public Claw(Servo claw) {
//        this.claw = claw;
//    }
//
//    public void init() {
//        claw.setPosition(CLAW_CLOSED_POSITION);
//    }
//
//    public void update(boolean xPressed) {
//        if (xPressed && !xPreviouslyPressed) {
//            if (isClawOpen) {
//                claw.setPosition(CLAW_CLOSED_POSITION);
//            } else {
//                claw.setPosition(CLAW_OPEN_POSITION);
//            }
//            isClawOpen = !isClawOpen;
//        }
//        xPreviouslyPressed = xPressed;
//    }
//
//    public boolean isOpen() {
//        return isClawOpen;
//    }
//}
