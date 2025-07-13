package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Autonomous(name = "AshleyAuto", group = "Autonomous")

public class AshleyAuto extends LinearOpMode {
    Driver driver;
    private Servo arm;
    private Servo turn;
    private Servo scoop;
    private DcMotor slide;
    private Servo claw;

    public void runOpMode()
    {
        slide = hardwareMap.get(DcMotor.class, "slide");
        arm = hardwareMap.get(Servo.class, "arm");
        scoop = hardwareMap.get(Servo.class, "scoop");
        turn = hardwareMap.get(Servo.class, "turn");
        claw = hardwareMap.get(Servo.class, "claw");
        driver = new Driver(hardwareMap);

        // direction for servos;
        arm.setDirection(Servo.Direction.FORWARD);
        turn.setDirection(Servo.Direction.FORWARD);
        scoop.setDirection(Servo.Direction.FORWARD);

        waitForStart(); //makes sure start button is pressed befre runnign

        driver.forward_tiles(2);//add number of tiles to move
        sleep(5000);
        arm.setPosition(0.5);
        sleep(2000);
        slide.setPower(-1.0);//1 is fully pressing toggle (full power), 0 is letting go
        sleep(5000);//sleep until slide should stop going up
        slide.setPower(0);
        sleep(1000); //rest so robot finishes previous move
        arm.setPosition(1.0);
        sleep(2000);
        driver.forward_tiles(0.5);
        sleep(1000);
        slide.setPower(1.0);//1 is fully pressing toggle (full power), 0 is letting go
        sleep(500);//sleep until slide should stop going up
        slide.setPower(0);
        sleep(1000);
        claw.setPosition(0.15);
        sleep(1000);
        slide.setPower(-1.0);//1 is fully pressing toggle (full power), 0 is letting go
        sleep(500); //sleep until slide should stop going up
        slide.setPower(0);
        sleep(1000);
        driver.forward_tiles(-2.5); //add number of tiles to move
        sleep(1000);
        slide.setPower(1.0);
        sleep(5000);
    }
}



