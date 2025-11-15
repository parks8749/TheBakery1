package org.firstinspires.ftc.teamcode.Core;//package org.firstinspires.ftc.teamcode.Core;
//
//import com.qualcomm.robotcore.hardware.Servo;
//
//public class Scoop {
//    private final Servo scoop;
//    public static final double SCOOP_DOWN_POSITION = 1.0;
//    public static final double SCOOP_UP_POSITION   = 0.4;
//
//    private boolean isScoopUp = false;
//    private boolean aPreviouslyPressed = false;
//
//    public Scoop(Servo scoop) {
//        this.scoop = scoop;
//    }
//
//    public void init() {
//        scoop.setDirection(Servo.Direction.FORWARD);
//        scoop.setPosition(SCOOP_DOWN_POSITION);
//    }
//
//    public void update(boolean aPressed) {
//        if (aPressed && !aPreviouslyPressed) {
//            if (isScoopUp) {
//                scoop.setPosition(SCOOP_DOWN_POSITION);
//            } else {
//                scoop.setPosition(SCOOP_UP_POSITION);
//            }
//            isScoopUp = !isScoopUp;
//        }
//        aPreviouslyPressed = aPressed;
//    }
//
//    public double getPosition() {
//        return scoop.getPosition();
//    }
//}
