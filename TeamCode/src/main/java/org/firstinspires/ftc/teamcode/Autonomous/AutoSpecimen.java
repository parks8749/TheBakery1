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
        //start
        driver.forward_tiles(0.86);
        sleep(400);
        arm.setPosition(0.5);
        sleep(300);
        slide.setPower(-1.0);
        sleep(1100);
        slide.setPower(0);
        sleep(500);
        driver.forward_tiles(0.2);
        sleep(500);
        slide.setPower(1.0);
        sleep(200);
        slide.setPower(0);
        sleep(500);
        claw.setPosition(0);
        sleep(200);
        driver.forward_tiles(-0.2);
        sleep(500);
        slide.setPower(1.0);
        sleep(500);
        arm.setPosition(1.0);
        sleep(1000);
        //scored one on high bar
        driver.turn_90_clockwise(1);
        driver.forward_tiles(1.35);
        driver.turn_90_clockwise(1);
        driver.forward_tiles(0.97);
        driver.strafe_tiles(0.3);
        sleep(300);
        claw.setPosition(0.45);
        //grabs the specimen from human player
        sleep(300);
        arm.setPosition(0.5);
        sleep(500);
        slide.setPower(-1.0);
        sleep(100);
        slide.setPower(0.0);
        arm.setPosition(1.0);
        driver.forward_tiles(-0.935);
        slide.setPower(1.0);
        sleep(100);
        arm.setPosition(1.0);
        sleep(500);
        driver.turn_90_clockwise(1);
        driver.forward_tiles(1.4);
        driver.turn_90_clockwise(1);
        sleep(500);
        arm.setPosition(0.5);
        sleep(500);
        slide.setPower(-1.0);
        sleep(1100);
        slide.setPower(0);
        sleep(1000);
        driver.forward_tiles(0.2);
        sleep(1000);
        slide.setPower(1.0);
        sleep(100);
        slide.setPower(0);
        sleep(500);
        claw.setPosition(0.0);
        sleep(500);
        driver.forward_tiles(-0.2);
        sleep(500);
        slide.setPower(1.0);
        sleep(500);
        arm.setPosition(1.0);
        sleep(1000);

    }
}
// to pull code:
// git pull origin master

// to push code:
// git add . -> adds the code you from android studio to git hub
// git commit -m"champs ready" -> you do this to add messages or comments in git hub
// git push origin master

// peyton was here
// peyton was here again ;)