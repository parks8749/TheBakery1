package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class BackIntake {
    private final CRServo backIntake;
    private static final float DEADZONE = 0.08f;
    private static final double POWER = 1.0;

    public BackIntake(CRServo backIntake) {
        this.backIntake = backIntake;
    }

    public void init() {
        backIntake.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void update(float rightStickY) {
        if (Math.abs(rightStickY) < DEADZONE) {
            backIntake.setPower(0.0);
            return;
        }
        backIntake.setPower(rightStickY > 0 ? -POWER : POWER);
    }
}
