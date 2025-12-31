package org.firstinspires.ftc.teamcode.TeleOp;

import android.app.VoiceInteractor;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Core.*;

@TeleOp(name="Decode2025", group="TeleOp")
public class Decode2025 extends LinearOpMode {
    public DriveTrain driveTrain;
    public BackBottom backBottom;
    public BackIntake backIntake;
    public LauncherWheel launcherWheel;
    public FlyWheels flyWheels;
    public Belts belts;
    public FrontIntake frontIntake;
    public TopFront topFront;

    private static final float STICK_DEADZONE = 0.08f;

    @Override
    public void runOpMode() {
        driveTrain   = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");
        backBottom   = new BackBottom(hardwareMap.get(CRServo.class, "BackBottom"));
        backIntake   = new BackIntake(hardwareMap.get(CRServo.class, "BackIntake"));
        launcherWheel = new LauncherWheel(hardwareMap.get(CRServo.class, "LauncherWheel"));
        frontIntake = new FrontIntake(hardwareMap.get(CRServo.class, "FrontIntake"));
        topFront = new TopFront(hardwareMap.get(CRServo.class, "TopFront"));

        flyWheels = new FlyWheels(
                hardwareMap.get(DcMotor.class, "leftFly"),
                hardwareMap.get(DcMotor.class, "rightFly")
        );
        belts = new Belts(
                hardwareMap.get(CRServo.class, "LeftBelt"),
                hardwareMap.get(CRServo.class, "RightBelt")
        );

        backBottom.init();
        backIntake.init();
        launcherWheel.init();
        flyWheels.init();
        belts.init();
        frontIntake.init();
        topFront.init();


        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            driveTrain.Drive(gamepad1);

            // deadzoned sticks
            float leftStick = applyDeadzone(gamepad2.left_stick_y, STICK_DEADZONE);
            float rightStick = applyDeadzone(gamepad2.right_stick_y, STICK_DEADZONE);

            boolean xPressed = gamepad2.x; // existing override for some subsystems
            boolean yPressed = gamepad2.y; // NEW global activation override

            // belts and frontIntake remain mapped to belts.getMode()
            belts.update(rightStick);
            frontIntake.update(belts.getMode());

            // OTHER subsystems must no longer rely on belts.getMode()
            // pass yPressed to let them run when the new override is pressed
            launcherWheel.update(gamepad2.b, xPressed, yPressed);
            backBottom.update(leftStick, yPressed);
            backIntake.update(leftStick, xPressed, yPressed);
            topFront.update(0); // do not use belts.getMode() here anymore
            flyWheels.update(gamepad2.right_bumper, gamepad2.left_bumper, xPressed, yPressed);

            telemetry.addData("Override X", xPressed);
            telemetry.addData("Override Y (global activate)", yPressed);
            telemetry.addData("Belts mode", belts.getMode());
            telemetry.update();

            sleep(10);
        }

    }

    private float applyDeadzone(float val, float dz) {
        return Math.abs(val) < dz ? 0.0f : val;
    }
}
