package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class servo extends OpMode{
    private CRServo servo;


    @Override
    public void init()
    {
        servo = hardwareMap.get(CRServo.class, "servo");
    }
    @Override
    public void loop()
    {
        servo.setPower(-1.0);


//        while (gamepad1.a) {
//            servo.setPower(1.0);
//        }
//        while (gamepad1.b){
//            servo.setPower(-1.0);
//        }
//        servo.setPower(0.0);
    }
}