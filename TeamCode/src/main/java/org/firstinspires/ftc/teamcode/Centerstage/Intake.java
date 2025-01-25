package org.firstinspires.ftc.teamcode.Centerstage;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Core.ServoToggle;
import org.firstinspires.ftc.teamcode.Core.State;

/** Manages the PixelCollector mechanism in an FTC robot, controlling intake, wrist, and trapdoor. */
public class Intake
{
    enum WristPosition
    {
        LOW,
////        MID_GOING_UP,
////        MID_GOING_DOWN,
        HIGH
    }

    /** Servo for controlling the wrist. */
    private final Servo wrist;


    /** High position value for wrist servo. */
    private final double wristLow = 0.10;

    /** Low position value for wrist servo. */
    //private final double wristMid = 0.34;

    /** High position value for wrist servo. */
    private final double wristHigh = 0.40;

    /** Current wrist position (ACTIVE = high, INACTIVE = low). */
    private WristPosition wristPosition = WristPosition.HIGH;

    /** Servo for claw mechanism. */
    private final Servo claw1;

    private final Servo claw2;

    /** Open position value for claw servo. */
    private final double clawClosePosition= 0.6;

    /** Close position value for claw servo. */
    private final double clawOpenPosition = 0.75;

    /** Current state of claw (ACTIVE = open, INACTIVE = closed). */
    private State clawState = State.INACTIVE;

    /**
     * Initializes intake, wrist, and trapdoor components.
     *
     * @param map           Hardware map for robot configurations.
     * @param wristName     Name of wrist Servo.
     * @param clawName      Name of trapdoor Servo.
     */

    private ServoToggle clawToggle;

    private ServoToggle wristToggle;

    public Intake(@NonNull HardwareMap map, String wristName, String clawName1, String clawName2)
    {
        wrist = map.get(Servo.class, wristName);
        claw1 = map.get(Servo.class, clawName1);
        claw2 = map.get(Servo.class, clawName2);
        claw1.setDirection(Servo.Direction.FORWARD);
        claw2.setDirection(Servo.Direction.REVERSE);

        clawToggle = new ServoToggle(this::toggleClawPosition);

        wristToggle = new ServoToggle(this::toggleWristPosition);
    }

    /** Toggles wrist position between high and low. */
    public void toggleWristPosition()
    {
        if (wristPosition == WristPosition.LOW) {
            setWristHigh();
            wristPosition = WristPosition.HIGH;
        }
//        else if(wristPosition == WristPosition.MID_GOING_UP) {
//            setWristHigh();
//            wristPosition = WristPosition.HIGH;
//        }
        else if(wristPosition == WristPosition.HIGH) {
            setWristLow();
            wristPosition = WristPosition.LOW;
        }
//        else if(wristPosition == WristPosition.MID_GOING_DOWN) {
//            setWristLow();
//            wristPosition = WristPosition.LOW;
//        }
    }

    /** Toggles claw between open and closed positions. */
    public void toggleClawPosition() {
        if (clawState == State.ACTIVE) {
            closeClaw();
        } else {
            openClaw();
        }
    }

    /** Sets claw to open position. */
    public void openClaw()
    {
        clawState = State.ACTIVE;
        claw1.setPosition(clawOpenPosition);
        claw2.setPosition(clawOpenPosition);
    }

    /** Sets claw to closed position. */
    public void closeClaw()
    {
        clawState = State.INACTIVE;
        claw1.setPosition(clawClosePosition);
        claw2.setPosition(clawClosePosition);
    }

//    /** Sets wrist to its low position. */
    public void setWristLow()
    {
        wrist.setPosition(wristLow);
    }
//
//    // uhh delete this later
//    public void xd() { wrist.setPosition(0.42); }
//
//    /** Sets wrist to its high position. */
    public void setWristHigh()
    {
        wrist.setPosition(wristHigh);
    }

//    public void setWristMid()
//    {
//        wrist.setPosition(wristMid);
//    }

    public void updateServos(boolean clawButton, boolean wristButton)
    {
        clawToggle.update(clawButton);
        wristToggle.update(wristButton);
    }
}