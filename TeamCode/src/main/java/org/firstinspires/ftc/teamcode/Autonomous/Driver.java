package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Core.DriveTrain;

/**
 * Driver extends DriveTrain with encoder-based high-level movement routines.
 * This version expects a LinearOpMode reference so wait loops can check opModeIsActive()
 * and update telemetry safely.
 */
public class Driver extends DriveTrain
{
    private final LinearOpMode opMode;

    /** The length of a tile in inches */
    int tileLength = 24;

    /** The number of ticks per rotation of the motor (example: REV 20:1 encoder) */
    private final double encoderResolution = 537.7;

    /** Wheel diameter in millimeters */
    private final double wheel_diameter = 96; // mm

    private final int ticks_per_90_turn = 935;

    /** linear distance covered by one rotation (mm) */
    private final double distance_per_motor_rotation = wheel_diameter * Math.PI; // mm per motor rotation

    /** Default timeout for any move (ms) */
    private static final long DEFAULT_MOVE_TIMEOUT_MS = 5000;

    /**
     * Constructor now accepts LinearOpMode to allow opMode checks and telemetry inside wait loops.
     */
    public Driver(LinearOpMode opMode, HardwareMap map)
    {
        super(map, "fL", "bL", "fR", "bR");
        this.opMode = opMode;

        // KEEP DIRECTIONS EXACTLY AS YOU HAD THEM
        super.MotorfR.setDirection(DcMotor.Direction.REVERSE);
        super.MotorbR.setDirection(DcMotor.Direction.REVERSE);
        super.MotorbL.setDirection(DcMotor.Direction.REVERSE);
        super.MotorfL.setDirection(DcMotor.Direction.REVERSE);

        // Initialize encoders and ensure brake behavior for precise autonomous moves
        super.initEncoders();
        super.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * Waits for RUN_TO_POSITION to finish, but is opMode-aware and has a timeout.
     */
    private void waitForMotionFinish(long timeoutMs)
    {
        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        while (opMode.opModeIsActive() && super.isBusy() && timer.milliseconds() < timeoutMs)
        {
            // Helpful telemetry for debugging during autonomous
            opMode.telemetry.addData("FL", getMotorFLPosition());
            opMode.telemetry.addData("FR", getMotorFRPosition());
            opMode.telemetry.addData("BL", getMotorBLPosition());
            opMode.telemetry.addData("BR", getMotorBRPosition());
            opMode.telemetry.update();

            // Let the system breathe and allow other tasks to run
            opMode.idle();
        }

        // Stop motors to ensure a clean end state
        super.Stop();

        // After a RUN_TO_POSITION operation, switch to RUN_USING_ENCODER so encoders remain active
        super.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void wait_to_finish()
    {
        waitForMotionFinish(DEFAULT_MOVE_TIMEOUT_MS);
    }

    private void forward_ticks(int ticks, double power)
    {
        int FR_target = super.MotorfR.getCurrentPosition() - ticks;
        int FL_target = super.MotorfL.getCurrentPosition() + ticks;
        int BR_target = super.MotorbR.getCurrentPosition() - ticks;
        int BL_target = super.MotorbL.getCurrentPosition() + ticks;

        super.MotorfR.setTargetPosition(FR_target);
        super.MotorfL.setTargetPosition(FL_target);
        super.MotorbR.setTargetPosition(BR_target);
        super.MotorbL.setTargetPosition(BL_target);

        super.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        super.setPower(power);

        wait_to_finish();
    }

    public void forward_distance(double distanceMm, double power)
    {
        // distance passed in mm (consistent with wheel_diameter mm)
        int ticks = (int) (distanceMm * encoderResolution / distance_per_motor_rotation);
        forward_ticks(ticks, power);
    }

    public void forward_tiles(double tiles, double power)
    {
        // convert tiles (inches) -> mm
        double distanceMm = tiles * tileLength * 25.4;
        forward_distance(distanceMm, power);
    }

    public void forward_tiles(double tiles)
    {
        forward_tiles(tiles, 0.5);
    }

    private void strafe_ticks(int ticks, double power)
    {
        // Note: this mapping depends on your mecanum/omni layout and motor directions
        int FR_target = super.MotorfR.getCurrentPosition() - ticks;
        int FL_target = super.MotorfL.getCurrentPosition() - ticks;
        int BR_target = super.MotorbR.getCurrentPosition() + ticks;
        int BL_target = super.MotorbL.getCurrentPosition() + ticks;

        super.MotorfR.setTargetPosition(FR_target);
        super.MotorfL.setTargetPosition(FL_target);
        super.MotorbR.setTargetPosition(BR_target);
        super.MotorbL.setTargetPosition(BL_target);

        super.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        super.setPower(power);

        wait_to_finish();
    }

    public void strafe_distance(double distanceMm, double power)
    {
        int ticks = (int) (distanceMm * encoderResolution / distance_per_motor_rotation);
        strafe_ticks(ticks, power);
    }

    public void strafe_tiles(double tiles, double power)
    {
        strafe_distance(tiles * tileLength * 25.4, power);
    }

    public void strafe_tiles(double tiles)
    {
        strafe_tiles(tiles, 0.3);
    }

    public void turn_ticks(int ticks, double power)
    {
        int BL_target = super.MotorbL.getCurrentPosition() + ticks;
        int BR_target = super.MotorbR.getCurrentPosition() + ticks;
        int FL_target = super.MotorfL.getCurrentPosition() + ticks;
        int FR_target = super.MotorfR.getCurrentPosition() + ticks;

        super.MotorbL.setTargetPosition(BL_target);
        super.MotorbR.setTargetPosition(BR_target);
        super.MotorfL.setTargetPosition(FL_target);
        super.MotorfR.setTargetPosition(FR_target);

        super.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        super.setPower(power);

        wait_to_finish();
    }

    public void turn_90_intervals(int times)
    {
        turn_ticks(ticks_per_90_turn * times, 0.5);
    }

    public void turn_90_clockwise(int times)
    {
        turn_90_intervals(times);
    }

    public void turn_90_counter_clockwise(int times)
    {
        turn_90_intervals(-times);
    }
}
