package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="ParkingAutoBlueClose", group="Autonomous")
public class ParkingAutoBlueClose extends LinearOpMode
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
        driver.forward_tiles(-2.0);
//        driver.parkEasyBlue();
    }
}