// TeleOp using OpMode (Iterative style)
package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="OppositeMotors_DirReverse")
public class OppositeMotors_DirReverse extends OpMode {

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void init() {
        leftMotor  = hardwareMap.get(DcMotor.class, "one");   // config name
        rightMotor = hardwareMap.get(DcMotor.class, "two");  // config name

        // If wiring/mounting makes them spin same direction with same POWER,
        // reverse one motor so a single power value spins them opposite.
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Optional: set mode depending on whether you want encoders
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {
        // Example: use left stick Y to control speed.
        // Note: gamepad stick forward is negative, so negate to make forward = positive.
        double power = -gamepad1.left_stick_y;

        // clip to [-1,1] just in case
        power = Math.max(-1.0, Math.min(1.0, power));

        // Because we reversed rightMotor above, giving the same power makes them spin opposite.
        leftMotor.setPower(power);
        rightMotor.setPower(power);

        telemetry.addData("Power", power);
        telemetry.update();
    }
}
