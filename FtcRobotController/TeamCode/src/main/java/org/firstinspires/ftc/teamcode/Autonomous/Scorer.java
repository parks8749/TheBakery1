//package org.firstinspires.ftc.teamcode.Autonomous;
//
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.teamcode.Centerstage.Intake;
//import org.firstinspires.ftc.teamcode.Core.Slide;
//
//public class Scorer
//{
//    public Slide slides;
//    public Intake intake;
//
//
////    public Scorer(HardwareMap map) // initialization
////    {
////        this.slides = new Slide(map, "Slides", 1540);
////        this.intake = new Intake(map, "wrist", "claw1", "claw2");
//////        this.intake.openClaw();
//////        this.intake.setWristLow();
////        this.intake.closeClaw();
////    }
//
////    public void score(Driver driver) // scoring code
////    {
////        intake.closeClaw();
////        wait(500);
////        slides.extend();
////        wait(1500);
////        intake.setWristHigh();
////        wait(500);
////        intake.openClaw();
////        wait(500);
////        driver.forward_tiles(-0.1);
////        wait(500);
////        slides.reset();
////        wait(2000);
////    }
//
//    public void load() // loading pixel code
//    {
////        intake.setWristLow();
////        wait(500);
////        intake.openClaw();
////        wait(500);
////        intake.closeClaw();
////        wait(500);
//        intake.setWristHigh();
//    }
//
//    public void wait(int milliseconds)
//    {
//        try
//        {
//            Thread.sleep(milliseconds);
//        } catch (InterruptedException ignored) {}
//    }
//
//}
