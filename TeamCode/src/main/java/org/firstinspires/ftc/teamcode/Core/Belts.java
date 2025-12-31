package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Belts {

    private final CRServo leftBelt;
    private final CRServo rightBelt;
<<<<<<< HEAD
    private static final float DEADZONE = 0.08f;
    private static final double POWER = 1.0;
    private boolean toggled = false;
    private boolean lastBPressed = false;
=======
    private static final double POWER = 1.0;

    // 0 = off, 1 = forward, 2 = reverse
    private int mode = 0;
>>>>>>> local-save

    public Belts(CRServo leftBelt, CRServo rightBelt) {
        this.leftBelt = leftBelt;
        this.rightBelt = rightBelt;
    }

    public void init() {
        leftBelt.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBelt.setDirection(DcMotorSimple.Direction.FORWARD);
<<<<<<< HEAD
    }

    public void update(boolean xPressed) {
        if (xPressed && !lastBPressed) {
            toggled = !toggled;
        }
        lastBPressed = xPressed;
        rightBelt.setPower(toggled ? POWER : 0.0);
    }

    public void stop() {
        toggled = false;
        rightBelt.setPower(0.0);
    }


//        if (Math.abs(rightStickY) < DEADZONE) {
//            rightBelt.setPower(0.0);
//            leftBelt.setPower(0.0);
//            return;
//        }
//        rightBelt.setPower(rightStickY > 0 ? POWER : -POWER);
//        leftBelt.setPower(rightStickY > 0 ? -POWER : POWER);



=======
        applyMode();
    }

    /**
     * Control belts using the right stick value (already deadzoned by caller).
     * stickY > 0 => forward
     * stickY < 0 => reverse
     * stickY == 0 => off
     */
    public void update(float rightStickY) {
        if (rightStickY > 0.0f) {
            mode = 1;
        } else if (rightStickY < 0.0f) {
            mode = 2;
        } else {
            mode = 0;
        }
        applyMode();
    }

    private void applyMode() {
        switch (mode) {
            case 0: // off
                rightBelt.setPower(0.0);
                leftBelt.setPower(0.0);
                break;
            case 1: // forward
                rightBelt.setPower(POWER);
                leftBelt.setPower(-POWER); // inverted so both move same physical direction
                break;
            case 2: // reverse
                rightBelt.setPower(-POWER);
                leftBelt.setPower(POWER);
                break;
        }
    }

    public void stop() {
        mode = 0;
        applyMode();
    }

    public int getMode() {
        return mode;
    }

    public boolean isRunning() {
        return mode != 0;
    }

    /**
     * Directly force the belts into a mode (0/1/2) from code.
     * Useful for global overrides/automations.
     */
    public void setMode(int newMode) {
        this.mode = newMode;
        applyMode();
    }
>>>>>>> local-save
}
