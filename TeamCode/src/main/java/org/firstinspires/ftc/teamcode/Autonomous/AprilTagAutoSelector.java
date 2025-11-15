package org.firstinspires.ftc.teamcode.Autonomous;//package org.firstinspires.ftc.teamcode.Autonomous;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.Core.RobotHardware;
//import org.openftc.apriltag.AprilTagDetection;
//import org.openftc.apriltag.AprilTagDetectionPipeline;
//import org.openftc.easyopencv.OpenCvCamera;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//import org.openftc.easyopencv.OpenCvCameraRotation;
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//
//import java.util.List;
//
//@Autonomous(name = "AprilTag Auto Selector", group = "Auto")
//public class AprilTagAutoSelector extends LinearOpMode {
//
//    private OpenCvCamera camera;
//    private AprilTagDetectionPipeline aprilTagPipeline;
//
//    // tag size and intrinsics — tune if you need pose; not required for ID selection
//    private static final double TAG_SIZE_METERS = 0.1524;
//    private static final double FX = 600, FY = 600, CX = 320, CY = 240;
//
//    private Integer detectedTagId = null;
//    private static final long INIT_TIMEOUT_MS = 3000; // 3 sec
//
//    private RobotHardware robot = new RobotHardware();
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        // init robot hardware
//        robot.init(hardwareMap);
//
//        // camera setup
//        int cameraMonitorViewId = hardwareMap.appContext.getResources()
//                .getIdentifier("cameraMonitorViewId", "id",
//                        hardwareMap.appContext.getPackageName());
//
//        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1"); // change if your config name differs
//        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
//
//        aprilTagPipeline = new AprilTagDetectionPipeline(TAG_SIZE_METERS, FX, FY, CX, CY);
//        camera.setPipeline(aprilTagPipeline);
//
//        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
//            @Override public void onOpened() { camera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT); }
//            @Override public void onError(int errorCode) { telemetry.addData("Camera", "open error: " + errorCode); telemetry.update(); }
//        });
//
//        telemetry.addData("Status", "Init: scanning for tags...");
//        telemetry.update();
//
//        long startMs = System.currentTimeMillis();
//        while (!isStarted() && !isStopRequested() && (System.currentTimeMillis() - startMs) < INIT_TIMEOUT_MS) {
//
//            List<AprilTagDetection> detections = aprilTagPipeline.getDetectionsUpdate();
//            if (detections != null && !detections.isEmpty()) {
//                for (AprilTagDetection d : detections) {
//                    detectedTagId = d.id;
//                    telemetry.addData("Detected tag", detectedTagId);
//                    telemetry.update();
//                    break;
//                }
//            } else {
//                telemetry.addData("Detected tag", "none");
//                telemetry.update();
//            }
//
//            sleep(50);
//        }
//
//        // close camera streaming if you want to save CPU (optional)
//        camera.stopStreaming();
//
//        // wait for start
//        waitForStart();
//        if (isStopRequested()) return;
//
//        // choose and run autos based on detectedTagId
//        int id = (detectedTagId == null) ? -1 : detectedTagId;
//        switch (id) {
//            case 1:
//                telemetry.addLine("Running AutoA (tag 1)");
//                telemetry.update();
////                AutoA.run(robot, this);
//                break;
//            case 2:
//                telemetry.addLine("Running AutoB (tag 2)");
//                telemetry.update();
////                AutoB.run(robot, this);
//                break;
//            case 3:
//                telemetry.addLine("Running AutoC (tag 3)");
//                telemetry.update();
////                AutoC.run(robot, this);
//                break;
//            default:
//                telemetry.addLine("Running DEFAULT auto");
//                telemetry.update();
//                // call default or safe routine
////                AutoA.run(robot, this);
//                break;
//        }
//    }
//}