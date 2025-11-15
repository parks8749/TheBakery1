package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class TopFront {

    private final CRServo topFront;
    private static final double POWER = 1.0;
    private boolean toggled = false;
    private boolean lastXPressed = false;

    public TopFront(CRServo topFront) {
        this.topFront = topFront;
    }

    public void init() {
       topFront.setDirection(DcMotorSimple.Direction.FORWARD);
        topFront.setPower(0.0);
    }

    public void update(boolean xPressed) {
        if (xPressed && !lastXPressed) {
            toggled = !toggled;
        }
        lastXPressed = xPressed;
        topFront.setPower(toggled ? POWER : 0.0);
    }

    public void stop() {
        toggled = false;
        topFront.setPower(0.0);
    }
}
