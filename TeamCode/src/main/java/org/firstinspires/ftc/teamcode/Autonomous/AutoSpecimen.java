package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@Autonomous(name="AutoSpecimen", group="Autonomous")
public class AutoSpecimen extends LinearOpMode
{
    Driver driver;
    //    Scorer scorer;
    private Servo arm;
//    private Servo turn;
    private DcMotor slide;
//    private Servo scoop;
    private Servo claw;

    int tile_length = 24; //in inches
    public void runOpMode()
    {
        driver = new Driver(hardwareMap);
        slide = hardwareMap.get(DcMotor.class, "slide");
        arm = hardwareMap.get(Servo.class, "arm");
//        scoop = hardwareMap.get(Servo.class, "scoop");
        claw = hardwareMap.get(Servo.class, "claw");
        arm.setDirection(Servo.Direction.FORWARD);

        waitForStart();
        driver.forward_tiles(-1.1);
        sleep(1000);
        arm.setPosition(0.5);
        sleep(1000);
        slide.setPower(-1.0); // now this makes it go up for some reason
        sleep(1100);
        slide.setPower(0);
        sleep(1000);
        driver.forward_tiles(-0.118);
        sleep(1000);
        slide.setPower(1.0);
        sleep(100);
        slide.setPower(0);
        sleep(500);
        claw.setPosition(0.0);
        sleep(500);
        driver.forward_tiles(0.3);
        sleep(500);
        slide.setPower(1.0);
        sleep(500);
        arm.setPosition(1.0);
        sleep(1000);
    }
}
