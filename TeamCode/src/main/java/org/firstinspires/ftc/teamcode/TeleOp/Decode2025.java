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

    private boolean leftFlyOn = false;
    private boolean rightFlyOn = false;
    private boolean prevLeftBumper = false;
    private boolean prevRightBumper = false;

    private static final float STICK_DEADZONE = 0.08f;

    @Override
    public void runOpMode() {
        // map hardware
        driveTrain   = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");
        backBottom   = new BackBottom(hardwareMap.get(CRServo.class, "BackBottom"));
        leftBelt     = new LeftBelt(hardwareMap.get(CRServo.class, "LeftBelt"));
        rightBelt    = new RightBelt(hardwareMap.get(CRServo.class, "RightBelt"));
        backIntake   = new BackIntake(hardwareMap.get(CRServo.class, "BackIntake"));
        frontIntake  = new FrontIntake(hardwareMap.get(CRServo.class, "FrontIntake"));
        launcherWheel= new LauncherWheel(hardwareMap.get(CRServo.class, "LauncherWheel"));
        rightFlyWheel= new RightFlyWheel(hardwareMap.get(DcMotor.class, "RightFlyWheel"));
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

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            driveTrain.Drive(gamepad1);

            rightBelt.update(gamepad1.y);       // y pressed -> right belt on
            launcherWheel.update(gamepad1.b);   // b pressed -> launcher on
            backBottom.update(gamepad1.a);      // a pressed -> back bottom on
            leftBelt.update(gamepad1.x);        // x pressed -> left belt on

            float leftStick = applyDeadzone(gamepad1.left_stick_y, STICK_DEADZONE);
            float rightStick = applyDeadzone(gamepad1.right_stick_y, STICK_DEADZONE);
            frontIntake.update(leftStick);
            backIntake.update(rightStick);

            if (gamepad1.left_bumper && !prevLeftBumper) {
                leftFlyOn = !leftFlyOn;
            }
            if (gamepad1.right_bumper && !prevRightBumper) {
                rightFlyOn = !rightFlyOn;
            }
            prevLeftBumper = gamepad1.left_bumper;
            prevRightBumper = gamepad1.right_bumper;

            leftFlyWheel.update(leftFlyOn);
            rightFlyWheel.update(rightFlyOn);

            telemetry.addData("LeftFly", leftFlyOn ? "ON" : "OFF");
            telemetry.addData("RightFly", rightFlyOn ? "ON" : "OFF");
            telemetry.update();

            sleep(10);
        }
    }

    private float applyDeadzone(float val, float dz) {
        return Math.abs(val) < dz ? 0.0f : val;
    }
}
