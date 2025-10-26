package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Core.*;

@TeleOp(name="Decode2025", group="TeleOp")
public class Decode2025 extends LinearOpMode {
    public DriveTrain driveTrain;
    public BackBottom backBottom;
    public LeftBelt leftBelt;
    public RightBelt rightBelt;
    public BackIntake backIntake;
    public FrontIntake frontIntake;
    public LauncherWheel launcherWheel;
    public RightFlyWheel rightFlyWheel;
    public LeftFlyWheel leftFlyWheel;


    @Override
    public void runOpMode() {
        // map hardware
        driveTrain = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");
        backBottom = new BackBottom(hardwareMap.get(CRServo.class, "BackBottom"));
        leftBelt = new LeftBelt(hardwareMap.get(CRServo.class, "LeftBelt"));
        rightBelt = new RightBelt(hardwareMap.get(CRServo.class, "RightBelt"));
        backIntake = new BackIntake(hardwareMap.get(CRServo.class, "BackIntake"));
        frontIntake = new FrontIntake(hardwareMap.get(CRServo.class, "FrontIntake"));
        launcherWheel = new LauncherWheel(hardwareMap.get(CRServo.class, "LauncherWheel"));
        rightFlyWheel = new RightFlyWheel(hardwareMap.get(DcMotor.class, "RightFlyWheel"));
        leftFlyWheel = new LeftFlyWheel(hardwareMap.get(DcMotor.class, "LeftFlyWheel"));

        // initialize
        backBottom.init();
        leftBelt.init();
        rightBelt.init();
        backIntake.init();
        frontIntake.init();
        launcherWheel.init();
        rightFlyWheel.init();
        leftFlyWheel.init();

        telemetry.addData("Status","Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            // subsystem updates
            driveTrain.Drive(gamepad1);
            rightBelt.update(gamepad1.y);
            frontIntake.update(gamepad1.left_stick_y);
            launcherWheel.update(gamepad1.b);
            backIntake.update(gamepad1.right_stick_y);
            backBottom.update(gamepad1.a);
            leftBelt.update(gamepad1.x);
            leftFlyWheel.update(gamepad1.left_bumper);
            rightFlyWheel.update(gamepad1.right_bumper);


            // telemetry
//            telemetry.addData("Claw",     claw.isOpen() ? "Open" : "Closed");
//            telemetry.addData("Arm Pos",  arm.getPosition());
//            telemetry.addData("Scoop Pos",scoop.getPosition());
//            telemetry.addData("Slide Pos",slide.getPosition());
//            telemetry.update();
        }
    }
}

