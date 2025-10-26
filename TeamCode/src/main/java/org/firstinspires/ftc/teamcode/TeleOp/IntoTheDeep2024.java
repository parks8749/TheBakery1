package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Core.DriveTrain;


@TeleOp(name="IntoTheDeep2024", group="TeleOp")
public class IntoTheDeep2024 extends LinearOpMode {
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
    private final double CLAW_OPEN_POSITION = 0.15;  // May adjust
    private final double CLAW_CLOSED_POSITION = 0.45; // May adjust
//    private boolean isClawOpen = true;



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
        slide = hardwareMap.get(DcMotor.class, "slide");


// For using encoders for linear slides:
//        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        slide.setDirection(DcMotor.Direction.FORWARD);


        slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Initialized");
        driveTrain = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");
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
            int slidePosition = slide.getCurrentPosition();
            if (gamepad2.right_trigger > 0.1) {
                slide.setPower(-gamepad2.right_trigger);
            } else if (gamepad2.left_trigger > 0.1) {
                slide.setPower(gamepad2.left_trigger);
            } else {
                slide.setPower(0);
            }

            telemetry.addData("Slide Position", slidePosition);
            telemetry.addData("Slide Motor Power", slide.getPower());

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