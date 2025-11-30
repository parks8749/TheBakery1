package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

@Autonomous(name="BlueShootingPPG", group="Autonomous")
public class BlueShootingPPG extends LinearOpMode
{
    Driver driver;
    public CRServo backBottom;
    public CRServo backIntake;
    public CRServo launcherWheel;
    public DcMotor leftFlyWheel;
    public DcMotor rightFlyWheel;
//    public CRServo belts;
    public CRServo rightBelt;
    public CRServo leftBelt;
    public CRServo topFront;
    public CRServo frontIntake;


    @Override
    public void runOpMode() {
//        driver = new Driver(hardwareMap);
        driver = new Driver(this, hardwareMap);
        backBottom   = (hardwareMap.get(CRServo.class, "BackBottom"));
        backIntake   = (hardwareMap.get(CRServo.class, "BackIntake"));
        launcherWheel= (hardwareMap.get(CRServo.class, "LauncherWheel"));
        leftFlyWheel = (hardwareMap.get(DcMotor.class, "leftFly"));
        rightFlyWheel = (hardwareMap.get(DcMotor.class, "rightFly"));
        leftBelt = (hardwareMap.get(CRServo.class, "LeftBelt"));
        rightBelt = (hardwareMap.get(CRServo.class, "RightBelt"));
//        belts = (hardwareMap.get(CRServo.class, "RightBelt"));
//        belts = (hardwareMap.get(CRServo.class, "LeftBelt"));
        topFront =  (hardwareMap.get(CRServo.class, "TopFront"));
        frontIntake = (hardwareMap.get(CRServo.class, "FrontIntake"));

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        waitForStart();

        if (opModeIsActive())
        {

            driver.forward_tiles(-0.4);
            backIntake.setPower(-1.0);
            backBottom.setPower(-1.0);
            launcherWheel.setPower(1.0);
            leftFlyWheel.setPower(-1.0);
            rightFlyWheel.setPower(1.0);
            sleep(4000);
//-------------------------------------------------------------------------------
            rightBelt.setPower(1.0);
            leftBelt.setPower(-1.0);
            topFront.setPower(-1.0);
            backBottom.setPower(1.0);
            launcherWheel.setPower(1.0);
            backIntake.setPower(-1.0);
            leftFlyWheel.setPower(-1.0);
            rightFlyWheel.setPower(1.0);
            sleep(5000);
            rightBelt.setPower(0);
            leftBelt.setPower(0);
            topFront.setPower(0);
            backBottom.setPower(0);
            launcherWheel.setPower(0);
            backIntake.setPower(0);
            leftFlyWheel.setPower(0.0);
            rightFlyWheel.setPower(0.0);
//-------------------------------------------------------------------------------------------
            driver.forward_tiles(-1.88);
            sleep(500);
            driver.turn_ticks(-520,1.0);
            frontIntake.setPower(1.0);
            rightBelt.setPower(1.0);
            leftBelt.setPower(-1.0);
            topFront.setPower(-1.0);
            backBottom.setPower(1.0);
            launcherWheel.setPower(1.0);
            backIntake.setPower(-1.0);
            driver.forward_tiles(1.8, 0.4);
            driver.turn_90_clockwise(1);
            leftFlyWheel.setPower(-1.0);
            rightFlyWheel.setPower(1.0);
            driver.forward_tiles(0.7, 1);
            sleep(6000);

        }

    }
}