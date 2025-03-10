package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Core.DriveTrain;

public class Driver extends DriveTrain
{
    /**
     * The length of a tile in inches
     */
    int tileLength = 24;
    /**
     * The number of ticks per rotation of the motor
     */
    private final double encoderResolution = 537.7;

    /** Diameter of the wheels in millimeters */
    private final double wheel_diameter = 96; // mm

    private final int ticks_per_90_turn = 935; // normal was 870

    /** linear distance covered by one rotation of the wheel*/
    private final double distance_per_motor_rotation = wheel_diameter * Math.PI;

    public Driver(HardwareMap map)
    {
        super(map, "fL", "bL", "fR", "bR");
        super.MotorfR.setDirection(DcMotor.Direction.REVERSE);
        super.MotorbR.setDirection(DcMotor.Direction.REVERSE);
        super.MotorbL.setDirection(DcMotor.Direction.REVERSE);
        super.MotorfL.setDirection(DcMotor.Direction.REVERSE);
    }

    private void checkIsBusy()
    {
        while(super.isBusy())
        {
            continue;
        }
    }

    private void wait_to_finish()
    {
        checkIsBusy();
        super.Stop();
        waitMilliseconds(300); // 500
    }

    /**
     * Pauses the thread for a specified duration in milliseconds.
     * @param milliseconds The number of milliseconds to wait.
     */
    public void waitMilliseconds(long milliseconds) {
        ElapsedTime runtime = new ElapsedTime(); // Create a new ElapsedTime instance.

        runtime.reset(); // Reset the timer to zero.
        while (runtime.milliseconds() < milliseconds) {
            continue;
            // Wait here until the specified time has elapsed.
            // This loop will exit once the elapsed time is greater than or equal to the specified delay.
        }
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

    public void forward_distance(double distance, double power)
    {
        int current_position = super.MotorfL.getCurrentPosition();
        int ticks = (int) (distance * encoderResolution / distance_per_motor_rotation);
        forward_ticks(ticks, power);

    }

    public void forward_tiles(double tiles, double power)
    {
        forward_distance(tiles * tileLength * 25.4, power);
    }

    public void forward_tiles(double tiles)
    {

        forward_tiles(tiles, 0.5); // normal was 0.3
    }

    private void strafe_ticks(int ticks, double power) {
        int FR_target = super.MotorfR.getCurrentPosition() - ticks; // Front Right moves opposite
        int FL_target = super.MotorfL.getCurrentPosition() - ticks; // Front Left moves forward
        int BR_target = super.MotorbR.getCurrentPosition() + ticks; // Back Right moves forward
        int BL_target = super.MotorbL.getCurrentPosition() + ticks; // Back Left moves opposite

        super.MotorfR.setTargetPosition(FR_target);
        super.MotorfL.setTargetPosition(FL_target);
        super.MotorbR.setTargetPosition(BR_target);
        super.MotorbL.setTargetPosition(BL_target);

        super.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        super.setPower(power);

        wait_to_finish();
    }

    public void strafe_distance(double distance, double power) {
        int ticks = (int) (distance * encoderResolution / distance_per_motor_rotation);
        strafe_ticks(ticks, power);
    }

    public void strafe_tiles(double tiles, double power) {
        strafe_distance(tiles * tileLength * 25.4, power);
    }

    public void strafe_tiles(double tiles) {
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
    } // normal 0.2

    public void turn_90_clockwise(int times)
    {
        turn_90_intervals(times);
    }

    public void turn_90_counter_clockwise(int times)
    {
        turn_90_intervals(-times);
    }

    public int getMotorFLPosition()
    {
        return super.MotorfL.getCurrentPosition();
    }
    public int getMotorFRPosition()
    {
        return super.MotorfR.getCurrentPosition();
    }
    public int getMotorBLPosition()
    {
        return super.MotorbL.getCurrentPosition();
    }
    public int getMotorBRPosition()
    {
        return super.MotorbR.getCurrentPosition();
    }

    public void parkEasyBlue()
    {
        forward_tiles(1.45);
        turn_90_clockwise(1);
        forward_tiles(1);
        turn_90_counter_clockwise(1);
    }

    public void parkEasyRed()
    {
        forward_tiles(1.45);
        turn_90_counter_clockwise(1);
        forward_tiles(1);
        turn_90_clockwise(1);
    }

    public void parkHardBlue()
    {
        forward_tiles(2.2, 0.55); // maybe increase the value a little bit
        turn_90_counter_clockwise(1);
        forward_tiles(3.5, 0.55);
        turn_90_counter_clockwise(1);
        forward_tiles(0.8);
        turn_90_clockwise(1);
    }

    public void parkHardRed()
    {
        forward_tiles(2.2, 0.55); // maybe increase the value a little bit
        turn_90_clockwise(1);
        forward_tiles(3.45, 0.55);
        turn_90_clockwise(1);
        forward_tiles(0.85);
        turn_90_counter_clockwise(1);
    }

}