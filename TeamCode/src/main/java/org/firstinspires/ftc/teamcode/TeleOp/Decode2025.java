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
    public FlyWheels flyWheels;
    private boolean flyOn = false;
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

        flyWheels = new FlyWheels(
                hardwareMap.get(DcMotor.class, "leftFly"),
                hardwareMap.get(DcMotor.class, "rightFly")
        );

        // initialize
        backBottom.init();
        leftBelt.init();
        rightBelt.init();
        backIntake.init();
        frontIntake.init();
        launcherWheel.init();
        flyWheels.init();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            driveTrain.Drive(gamepad1);

            rightBelt.update(gamepad2.y);
            launcherWheel.update(gamepad2.b);
            backBottom.update(gamepad2.a);
            leftBelt.update(gamepad2.x);

            float leftStick = applyDeadzone(gamepad2.left_stick_y, STICK_DEADZONE);
            float rightStick = applyDeadzone(gamepad2.right_stick_y, STICK_DEADZONE);
            frontIntake.update(leftStick);
            backIntake.update(rightStick);

            if (gamepad2.right_bumper && !prevRightBumper) {
                flyOn = !flyOn;
            }
            prevRightBumper = gamepad2.right_bumper;
            flyWheels.update(flyOn);
            telemetry.addData("FlyWheels", flyOn ? "ON" : "OFF");
            telemetry.update();

            sleep(10);
        }
    }

    private float applyDeadzone(float val, float dz) {
        return Math.abs(val) < dz ? 0.0f : val;
    }
}
