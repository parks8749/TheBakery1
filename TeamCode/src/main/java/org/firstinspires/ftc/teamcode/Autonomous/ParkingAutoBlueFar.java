package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="ParkingAutoBlueFar", group="Autonomous")
public class ParkingAutoBlueFar extends LinearOpMode
{
    Driver driver;
//    Scorer scorer;
    int tile_length = 24; //in inches
    public void runOpMode()
    {
        driver = new Driver(hardwareMap);
//        scorer = new Scorer(hardwareMap);

        waitForStart();
//        scorer.load();
        driver.forward_tiles(-4.0);
    }
}