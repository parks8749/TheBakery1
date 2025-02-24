package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.DriveTrain;
@TeleOp(name="Drive25", group="TeleOp")
public class Drive25 extends LinearOpMode{
    private DriveTrain driveTrain;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        driveTrain = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()){
            telemetry.addData("fR Pos", String.valueOf(driveTrain.MotorfR.getPower()));
            telemetry.addData("fL Pos", String.valueOf(driveTrain.MotorfL.getPower()));
            telemetry.addData("bR Pos", String.valueOf(driveTrain.MotorbR.getPower()));
            telemetry.addData("bL Pos", String.valueOf(driveTrain.MotorbL.getPower()));

            telemetry.update();

            driveTrain.Drive(gamepad1);
        }
    }
}
