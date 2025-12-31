package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

@Autonomous(name="RedShooting", group="Autonomous")
public class RedShooting extends LinearOpMode
{
    Driver driver;
    public CRServo backBottom;
    public CRServo backIntake;
    public CRServo launcherWheel;
    public DcMotor leftFlyWheel;
    public DcMotor rightFlyWheel;
    public CRServo leftBelt;
    public CRServo rightBelt;
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
        frontIntake = (hardwareMap.get(CRServo.class, "FrontIntake"));

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        waitForStart();

        if (opModeIsActive())
        {
            // this section puts the robot in position to shoot the 2 artifacts in the back intake
            driver.forward_tiles(-0.75);
            backIntake.setPower(-1.0);
            backBottom.setPower(-1.0);
            launcherWheel.setPower(1.0);
            leftFlyWheel.setPower(-1.0);
            rightFlyWheel.setPower(1.0);
            sleep(3000);
//-------------------------------------------------------------------------------
            // this section turns on all the things needed to shoot the artifact in front intake
            rightBelt.setPower(1.0);
            leftBelt.setPower(-1.0);
            backBottom.setPower(1.0);
            launcherWheel.setPower(1.0);
            backIntake.setPower(-1.0);
            leftFlyWheel.setPower(-1.0);
            rightFlyWheel.setPower(1.0);
            sleep(5000);
//--------------------------------------------------------------------------------------------------
            // this section turns off all the parts to ensure nothing else happens that might give us penalty
            rightBelt.setPower(0);
            leftBelt.setPower(0);
            backBottom.setPower(0);
            launcherWheel.setPower(0);
            backIntake.setPower(0);
            leftFlyWheel.setPower(0.0);
            rightFlyWheel.setPower(0.0);
//--------------------------------------------------------------------------------------------------
            // this section moves the robot out of the launch zone to get move points
            driver.turn_ticks(390,1);
            driver.forward_tiles(0.7,1.0);
            driver.strafe_tiles(-0.5,1.0);
        }

    }
}