package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="AutoBlueBucket", group="Autonomous")
public class AutoBlueBucket extends LinearOpMode
{
    Driver driver;
//    Scorer scorer;
    private Servo arm;
    private Servo turn;
    private DcMotor slide;
    private Servo scoop;
    int tile_length = 24; //in inches
    public void runOpMode()
    {
        driver = new Driver(hardwareMap);
        slide = hardwareMap.get(DcMotor.class, "slide");
        arm = hardwareMap.get(Servo.class, "arm");
        scoop = hardwareMap.get(Servo.class, "scoop");
        arm.setDirection(Servo.Direction.FORWARD);

        waitForStart();
        driver.forward_tiles(1.44);
        sleep(1000);
        arm.setPosition(0.5);
        sleep(500);
        slide.setPower(-1.0);
        sleep(2400);
        slide.setPower(0);
        scoop.setPosition(0.4);
        sleep(1000);
        scoop.setPosition(1.0);
        sleep(1000);
        driver.forward_tiles(-0.1);
        slide.setPower(1.0);
        sleep(2100);
        slide.setPower(0);
        sleep(500);
        arm.setPosition(1.0);
        sleep(1000);
    }
}