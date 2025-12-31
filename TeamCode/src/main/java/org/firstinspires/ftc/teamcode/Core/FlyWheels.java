package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class FlyWheels {

    private final DcMotor leftFlyWheel;
    private final DcMotor rightFlyWheel;
    private static final double POWER = 1.0;

    private enum State { STOPPED, FORWARD, REVERSE }
    private State state = State.STOPPED;

    private boolean prevLeftPressed = false;
    private boolean prevRightPressed = false;

    public FlyWheels(DcMotor leftFlyWheel, DcMotor rightFlyWheel) {
        this.leftFlyWheel = leftFlyWheel;
        this.rightFlyWheel = rightFlyWheel;
    }

    public void init() {
        if (leftFlyWheel == null || rightFlyWheel == null) {
            throw new IllegalStateException("Flywheel motors not initialized (null).");
        }
        leftFlyWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFlyWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFlyWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        rightFlyWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFlyWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFlyWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        applyPower();
    }

    /**
     * @param leftPressed bumper toggles forward (existing)
     * @param rightPressed bumper toggles reverse (existing)
     * @param xPressed existing override -> reverse at -0.7
     * @param overrideActive new global activation (gamepad2.y) -> run forward at full POWER
     */
    public void update(boolean leftPressed, boolean rightPressed, boolean xPressed, boolean overrideActive) {
        boolean leftRising = leftPressed && !prevLeftPressed;
        boolean rightRising = rightPressed && !prevRightPressed;

        prevLeftPressed = leftPressed;
        prevRightPressed = rightPressed;

        if (xPressed) {
            // existing override: reverse moderate power
            leftFlyWheel.setPower(-0.7);
            rightFlyWheel.setPower(-0.7);
            return;
        }

        if (overrideActive) {
            // global activation -> run both flywheels forward at full power
            leftFlyWheel.setPower(POWER);
            rightFlyWheel.setPower(POWER);
            return;
        }

        if (leftRising && rightRising) {
            state = State.STOPPED;
        } else if (leftRising) {
            state = (state == State.FORWARD) ? State.STOPPED : State.FORWARD;
        } else if (rightRising) {
            state = (state == State.REVERSE) ? State.STOPPED : State.REVERSE;
        }

        applyPower();
    }

    private void applyPower() {
        double p;
        switch (state) {
            case FORWARD: p = POWER; break;
            case REVERSE: p = -POWER; break;
            default: p = 0.0; break;
        }
        leftFlyWheel.setPower(p);
        rightFlyWheel.setPower(p);
    }

    public void stop() {
        state = State.STOPPED;
        applyPower();
    }

    public void setReverse() { state = State.REVERSE; applyPower(); }
    public void setForward() { state = State.FORWARD; applyPower(); }
}
