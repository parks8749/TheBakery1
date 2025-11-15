package org.firstinspires.ftc.teamcode.Core;//package org.firstinspires.ftc.teamcode.Core;
//
//import com.qualcomm.robotcore.hardware.DcMotor;
//
//public class Slide {
//    private final DcMotor slide;
//    // (Optional) you could reintroduce encoder limits here:
//    // public static final int SLIDE_MAX_POSITION = 5000;
//    // public static final int SLIDE_MIN_POSITION = 0;
//
//    public Slide(DcMotor slide) {
//        this.slide = slide;
//        this.slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//    }
//
//    public void init() {
//        // reset encoders / zero-power-behavior if desired
//    }
//
//    public void update(double rightTrigger, double leftTrigger) {
//        if (rightTrigger > 0.1) {
//            slide.setPower(-rightTrigger);
//        } else if (leftTrigger > 0.1) {
//            slide.setPower(leftTrigger);
//        } else {
//            slide.setPower(0);
//        }
//    }
//
//    public int getPosition() {
//        return slide.getCurrentPosition();
//    }
//}
