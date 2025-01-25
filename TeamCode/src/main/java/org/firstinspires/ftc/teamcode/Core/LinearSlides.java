package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * This class represents the linear slides mechanism.
 * It controls a motor to extend or retract the linear slides to a specific position.
 */
public class LinearSlides
{
    public DcMotor motor;
    /**
     * The position to which the slides will extend.
     */
    private int extendedPosition;

    /**
     * True if the slides are extended (at the extended position), false otherwise.
     */
    private boolean isExtended;
    private ServoToggle servoToggle;

    /**
     * Constructor for LinearSlides.
     * Initializes the motor and sets its target position and mode.
     *
     * @param map         The hardware map to get the motor configuration.
     * @param name        The name of the motor used to control the slides in the hardware map.
     * @param extendedPosition The max position to which the slides will extend.
     */
    public LinearSlides(HardwareMap map, String name, int extendedPosition)
    {
        this.motor = map.get(DcMotor.class, name);
        this.extendedPosition = extendedPosition;
        this.isExtended = false;

        this.servoToggle = new ServoToggle(this::toggle);

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(extendedPosition);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void update(boolean buttonState)
    {
        servoToggle.update(buttonState);
    }

    /**
     * Toggles the position of the linear slides.
     * If the slides are at the top, it will reset them (retract); if not, it will extend them.
     */
    private void toggle()
    {
        if (isExtended)
            reset();
        else
            extend();
    }

    /**
     * Extends the linear slides to their extended position.
     * Sets the target position to the extended and applies power to the motor.
     */
    public void extend()
    {
        motor.setTargetPosition(extendedPosition);
        motor.setPower(0.5);
        isExtended = true;
    }

    public void initExtend() {
        motor.setTargetPosition(250);
        motor.setPower(0.75);
        isExtended = true;
    }

    /**
     * Resets the linear slides to their starting position.
     * Sets the target position to zero and applies power to the motor.
     */
    public void reset()
    {
        motor.setTargetPosition(0);
        motor.setPower(0.5);
        isExtended = false;
    }

    /**
     * Checks if the linear slides are in the extended position.
     *
     * @return True if the slides are extended , false otherwise.
     */
    public boolean isExtended()
    {
        return isExtended;
    }

    /**
     * Gets the current position of the linear slides.
     *
     * @return The current position of the motor driving the slides.
     */
    public int getPosition()
    {
        return motor.getCurrentPosition();
    }
}

