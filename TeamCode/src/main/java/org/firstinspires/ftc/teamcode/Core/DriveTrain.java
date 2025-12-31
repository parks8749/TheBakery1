package org.firstinspires.ftc.teamcode.Core;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Basic drivetrain helper. Supports direct open-loop driving (teleop)
 * and helper methods for encoder-based control (autonomous).
 */
public class DriveTrain
{
    public DcMotor MotorfL, MotorbL;
    public DcMotor MotorfR, MotorbR;

    // Direction modifiers (1 or -1) in case a motor needs reversing for power calls
    // <-- LEFT MOTORS inverted to correct physical wiring (front & back left were spinning opposite)
    protected final int MOTOR_fL_MODIFIER = -1;
    protected final int MOTOR_bL_MODIFIER = -1;
    protected final int MOTOR_fR_MODIFIER = 1;
    protected final int MOTOR_bR_MODIFIER = 1;

    protected boolean encoders_initialized = false;

    public DriveTrain(HardwareMap map, String FL, String BL, String FR, String BR)
    {
        MotorfL = map.get(DcMotor.class, FL);
        MotorbL = map.get(DcMotor.class, BL);
        MotorfR = map.get(DcMotor.class, FR);
        MotorbR = map.get(DcMotor.class, BR);

        // Default direction: if your physical wiring makes a wheel spin wrong way, reverse here.
        setDirection(DcMotor.Direction.FORWARD);

        // Default to open-loop for teleop responsiveness
        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Default zero power behavior: BRAKE makes autonomous moves stop more reliably.
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Stop();
    }

    /**
     * Teleop driving: mix turn/forward/strafe and write motor power once (prevents overwriting).
     * This mixing uses the same sign convention as your Direct* helpers so pure axis moves
     * behave the same as before.
     */
    public void Drive(Gamepad gamepad)
    {
        // joystick axes: push up = negative on most controllers, so invert Y
        double forward = -gamepad.left_stick_y; // forward positive
        double turn    =  gamepad.left_stick_x; // right positive
        double strafe  =  gamepad.right_stick_x; // right positive

        // deadzone
        final double DZ = 0.08;
        forward = Math.abs(forward) < DZ ? 0.0 : forward;
        turn    = Math.abs(turn)    < DZ ? 0.0 : turn;
        strafe  = Math.abs(strafe)  < DZ ? 0.0 : strafe;

        // global speed modifier (use right bumper for fast, 'b' for slow)
        double speedMod = calculateSpeedModifier(gamepad, 0.2, 1.0, 1.0);

        // mecanum mix (consistent with Direct* helpers)
        double fl = forward + strafe + turn;
        double fr = forward - strafe - turn;
        double bl = forward - strafe + turn;
        double br = forward + strafe - turn;

        // normalize so no value > 1.0
        double max = Math.abs(fl);
        max = Math.max(max, Math.abs(fr));
        max = Math.max(max, Math.abs(bl));
        max = Math.max(max, Math.abs(br));
        if (max < 1.0) max = 1.0;
        fl /= max; fr /= max; bl /= max; br /= max;

        // apply global speed
        fl *= speedMod; fr *= speedMod; bl *= speedMod; br *= speedMod;

        // write to motors (these methods apply per-motor modifiers)
        setPowerFL(fl);
        setPowerFR(fr);
        setPowerBL(bl);
        setPowerBR(br);
    }

    public void Stop()
    {
        setPower(0);
    }

    protected void Forward(Gamepad gamepad, float speed)
    {
        double speedMod = calculateSpeedModifier(gamepad, 0.6, 1.0, 1.0);
        DirectForward(speed * speedMod);
    }

    protected void Strafe(Gamepad gamepad, float speed)
    {
        double speedMod = calculateSpeedModifier(gamepad, 0.6, 1.0, 1.0);
        DirectStrafe(speed * speedMod);
    }

