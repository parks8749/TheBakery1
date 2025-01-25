package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Core.DriveTrain;

@Autonomous(name="Test2024AUTO", group="Autonomous")
public class Test2024AUTO extends LinearOpMode
{

    public void runOpMode()
    {
        DriveTrain driveTrain = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");
        Driver driver = new Driver(hardwareMap);


        //can we delete this?
        driveTrain.MotorfR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.MotorfL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.MotorbL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.MotorbR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        boolean ran = false;

        waitForStart();
        while(opModeIsActive())
        {
            telemetry.clear();
            telemetry.addData("Status", "Running");
            telemetry.addData("MotorFL", driver.getMotorFLPosition());
            telemetry.addData("MotorFR", driver.getMotorFRPosition());
            telemetry.addData("MotorBL", driver.getMotorBLPosition());
            telemetry.addData("MotorBR", driver.getMotorBRPosition());
            telemetry.update();

            //any movement code goes here
            if (!ran)
            {
                driver.forward_tiles(-2);
                driver.turn_90_counter_clockwise(1);
                driver.forward_tiles(1);
                ran = true;
            }
        }
    }
}