package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class BackBottom {

    private final CRServo backBottom;
    private static final double POWER = 1.0;

    private boolean directionForward = true;
    private boolean running = false;
    private boolean lastAPressed = false;

    public BackBottom(CRServo backRoller) {
        this.backBottom = backRoller;
    }

    public void init() {
        backBottom.setDirection(DcMotorSimple.Direction.REVERSE);
        backBottom.setPower(0);
    }

    public void update(boolean aPressed) {


        if (aPressed && !lastAPressed) {

            if (!running) {

                running = true;
            } else {

                directionForward = !directionForward;
            }
        }

        lastAPressed = aPressed;


        if (running) {
            backBottom.setPower(directionForward ? POWER : -POWER);
        } else {
            backBottom.setPower(0);
        }
    }

    public void stop() {
        running = false;
        backBottom.setPower(0.0);
    }
}











/*package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


public class BackBottom {

    private final CRServo backBottom;
    private static final double POWER = 1.0;
    private boolean toggled = false;
    private boolean lastAPressed = false;

    public BackBottom(CRServo backRoller) {
        this.backBottom = backRoller;
    }

    public void init() {
        backBottom.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void update(boolean aPressed) {
        if (aPressed && !lastAPressed) {
            toggled = !toggled;
        }
        lastAPressed = aPressed;

        // set power based on toggled state
        backBottom.setPower(toggled ? POWER : 0.0);
//        double p = aPressed ? POWER : 0.0;
//        backBottom.setPower(p);
//        if (aPressed) {
//            backBottom.setPower(-1.0);
//        } else {
//            backBottom.setPower(0.0);
//        }
    }
    public void stop() {
        toggled = false;
        backBottom.setPower(0.0);
    }
}
*/
