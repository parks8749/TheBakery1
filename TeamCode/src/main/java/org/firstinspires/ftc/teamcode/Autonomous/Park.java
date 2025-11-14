//package org.firstinspires.ftc.teamcode.Autonomous;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//@Autonomous(name = "Park", group = "Autonomous")
//public class Park extends LinearOpMode
//{
//
//    Driver driver;
//
//
//    public void runOpMode() {
//        driver = new Driver(hardwareMap);
//
//
//        waitForStart();
//        driver.forward_tiles(1.0);
//    }
//}
////
////
////



package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Park", group = "Autonomous")
public class Park extends LinearOpMode
{
    Driver driver;

    @Override
    public void runOpMode() {
        driver = new Driver(this, hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            driver.forward_tiles(1.0);
        }
    }
}
