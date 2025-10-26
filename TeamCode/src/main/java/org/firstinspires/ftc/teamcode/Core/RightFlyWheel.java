package org.firstinspires.ftc.teamcode.Core;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class RightFlyWheel {

    private final DcMotor rightFlyWheel;

    public RightFlyWheel(DcMotor leftFlyWheel) {
        this.rightFlyWheel = leftFlyWheel;
    }

    public void init() {
        rightFlyWheel.setDirection(CRServo.Direction.REVERSE);
        rightFlyWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void update(boolean rightBumperPressed) {
        if (rightBumperPressed) {
            rightFlyWheel.setPower(1.0);
        } else {
            rightFlyWheel.setPower(0.0);
        }
    }
}
