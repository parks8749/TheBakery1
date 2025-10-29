package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Belts {

    private final CRServo leftBelt;
    private final CRServo rightBelt;
    private static final float DEADZONE = 0.08f;
    private static final double POWER = 1.0;

    public Belts(CRServo leftBelt, CRServo rightBelt) {
        this.leftBelt = leftBelt;
        this.rightBelt = rightBelt;
    }

    public void init() {
        leftBelt.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBelt.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void update(float rightStickY) {
        if (Math.abs(rightStickY) < DEADZONE) {
            rightBelt.setPower(0.0);
            leftBelt.setPower(0.0);
            return;
        }
        rightBelt.setPower(rightStickY > 0 ? POWER : -POWER);
        leftBelt.setPower(rightStickY > 0 ? -POWER : POWER);

    }


}
