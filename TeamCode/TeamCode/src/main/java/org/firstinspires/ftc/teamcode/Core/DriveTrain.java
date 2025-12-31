//package org.firstinspires.ftc.teamcode.Core;
//
//import androidx.annotation.NonNull;
//
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.Gamepad;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//public class DriveTrain
//{
//    public DcMotor MotorfL, MotorbL;
//    public DcMotor MotorfR, MotorbR;
//
//    /**
//     * Motor power modifiers to ensure consistent directgit pull ional movement across all wheels.
//     * <p>
//     * These modifiers are used to adjust the direction of rotation for each motor, as
//     * sometimes wheels may move in opposite directions with the same power setting.
//     * <p>
//     * These modifiers ensure that all wheels move in the intended direction.
//     */
//    protected final int MOTOR_fL_MODIFIER = 1, MOTOR_bL_MODIFIER = 1, MOTOR_fR_MODIFIER = 1, MOTOR_bR_MODIFIER = 1;
//
//    protected boolean encoders_initialized = false;
//
//    /**
//     * Constructor for DriveTrain.
//     * Initializes the motors used in the drive train.
//     *
//     * @param map HardwareMap to get hardware configurations.
//     * @param FL  Front Left motor name.
//     * @param BL  Back Left motor name.
//     * @param FR  Front Right motor name.
//     * @param BR  Back Right motor name.
//     */
//    public DriveTrain(HardwareMap map, String FL, String BL, String FR, String BR)
//    {
//        MotorfL = map.get(DcMotor.class, FL);
//        MotorbL = map.get(DcMotor.class, BL);
//        MotorfR = map.get(DcMotor.class, FR);
//        MotorbR = map.get(DcMotor.class, BR);
//
//        setDirection(DcMotor.Direction.FORWARD);
//        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
////        MotorBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
////        MotorBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
////        MotorFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
////        MotorFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        Stop();
//    }
//
//    /**
//     * Drives the robot based on Gamepad input.
//     * It controls the robot's turn, forward, and strafe movements.
//     *
//     * @param gamepad The gamepad input from the user.
//     */
//    public void Drive(Gamepad gamepad)
//    {
//        Turn(gamepad, gamepad.left_stick_x); // change to right_stick_x
//        Forward(gamepad, gamepad.left_stick_y);
//        Strafe(gamepad, gamepad.right_stick_x); // change to left_stick_x
//    }
//
//    /**
//     * Stops all motors in the drive train.
//     */
//    public void Stop()
//    {
//        setPower(0);
//    }
//
//    /**
//     * Controls the strafing movement of the robot with speed modifiers.
//     *
//     * @param gamepad The gamepad controlling movement for the robot.
//     * @param speed   The speed at which to strafe.
//     */
////    protected void Strafe(Gamepad gamepad, float speed)
////    {
////        double speedMod = calculateSpeedModifier(gamepad, 0.2, 0.6, 1);
////        DirectStrafe(speed * speedMod);
////    }
//
//    /**
//     * Controls the forward/backward movement of the robot with speed modifiers.
//     *
//     * @param gamepad The gamepad controlling movement for the robot.
//     * @param speed   The speed at which to move forward/backward.
//     */
//    protected void Forward(Gamepad gamepad, float speed)
//    {
//        double speedMod = calculateSpeedModifier(gamepad, 0.2, 1.0, 1);
//        DirectForward(speed * speedMod);
//    }
//
//    protected void Strafe(Gamepad gamepad, float speed)
//    {
//        double speedMod = calculateSpeedModifier(gamepad, 0.2, 1.0, 1);
//        DirectStrafe(speed * speedMod);
//    }
//
//    /**
//     * Controls the turning movement of the robot with speed modifiers.
//     *
//     * @param gamepad The gamepad controlling movement for the robot.
//     * @param speed   The speed at which to turn.
//     */
//    public void Turn(Gamepad gamepad, float speed)
//    {
//        double speedMod = calculateSpeedModifier(gamepad, 0.3, 1.0, 1);
//        DirectTurn(speed * speedMod);
//    }
//
//    /**
//     * Calculates the speed modifier based on gamepad button inputs.
//     *
//     * @param gamepad The gamepad controlling movement for the robot.
//     * @param slow    Slow speed modifier.
//     * @param normal  Normal speed modifier.
//     * @param fast    Fast speed modifier.
//     * @return The calculated speed modifier.
//     */
//    protected double calculateSpeedModifier(@NonNull Gamepad gamepad, double slow, double normal, double fast)
//    {
//        double speedModifier = normal;
//        if (gamepad.right_bumper)
//        {
//            speedModifier = fast;
//        }
//        else if (gamepad.b)
//        {
//            speedModifier = slow;
//        }
//        else
//        {
//            speedModifier = normal;
//        }
//        return speedModifier;
//    }
//
//    /**
//     * Sets the direction of all motors in the drive train.
//     *
//     * @param direction The direction to set the motors.
//     */
//    protected void setDirection(DcMotor.Direction direction)
//    {
//        MotorfL.setDirection(direction);
//        MotorbL.setDirection(direction);
//        MotorfR.setDirection(direction);
//        MotorbR.setDirection(direction);
//    }
//
//    /**
//     * Initializes the encoders for the motors.
//     * WARNING: ONLY USE THIS IN AUTONOMOUS MODE.
//     */
//    public void initEncoders()
//    {
////        MotorBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
////        MotorBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
////        MotorFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
////        MotorFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
////        setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    }
//
//    /**
//     * Directly controls the forward movement of the robot without speed modifiers.
//     *
//     * @param speed The speed at which to move forward.
//     */
//    public void DirectForward(double speed)
//    {
//        System.out.println("Driving");
//        setPowerFR(-speed);
//        setPowerFL(speed);
//        setPowerBL(speed);
//        setPowerBR(-speed);
//    }
//
//    /**
//     * Directly controls the turning movement of the robot without speed modifiers.
//     *
//     * @param speed The speed at which to turn.
//     */
//    public void DirectTurn(double speed)
//    {
//        System.out.println("Turning");
//        setPowerFR(-speed);
//        setPowerFL(-speed);
//        setPowerBL(-speed);
//        setPowerBR(-speed);
//    }
//
//    /**
//     * Directly controls the strafing movement of the robot without speed modifiers.
//     *
//     * @param speed The speed at which to strafe.
//     */
//    protected void DirectStrafe(double speed)
//    {
//        System.out.println("Strafing");
//        setPowerFR(-speed); // used to be +
//        setPowerFL(-speed); // used to be +
//        setPowerBL(speed); // used to be -
//        setPowerBR(speed); // used to be -
//    }
//
//    /**
//     * Sets the power for all motors in the drive train.
//     *
//     * @param power The power level to set the motors.
//     */
//    public void setPower(double power)
//    {
//        MotorfL.setPower(power * MOTOR_fL_MODIFIER);
//        MotorbL.setPower(power * MOTOR_bL_MODIFIER);
//        MotorfR.setPower(power * MOTOR_fR_MODIFIER);
//        MotorbR.setPower(power * MOTOR_bR_MODIFIER);
//    }
//
//    /**
//     * Sets the power for the Front Left motor.
//     *
//     * @param power The power level for the Front Left motor.
//     */
//    protected void setPowerFL(double power)
//    {
//        MotorfL.setPower(power * MOTOR_fL_MODIFIER);
//    }
//
//    /**
//     * Sets the power for the Back Left motor.
//     *
//     * @param power The power level for the Back Left motor.
//     */
//    protected void setPowerBL(double power)
//    {
//        MotorbL.setPower(power * MOTOR_bL_MODIFIER);
//    }
//
//    /**
//     * Sets the power for the Front Right motor.
//     *
//     * @param power The power level for the Front Right motor.
//     */
//    protected void setPowerFR(double power)
//    {
//        MotorfR.setPower(power * MOTOR_fR_MODIFIER);
//    }
//
//    /**
//     * Sets the power for the Back Right motor.
//     *
//     * @param power The power level for the Back Right motor.
//     */
//    protected void setPowerBR(double power)
//    {
//        MotorbR.setPower(power * MOTOR_bR_MODIFIER);
//    }
//
//    /**
//     * Sets the mode for all motors in the drive train.
//     *
//     * @param mode The mode to set the motors.
//     */
//    public void setMode(DcMotor.RunMode mode)
//    {
//        MotorfL.setMode(mode);
//        MotorbL.setMode(mode);
//        MotorfR.setMode(mode);
//        MotorbR.setMode(mode);
//    }
//    public void setTargetPosition(int ticks)
//    {
//        MotorfL.setTargetPosition(ticks);
//        MotorbL.setTargetPosition(ticks);
//        MotorfR.setTargetPosition(ticks);
//        MotorbR.setTargetPosition(ticks);
//    }
//    public boolean isBusy()
//    {
//        return MotorfL.isBusy() || MotorbL.isBusy() || MotorfR.isBusy() || MotorbR.isBusy();
//    }
//
//}




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
