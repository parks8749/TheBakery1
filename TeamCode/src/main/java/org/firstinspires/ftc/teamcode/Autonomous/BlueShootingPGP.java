package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

@Autonomous(name="BlueShootingPGP", group="Autonomous")
public class BlueShootingPGP extends LinearOpMode
{
    Driver driver;
    public CRServo backBottom;
    public CRServo backIntake;
    public CRServo launcherWheel;
    public DcMotor leftFlyWheel;
    public DcMotor rightFlyWheel;
    public CRServo rightBelt;
    public CRServo leftBelt;
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
            driver.forward_tiles(-0.6); // -0.75
            backIntake.setPower(-1.0);
            backBottom.setPower(-1.0);
            launcherWheel.setPower(1.0);
            leftFlyWheel.setPower(-1.0);
            rightFlyWheel.setPower(1.0);
            sleep(4000);
//-------------------------------------------------------------------------------
            // this section turns on all the things needed to shoot the artifact in front intake
            rightBelt.setPower(1.0);
            leftBelt.setPower(-1.0);
            backBottom.setPower(1.0);
            launcherWheel.setPower(1.0);
            backIntake.setPower(-1.0);
            leftFlyWheel.setPower(-1.0);
            rightFlyWheel.setPower(1.0);
            sleep(4000);
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
            // drives robot to take in the first row of artifacts (GPP)
            driver.forward_tiles(-0.5);
            driver.turn_ticks(-420,1);
            driver.strafe_tiles(2.05,1);
            frontIntake.setPower(1.0);
            rightBelt.setPower(1.0);
            leftBelt.setPower(-1.0);
            launcherWheel.setPower(1.0);
            backIntake.setPower(-1.0);
//            driver.forward_tiles(3, 0.2);
            sleep(500);
            driver.forward_tiles(2.5);
            sleep(500);
//--------------------------------------------------------------------------------------------------
            // backs up and gets in position to shoot in goal
            driver.forward_tiles(-1.35);
            driver.strafe_tiles(-2.7,1);
            driver.turn_ticks(440,1);
            driver.forward_tiles(0.2,1);
            backBottom.setPower(1.0);
            leftFlyWheel.setPower(-1.0);
            rightFlyWheel.setPower(1.0);
            sleep(4000);
//--------------------------------------------------------------------------------------------------
            // stops all motor and get out of launch zone to get leave points
            rightBelt.setPower(0);
            leftBelt.setPower(0);
            backBottom.setPower(0);
            launcherWheel.setPower(0);
            backIntake.setPower(0);
            leftFlyWheel.setPower(0.0);
            rightFlyWheel.setPower(0.0);
            driver.strafe_tiles(1,1);
        }

    }
}