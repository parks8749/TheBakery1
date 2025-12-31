package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Vision.Limelight3AHelper;

@Autonomous(name = "BlueAutoSelector", group = "Autonomous")
public class BlueAutoSelector extends LinearOpMode {

    private Driver driver;

    public CRServo backBottom;
    public CRServo backIntake;
    public CRServo launcherWheel;
    public DcMotor leftFlyWheel;
    public DcMotor rightFlyWheel;
    public CRServo belts;
    public CRServo topFront;

    private final Limelight3AHelper llHelper = new Limelight3AHelper();
    private int cachedTagId = -1;

    @Override
    public void runOpMode() throws InterruptedException {

        // Construct Driver with this LinearOpMode and the hardware map so
        // Driver can use opMode checks and telemetry inside its wait loops.
        driver = new Driver(this, hardwareMap);

        // Initialize non-drive hardware (flywheels, servos)
        initHardware(hardwareMap);

        try {
            llHelper.init(hardwareMap);
        } catch (Exception e) {
            telemetry.addData("Limelight", "Init failed: " + e.getMessage());
        }

        telemetry.addData("Status", "Initialized - Scanning for AprilTag...");
        telemetry.update();

        ElapsedTime timer = new ElapsedTime();

        while (!isStarted() && !isStopRequested()) {
            if (timer.milliseconds() > 100) {
                llHelper.update();

                if (llHelper.hasValidTarget()) {
                    int id = llHelper.getBestFiducialId();
                    telemetry.addData("Detected Tag", id);
                    cachedTagId = id >= 0 ? id : cachedTagId;
                } else {
                    telemetry.addData("Detected Tag", "none");
                }
                telemetry.update();
                timer.reset();
            }
            idle();
        }

        if (isStopRequested()) return;

        telemetry.clearAll();
        telemetry.addData("Selected Tag", cachedTagId);
        telemetry.update();

        // Use Driver's encoder-based routines for autonomous behaviors
        switch (cachedTagId) {
            case 21: // G P P
                runAutoForTag21();
                break;
            case 22: // P G P
                runAutoForTag22();
                break;
            case 23: // P P G
                runAutoForTag23();
                break;
            default:
                runDefaultAuto();
                break;
        }
    }

    private void initHardware(HardwareMap hardwareMap) {

        backBottom   = hardwareMap.get(CRServo.class, "BackBottom");
        backIntake   = hardwareMap.get(CRServo.class, "BackIntake");
        launcherWheel= hardwareMap.get(CRServo.class, "LauncherWheel");
        leftFlyWheel = hardwareMap.get(DcMotor.class, "leftFly");
        rightFlyWheel= hardwareMap.get(DcMotor.class, "rightFly");
        belts        = hardwareMap.get(CRServo.class, "RightBelt");
        topFront     = hardwareMap.get(CRServo.class, "TopFront");

        // flywheel directions (keep as you had)
        leftFlyWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFlyWheel.setDirection(DcMotorSimple.Direction.FORWARD);

        // reset + enable encoders for flywheels if supported (defensive)
        try {
            leftFlyWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightFlyWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        } catch (Exception ignored) {}

        leftFlyWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFlyWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFlyWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFlyWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // default powers
        leftFlyWheel.setPower(0);
        rightFlyWheel.setPower(0);
        backBottom.setPower(0);
        backIntake.setPower(0);
        launcherWheel.setPower(0);
        belts.setPower(0);
        topFront.setPower(0);
    }

    private void runAutoForTag21() throws InterruptedException {
        telemetry.addData("Auto", "Running Tag 21 (G P P)");
        telemetry.update();

        // Example: drive forward 1 tile, strafe right 0.5, park
        if (opModeIsActive()) {
            // GPP auto
        }
    }

    private void runAutoForTag22() throws InterruptedException {
        telemetry.addData("Auto", "Running Tag 22 (P G P)");
        telemetry.update();

        if (opModeIsActive()) {
            // PGP auto
        }
    }

    private void runAutoForTag23() throws InterruptedException {
        telemetry.addData("Auto", "Running Tag 23 (P P G)");
        telemetry.update();

        if (opModeIsActive()) {
            // PPG auto
        }
    }

    private void runDefaultAuto() throws InterruptedException {
        telemetry.addData("Auto", "Running default fallback (encoder-based)");
        telemetry.update();

        if (opModeIsActive()) {
            driver.strafe_tiles(1.0);
        }
    }
}
