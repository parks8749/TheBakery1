package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

@Autonomous(name="Shooting", group="Autonomous")
public class Shooting extends LinearOpMode
{
    Driver driver;
    public CRServo backBottom;
    public CRServo backIntake;
    public CRServo launcherWheel;
    public DcMotor leftFlyWheel;
    public DcMotor rightFlyWheel;


    @Override
    public void runOpMode() {
//        driver = new Driver(hardwareMap);
        driver = new Driver(this, hardwareMap);
        backBottom   = (hardwareMap.get(CRServo.class, "BackBottom"));
        backIntake   = (hardwareMap.get(CRServo.class, "BackIntake"));
        launcherWheel= (hardwareMap.get(CRServo.class, "LauncherWheel"));
        leftFlyWheel = (hardwareMap.get(DcMotor.class, "leftFly"));
        rightFlyWheel = (hardwareMap.get(DcMotor.class, "rightFly"));

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        waitForStart();

        if (opModeIsActive())
        {
            driver.forward_tiles(-1.0);
            sleep(500);
            backIntake.setPower(-1.0);
            backBottom.setPower(-1.0);
            launcherWheel.setPower(1.0);
            leftFlyWheel.setPower(-1.0);
            rightFlyWheel.setPower(1.0);
            sleep(10000);
        }

    }
}