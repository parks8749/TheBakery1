//package org.firstinspires.ftc.teamcode.Core;
//
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//
//public class TopFront {
//
//    private final CRServo topFront;
//    private static final double POWER = 1.0;
//    private boolean toggled = false;
//    private boolean lastYPressed = false;
//
//    public TopFront(CRServo topFront) {
//        this.topFront = topFront;
//    }
//
//    public void init() {
//        topFront.setDirection(DcMotorSimple.Direction.REVERSE);
//        topFront.setPower(0.0);
//    }
//
//    public void update(boolean yPressed) {
//        if (yPressed && !lastYPressed) {
//            toggled = !toggled;
//        }
//        lastYPressed = yPressed;
//        topFront.setPower(toggled ? POWER : 0.0);
//    }
//
//    public void stop() {
//        toggled = false;
//        topFront.setPower(0.0);
//    }
//}




package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class TopFront {

    private final CRServo topFront;

    public TopFront(CRServo topFront) {
        this.topFront = topFront;
    }

    public void init() {
        // keep existing orientation; change if your wiring requires different direction
        topFront.setDirection(DcMotorSimple.Direction.REVERSE);
        topFront.setPower(0.0);
    }

    /**
     * Mirrors the belts mode:
     * mode == 1 -> power = +1.0 (forward)
     * mode == 2 -> power = -1.0 (reverse)
     * mode == 0 -> power = 0.0 (off)
     *
     * This accepts the same belts.getMode() integer (0/1/2) that belts provides.
     */
    public void update(int beltsMode) {
        if (beltsMode == 1) {
            topFront.setPower(1.0);
        } else if (beltsMode == 2) {
            topFront.setPower(-1.0);
        } else {
            topFront.setPower(0.0);
        }
    }

    public void stop() {
        topFront.setPower(0.0);
    }
}
