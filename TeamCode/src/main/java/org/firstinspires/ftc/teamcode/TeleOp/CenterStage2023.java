package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Core.DriveTrain;
import org.firstinspires.ftc.teamcode.Core.LinearSlides;


@TeleOp(name="CenterStage2023", group="TeleOp")
public class CenterStage2023 extends LinearOpMode {
    private DriveTrain driveTrain;

    private Servo scoop;
    private final double SCOOP_DOWN_POSITION = 1.0;
    private final double SCOOP_UP_POSITION = 0.4;
    private Servo turn;
    private Servo arm;
    private final double ARM_DOWN_POSITION = 1.0; // new value for new arm (up position)
    private final double ARM_UP_POSITION = 0.5; // new value for new arm (down position)
    private DcMotor slide;
    private Servo claw;
    private final double CLAW_OPEN_POSITION = 0.0;  // May adjust
    private final double CLAW_CLOSED_POSITION = 0.4; // May adjust
//    private boolean isClawOpen = true;
//    private LinearSlides linearSlides;



    // Define the maximum and minimum encoder positions for the slide
//    private final int SLIDE_MAX_POSITION = 5000; // Adjust based on the max height of your slide
//    private final int SLIDE_MIN_POSITION = 0;

    @Override
    public void runOpMode()
    {
        scoop = hardwareMap.get(Servo.class, "scoop");
        arm = hardwareMap.get(Servo.class, "arm");
        turn = hardwareMap.get(Servo.class, "turn");
        slide = hardwareMap.get(DcMotor.class, "slide");
        claw = hardwareMap.get(Servo.class, "claw");

//        // Set motor direction and zero power behavior
//        slide.setDirection(DcMotor.Direction.FORWARD);
//        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        // Reset encoder position and set to run using encoder
//        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        slide = hardwareMap.get(DcMotor.class, "slide");

        // Set the motor to run without encoders
        slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Initialized");
        driveTrain = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");
//
//        LinearSlides linearSlides = new LinearSlides(hardwareMap, "Slides", linearSlidesPosition);
//
//        Intake intake = new Intake(hardwareMap, "wrist", "claw1", "claw2");
//
////        HookMechanism hookMechanism = new HookMechanism(hardwareMap, "hanging", "hook");
//
//        Launcher launcher = new Launcher(hardwareMap, "launcher");
//
        telemetry.addData("Status", "Initialized");
        telemetry.update();


//        claw.setPosition(CLAW_OPEN_POSITION);


        waitForStart();
        boolean isClawOpen = false;
        boolean xPreviouslyPressed = false;

        boolean isArmUp = false;
        boolean bPreviouslyPressed = false;

        boolean isScoopUp = false;
        boolean aPreviouslyPressed = false;

        while (opModeIsActive())
        {


//CLAW CODE (X)
            boolean xPressed = gamepad2.x;

            if (xPressed && !xPreviouslyPressed) {
                if (isClawOpen) {
                    claw.setPosition(CLAW_CLOSED_POSITION);
                } else {
                    claw.setPosition(CLAW_OPEN_POSITION);
                }
                isClawOpen = !isClawOpen;
            }
            xPreviouslyPressed = xPressed;

            telemetry.addData("Claw Position", isClawOpen ? "Open" : "Closed");
            telemetry.update();

// ARM CODE (B)
            boolean bPressed = gamepad2.b;
            arm.setDirection(Servo.Direction.FORWARD);
            if (bPressed && !bPreviouslyPressed) {
                if (isArmUp) {
                    arm.setPosition(ARM_DOWN_POSITION);
                }
                else {
                    arm.setPosition(ARM_UP_POSITION);
                }
                isArmUp = !isArmUp;
            }
            bPreviouslyPressed = bPressed;

            telemetry.addData("Servo Position", arm.getPosition());
            telemetry.update();
            telemetry.clear();


// SCOOP CODE (A)
            boolean aPressed = gamepad2.a;
            scoop.setDirection(Servo.Direction.FORWARD);
            if (aPressed && !aPreviouslyPressed) { // goes counter-clockwise.
                if (isScoopUp) {
                    scoop.setPosition(SCOOP_DOWN_POSITION);
                }
                else {
                    scoop.setPosition(SCOOP_UP_POSITION);
                }
                isScoopUp = !isScoopUp;
            }
            aPreviouslyPressed = aPressed;

            telemetry.addData("Servo Position", scoop.getPosition());
            telemetry.update();
            telemetry.clear();


// SLIDE CODE(TRIGGERS)
            if (gamepad2.right_trigger > 0.1) {  // Move slides up when right trigger is pressed
                slide.setPower(gamepad2.right_trigger);
            } else if (gamepad2.left_trigger > 0.1) {  // Move slides down when left trigger is pressed
                slide.setPower(-gamepad2.left_trigger);
            } else {
                slide.setPower(0);
            }
            telemetry.addData("Slide Motor Power", slide.getPower());
            telemetry.update();

//            slide.setPower(power);
//
//            telemetry.addData("Slide Position", currentPosition);
//            telemetry.addData("Motor Power", slide.getPower());
//            telemetry.addData("Left Trigger Value", gamepad2.left_trigger);
//            telemetry.addData("Right Trigger Value", gamepad2.right_trigger);
//            telemetry.update();




// TURN CODE(RIGHT JOYSTICK)
            turn.setDirection(Servo.Direction.FORWARD); // or REVERSE
            // Control the continuous servo based on gamepad input
            if (gamepad2.left_stick_y > 0) {
                // Full speed clockwise
                turn.setPosition(-1.0);
            } else if (gamepad2.left_stick_y < 0) {
                // Full speed counter-clockwise
//                double reducedSpeed = gamepad2.left_stick_y * 0.5; // Adjust the factor as needed (e.g., 0.5 for 50% speed)
//                turn.setPosition(reducedSpeed);
                turn.setPosition(1.0);

                // Add telemetry or other controls as needed
                telemetry.update();
            }








            telemetry.addData("fR Pos", String.valueOf(driveTrain.MotorfR.getPower()));
            telemetry.addData("fL Pos", String.valueOf(driveTrain.MotorfL.getPower()));
            telemetry.addData("bR Pos", String.valueOf(driveTrain.MotorbR.getPower()));
            telemetry.addData("bL Pos", String.valueOf(driveTrain.MotorbL.getPower()));

            telemetry.update();

            driveTrain.Drive(gamepad1);
        }
    }

}