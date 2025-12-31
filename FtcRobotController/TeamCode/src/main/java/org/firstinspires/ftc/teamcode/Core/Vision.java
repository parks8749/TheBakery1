//package org.firstinspires.ftc.teamcode.Core;
//
//
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.vision.VisionPortal;
//import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
////import org.firstinspires.ftc.vision.tfod.TfodProcessor;
//
//public class Vision
//{
//    AprilTagProcessor myAprilTagProcessor;
////    TfodProcessor myTfodProcessor;
//    VisionPortal myVisionPortal;
//    CameraName cameraName;
//
//
//// Create the AprilTag processor and assign it to a variable.
//
//    public Vision(HardwareMap map, String webcamName)
//    {
//        myAprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
////        myTfodProcessor = TfodProcessor.easyCreateWithDefaults();
//        cameraName = map.get(WebcamName.class, webcamName);
//        myVisionPortal = VisionPortal.easyCreateWithDefaults(cameraName, myAprilTagProcessor, myTfodProcessor);
//    }
//}
