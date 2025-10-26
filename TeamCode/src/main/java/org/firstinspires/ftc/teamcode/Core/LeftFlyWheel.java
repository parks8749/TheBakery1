package org.firstinspires.ftc.teamcode.Core;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
public class LeftFlyWheel {

    private final DcMotor leftFlyWheel;

    public LeftFlyWheel(DcMotor leftFlyWheel) {
        this.leftFlyWheel = leftFlyWheel;
    }

    public void init() {
        leftFlyWheel.setDirection(CRServo.Direction.FORWARD);
        leftFlyWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void update(boolean leftBumperPressed) {
        if (leftBumperPressed) {
            leftFlyWheel.setPower(1.0);
        } else {
            leftFlyWheel.setPower(0.0);
        }
    }
}
