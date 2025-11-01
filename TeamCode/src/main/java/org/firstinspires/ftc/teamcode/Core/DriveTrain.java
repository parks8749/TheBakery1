package org.firstinspires.ftc.teamcode.Core;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain
{
    public DcMotor MotorfL, MotorbL;
    public DcMotor MotorfR, MotorbR;

    /**
     * Motor power modifiers to ensure consistent directgit pull ional movement across all wheels.
     * <p>
     * These modifiers are used to adjust the direction of rotation for each motor, as
     * sometimes wheels may move in opposite directions with the same power setting.
     * <p>
     * These modifiers ensure that all wheels move in the intended direction.
     */
    protected final int MOTOR_fL_MODIFIER = 1, MOTOR_bL_MODIFIER = 1, MOTOR_fR_MODIFIER = 1, MOTOR_bR_MODIFIER = 1;

    protected boolean encoders_initialized = false;

    /**
     * Constructor for DriveTrain.
     * Initializes the motors used in the drive train.
     *
     * @param map HardwareMap to get hardware configurations.
     * @param FL  Front Left motor name.
     * @param BL  Back Left motor name.
     * @param FR  Front Right motor name.
     * @param BR  Back Right motor name.
     */
    public DriveTrain(HardwareMap map, String FL, String BL, String FR, String BR)
    {
        MotorfL = map.get(DcMotor.class, FL);
        MotorbL = map.get(DcMotor.class, BL);
        MotorfR = map.get(DcMotor.class, FR);
        MotorbR = map.get(DcMotor.class, BR);

        setDirection(DcMotor.Direction.FORWARD);
        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        MotorBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        MotorBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        MotorFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        MotorFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Stop();
    }

    /**
     * Drives the robot based on Gamepad input.
     * It controls the robot's turn, forward, and strafe movements.
     *
     * @param gamepad The gamepad input from the user.
     */
    public void Drive(Gamepad gamepad)
    {
        Turn(gamepad, gamepad.right_stick_x);
        Forward(gamepad, gamepad.left_stick_y);
        Strafe(gamepad, gamepad.left_stick_x);
    }

    /**
     * Stops all motors in the drive train.
     */
    public void Stop()
    {
        setPower(0);
    }

    /**
     * Controls the strafing movement of the robot with speed modifiers.
     *
     * @param gamepad The gamepad controlling movement for the robot.
     * @param speed   The speed at which to strafe.
     */
//    protected void Strafe(Gamepad gamepad, float speed)
//    {
//        double speedMod = calculateSpeedModifier(gamepad, 0.2, 0.6, 1);
//        DirectStrafe(speed * speedMod);
//    }

    /**
     * Controls the forward/backward movement of the robot with speed modifiers.
     *
     * @param gamepad The gamepad controlling movement for the robot.
     * @param speed   The speed at which to move forward/backward.
     */
    protected void Forward(Gamepad gamepad, float speed)
    {
        double speedMod = calculateSpeedModifier(gamepad, 0.2, 1.0, 1);
        DirectForward(speed * speedMod);
    }

    protected void Strafe(Gamepad gamepad, float speed)
    {
        double speedMod = calculateSpeedModifier(gamepad, 0.2, 1.0, 1);
        DirectStrafe(speed * speedMod);
    }

    /**
     * Controls the turning movement of the robot with speed modifiers.
     *
     * @param gamepad The gamepad controlling movement for the robot.
     * @param speed   The speed at which to turn.
     */
    public void Turn(Gamepad gamepad, float speed)
    {
        double speedMod = calculateSpeedModifier(gamepad, 0.3, 1.0, 1);
        DirectTurn(speed * speedMod);
    }

    /**
     * Calculates the speed modifier based on gamepad button inputs.
     *
     * @param gamepad The gamepad controlling movement for the robot.
     * @param slow    Slow speed modifier.
     * @param normal  Normal speed modifier.
     * @param fast    Fast speed modifier.
     * @return The calculated speed modifier.
     */
    protected double calculateSpeedModifier(@NonNull Gamepad gamepad, double slow, double normal, double fast)
    {
        double speedModifier = normal;
        if (gamepad.right_bumper)
        {
            speedModifier = fast;
        }
        else if (gamepad.b)
        {
            speedModifier = slow;
        }
        else
        {
            speedModifier = normal;
        }
        return speedModifier;
    }

    /**
     * Sets the direction of all motors in the drive train.
     *
     * @param direction The direction to set the motors.
     */
    protected void setDirection(DcMotor.Direction direction)
    {
        MotorfL.setDirection(direction);
        MotorbL.setDirection(direction);
        MotorfR.setDirection(direction);
        MotorbR.setDirection(direction);
    }

    /**
     * Initializes the encoders for the motors.
     * WARNING: ONLY USE THIS IN AUTONOMOUS MODE.
     */
    public void initEncoders()
    {
//        MotorBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        MotorBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        MotorFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        MotorFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * Directly controls the forward movement of the robot without speed modifiers.
     *
     * @param speed The speed at which to move forward.
     */
    public void DirectForward(double speed)
    {
        System.out.println("Driving");
        setPowerFR(-speed);
        setPowerFL(speed);
        setPowerBL(speed);
        setPowerBR(-speed);
    }

    /**
     * Directly controls the turning movement of the robot without speed modifiers.
     *
     * @param speed The speed at which to turn.
     */
    public void DirectTurn(double speed)
    {
        System.out.println("Turning");
        setPowerFR(-speed);
        setPowerFL(-speed);
        setPowerBL(-speed);
        setPowerBR(-speed);
    }

    /**
     * Directly controls the strafing movement of the robot without speed modifiers.
     *
     * @param speed The speed at which to strafe.
     */
    protected void DirectStrafe(double speed)
    {
        System.out.println("Strafing");
        setPowerFR(speed);
        setPowerFL(speed);
        setPowerBL(-speed);
        setPowerBR(-speed);
    }

    /**
     * Sets the power for all motors in the drive train.
     *
     * @param power The power level to set the motors.
     */
    public void setPower(double power)
    {
        MotorfL.setPower(power * MOTOR_fL_MODIFIER);
        MotorbL.setPower(power * MOTOR_bL_MODIFIER);
        MotorfR.setPower(power * MOTOR_fR_MODIFIER);
        MotorbR.setPower(power * MOTOR_bR_MODIFIER);
    }

    /**
     * Sets the power for the Front Left motor.
     *
     * @param power The power level for the Front Left motor.
     */
    protected void setPowerFL(double power)
    {
        MotorfL.setPower(power * MOTOR_fL_MODIFIER);
    }

    /**
     * Sets the power for the Back Left motor.
     *
     * @param power The power level for the Back Left motor.
     */
    protected void setPowerBL(double power)
    {
        MotorbL.setPower(power * MOTOR_bL_MODIFIER);
    }

    /**
     * Sets the power for the Front Right motor.
     *
     * @param power The power level for the Front Right motor.
     */
    protected void setPowerFR(double power)
    {
        MotorfR.setPower(power * MOTOR_fR_MODIFIER);
    }

    /**
     * Sets the power for the Back Right motor.
     *
     * @param power The power level for the Back Right motor.
     */
    protected void setPowerBR(double power)
    {
        MotorbR.setPower(power * MOTOR_bR_MODIFIER);
    }

    /**
     * Sets the mode for all motors in the drive train.
     *
     * @param mode The mode to set the motors.
     */
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

}
