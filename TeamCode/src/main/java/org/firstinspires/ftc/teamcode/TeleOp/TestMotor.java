package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "TestMotor", group = "Test")
public class TestMotor extends LinearOpMode {

    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;

    @Override
    public void runOpMode() throws InterruptedException {
        // Update these to match your configuration names on the Robot Controller phone/Control Hub
        leftMotor  = hardwareMap.get(DcMotor.class, "one");
        rightMotor = hardwareMap.get(DcMotor.class, "two");

        // Use encoders if available for better runtime info; otherwise RUN_WITHOUT_ENCODER is fine.
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Make motors spin physically opposite when given the same power value.
        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Init complete. Motors: left_drive, right_drive");
        telemetry.addData("Info", "Press PLAY to run motors at full power (1.0)");
        telemetry.update();

        waitForStart();
        if (isStopRequested()) return;

        // Run both motors at full power (approx max RPM depending on battery/gearing)
        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);

        telemetry.clearAll();
        telemetry.addLine("Motors running at full power (1.0)");
        telemetry.addData("LeftDirection", leftMotor.getDirection());
        telemetry.addData("RightDirection", rightMotor.getDirection());
        telemetry.update();

        // Keep running until stop
        while (opModeIsActive() && !isStopRequested()) {
            telemetry.addData("LeftPower",  "%.2f", leftMotor.getPower());
            telemetry.addData("RightPower", "%.2f", rightMotor.getPower());
            // If encoders are present, show ticks
            telemetry.addData("LeftTicks", leftMotor.getCurrentPosition());
            telemetry.addData("RightTicks", rightMotor.getCurrentPosition());
            telemetry.update();

            sleep(50);
        }

        // Stop motors
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);
        telemetry.addData("Status", "Stopped");
        telemetry.update();
        sleep(250);
    }
}
