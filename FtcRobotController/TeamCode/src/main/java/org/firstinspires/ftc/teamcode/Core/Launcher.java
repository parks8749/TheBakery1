//package org.firstinspires.ftc.teamcode.Core;
//
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
//
//public class Launcher
//{
//    //the final might break something
//    private final Servo launcher;
//
//    private State launcherPosition = State.INACTIVE;
//
//    private final ServoToggle launcherToggle;
//
//    public Launcher(HardwareMap map, String launcherName)
//    {
//        launcher = map.get(Servo.class, launcherName);
//        launcherToggle = new ServoToggle(this::toggleLauncherPosition);
//    }
//    public void toggleLauncherPosition()
//     {
//        if (launcherPosition == State.INACTIVE)
//            closeLauncher();
//        else
//            openLauncher();
//    }
//    public void openLauncher()
//    {
//        launcherPosition = State.INACTIVE;
//        double launcherHigh = 0.8;
//        launcher.setPosition(launcherHigh);
//    }
//    public void closeLauncher()
//    {
//        launcherPosition = State.ACTIVE;
//        double launcherLow = 0.2;  //maybe 0.25
//        launcher.setPosition(launcherLow);
//    }
//    public void update(boolean buttonState)
//    {
//        launcherToggle.update(buttonState);
//    }
//}
//// for launcher the higher the number, the more right it goes