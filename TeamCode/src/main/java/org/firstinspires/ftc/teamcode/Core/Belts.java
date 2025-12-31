package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Belts {

    private final CRServo leftBelt;
    private final CRServo rightBelt;
    private static final double POWER = 1.0;

    // 0 = off, 1 = forward, 2 = reverse
    private int mode = 0;

    public Belts(CRServo leftBelt, CRServo rightBelt) {
        this.leftBelt = leftBelt;
        this.rightBelt = rightBelt;
    }

    public void init() {
        leftBelt.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBelt.setDirection(DcMotorSimple.Direction.FORWARD);
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
        rightBelt.setPower(0.0);
        leftBelt.setPower(0.0);
    }

    public int getMode() {
        return mode;
    }

    public boolean isRunning() {
        return mode != 0;
    }
}
