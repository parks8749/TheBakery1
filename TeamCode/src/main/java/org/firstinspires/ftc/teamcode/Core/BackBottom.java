package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class BackBottom {

    private final CRServo backBottom;
    private static final double POWER = 1.0;
    private static final float DEADZONE = 0.08f;

    public BackBottom(CRServo backRoller) {
        this.backBottom = backRoller;
    }

    public void init() {
        // keep this direction; change if your wiring requires it
        backBottom.setDirection(DcMotorSimple.Direction.REVERSE);
        backBottom.setPower(0.0);
    }

    /**
     * Priority rules:
     * 1) If leftStickY is being used (outside DEADZONE) -> mirror BackIntake behavior.
     * 2) Otherwise, if overrideActive (gamepad2.y) -> run a default "forward" mapping.
     * 3) Otherwise -> 0.0
     *
     * @param leftStickY raw left stick Y (not deadzoned by caller; we check DEADZONE here)
     * @param overrideActive true when global activation override (gamepad2.y) is pressed
     */
    public void update(float leftStickY, boolean overrideActive) {
        // 1) left-stick overrides: mirror BackIntake mapping
        if (Math.abs(leftStickY) >= DEADZONE) {
            backBottom.setPower(leftStickY > 0 ? POWER : -POWER);
            return;
        }

        // 2) override pressed -> run "forward" mapping (matches previous belts->case 1 mapping)
        if (overrideActive) {
            backBottom.setPower(-POWER); // use same forward mapping as previous belts-mode->case1 behavior
            return;
        }

        // 3) neutral
        backBottom.setPower(0.0);
    }

    public void setPower(double power) {
        backBottom.setPower(power);
    }

    public void stop() {
        backBottom.setPower(0.0);
    }
}
