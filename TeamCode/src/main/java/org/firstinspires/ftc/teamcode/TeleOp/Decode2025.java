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

    private static final float STICK_DEADZONE = 0.08f;

    @Override
    public void runOpMode() {
        // map hardware
        driveTrain   = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");
        backBottom   = new BackBottom(hardwareMap.get(CRServo.class, "BackBottom"));
        backIntake   = new BackIntake(hardwareMap.get(CRServo.class, "BackIntake"));
        launcherWheel = new LauncherWheel(hardwareMap.get(CRServo.class, "LauncherWheel"));
        frontIntake = new FrontIntake(hardwareMap.get(CRServo.class, "FrontIntake"));

        flyWheels = new FlyWheels(
                hardwareMap.get(DcMotor.class, "leftFly"),
                hardwareMap.get(DcMotor.class, "rightFly")
        );
        belts = new Belts(
                hardwareMap.get(CRServo.class, "LeftBelt"),
                hardwareMap.get(CRServo.class, "RightBelt")
        );

        // initialize subsystems
        backBottom.init();
        backIntake.init();
        launcherWheel.init();
        flyWheels.init();
        belts.init();
        frontIntake.init();


        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            driveTrain.Drive(gamepad1);

            launcherWheel.update(gamepad2.b);
//            backBottom.update(gamepad2.a);

            float leftStick = applyDeadzone(gamepad2.left_stick_y, STICK_DEADZONE);
            float rightStick = applyDeadzone(gamepad2.right_stick_y, STICK_DEADZONE);
            backIntake.update(leftStick);
            backBottom.update(belts.getMode(), gamepad2.left_stick_y);
//            belts.update(gamepad2.x);
            belts.update(rightStick);
            frontIntake.update(belts.getMode());
//            frontIntake.update(rightStick);
//            topFront.update(gamepad2.y);

            flyWheels.update(gamepad2.right_bumper, gamepad2.left_bumper);

            telemetry.update();

            sleep(10);
        }
    }

    private float applyDeadzone(float val, float dz) {
        return Math.abs(val) < dz ? 0.0f : val;
    }
}