    public void Turn(Gamepad gamepad, float speed)
    {
        double speedMod = calculateSpeedModifier(gamepad, 0.6, 1.0, 1.0);
        DirectTurn(speed * speedMod);
    }

    protected double calculateSpeedModifier(@NonNull Gamepad gamepad, double slow, double normal, double fast)
    {
        if (gamepad.right_bumper) return fast;
        if (gamepad.b) return slow;
        return normal;
    }

    protected void setDirection(DcMotor.Direction direction)
    {
        MotorfL.setDirection(direction);
        MotorbL.setDirection(direction);
        MotorfR.setDirection(direction);
        MotorbR.setDirection(direction);
    }

    /**
     * Initialize encoders: reset and switch to RUN_USING_ENCODER.
     * WARNING: Only call at the start of autonomous or when safe to reset position.
     */
    public void initEncoders()
    {
        // ensure motors will actively brake during encoder moves
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // reset encoder counts to zero
        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // switch to a mode that allows velocity/position control – keeps counts running
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        encoders_initialized = true;
    }

    public void DirectForward(double speed)
    {
        // Note: signs chosen to match your previous logic. Adjust if your wiring differs.
        setPowerFR(speed); //-
        setPowerFL(speed); //+
        setPowerBL(speed); //+
        setPowerBR(speed); //-
    }

    public void DirectTurn(double speed)
    {
        setPowerFR(-speed);
        setPowerFL(speed);
        setPowerBL(speed);
        setPowerBR(-speed);
    }

    protected void DirectStrafe(double speed)
    {
        // matches your previously provided mapping
        setPowerFR(-speed); //+
        setPowerFL(speed); //+
        setPowerBL(-speed); //-
        setPowerBR(speed); //-
    }

    public void setPower(double power)
    {
        MotorfL.setPower(power * MOTOR_fL_MODIFIER);
        MotorbL.setPower(power * MOTOR_bL_MODIFIER);
        MotorfR.setPower(power * MOTOR_fR_MODIFIER);
        MotorbR.setPower(power * MOTOR_bR_MODIFIER);
    }

    protected void setPowerFL(double power)
    {
        MotorfL.setPower(power * MOTOR_fL_MODIFIER);
    }

    protected void setPowerBL(double power)
    {
        MotorbL.setPower(power * MOTOR_bL_MODIFIER);
    }

    protected void setPowerFR(double power)
    {
        MotorfR.setPower(power * MOTOR_fR_MODIFIER);
    }

    protected void setPowerBR(double power)
    {
        MotorbR.setPower(power * MOTOR_bR_MODIFIER);
    }

    public void setMode(DcMotor.RunMode mode)
    {
        MotorfL.setMode(mode);
        MotorbL.setMode(mode);
        MotorfR.setMode(mode);
        MotorbR.setMode(mode);
    }

    public void setTargetPosition(int ticks)
    {
        MotorfL.setTargetPosition(ticks);
        MotorbL.setTargetPosition(ticks);
        MotorfR.setTargetPosition(ticks);
        MotorbR.setTargetPosition(ticks);
    }

    public boolean isBusy()
    {
        return MotorfL.isBusy() || MotorbL.isBusy() || MotorfR.isBusy() || MotorbR.isBusy();
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior)
    {
        MotorfL.setZeroPowerBehavior(behavior);
        MotorbL.setZeroPowerBehavior(behavior);
        MotorfR.setZeroPowerBehavior(behavior);
        MotorbR.setZeroPowerBehavior(behavior);
    }

    // Individual position accessors useful for logging / calibration
    public int getMotorFLPosition() { return MotorfL.getCurrentPosition(); }
    public int getMotorFRPosition() { return MotorfR.getCurrentPosition(); }
    public int getMotorBLPosition() { return MotorbL.getCurrentPosition(); }
    public int getMotorBRPosition() { return MotorbR.getCurrentPosition(); }
}
