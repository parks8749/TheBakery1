package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="ParkingAutoRedClose", group="Autonomous")
public class ParkingAutoRedClose extends LinearOpMode
{
    Driver driver;
    Scorer scorer;
    int tile_length = 24; //in inches
    public void runOpMode()
    {
        driver = new Driver(hardwareMap);
//        scorer = new Scorer(hardwareMap);

        waitForStart();

//        driver.parkEasyRed();
        driver.forward_tiles(2.0);
    }
}