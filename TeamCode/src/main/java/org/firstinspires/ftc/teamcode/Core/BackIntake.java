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

    /**
     * Controls:
     * - xPressed (original override) -> 0.7
     * - overrideActive (gamepad2.y) -> 0.7 (activate)
     * - leftStickY (if outside deadzone) -> manual mapping (mirror mapping used previously)
     */
    public void update(float leftStickY, boolean xPressed, boolean overrideActive) {
        if (xPressed) {
            backIntake.setPower(0.7);
            return;
        }
        if (overrideActive)
        {
            backIntake.setPower(-0.7);
            return;
        }

        if (Math.abs(leftStickY) < DEADZONE) {
            backIntake.setPower(0.0);
            return;
        }
        backIntake.setPower(leftStickY > 0 ? -POWER : POWER);
    }

    public void setPower(double power) {
        backIntake.setPower(power);
    }

    public void stop() {
        backIntake.setPower(0.0);
    }
}
