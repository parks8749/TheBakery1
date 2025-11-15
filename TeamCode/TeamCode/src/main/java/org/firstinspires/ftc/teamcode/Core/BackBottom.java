//package org.firstinspires.ftc.teamcode.Core;
//
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//
//
//public class BackBottom {
//
//    private final CRServo backBottom;
//    private static final double POWER = 1.0;
//    private boolean toggled = false;
//    private boolean lastAPressed = false;
//
//    public BackBottom(CRServo backRoller) {
//        this.backBottom = backRoller;
//    }
//
//    public void init() {
//        backBottom.setDirection(DcMotorSimple.Direction.REVERSE);
//    }
//
//    public void update(boolean aPressed) {
//        if (aPressed && !lastAPressed) {
//            toggled = !toggled;
//        }
//        lastAPressed = aPressed;
//
//        // set power based on toggled state
//        backBottom.setPower(toggled ? POWER : 0.0);
////        double p = aPressed ? POWER : 0.0;
////        backBottom.setPower(p);
////        if (aPressed) {
////            backBottom.setPower(-1.0);
////        } else {
////            backBottom.setPower(0.0);
////        }
//    }
//    public void stop() {
//        toggled = false;
//        backBottom.setPower(0.0);
//    }
//}


package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class BackBottom {

    private final CRServo backBottom;
    private static final double POWER = 1.0;
    private static final float DEADZONE = 0.08f;

    public BackBottom(CRServo backRoller) {
        this.backBottom = backRoller;
    }

    public void init() {
        // keep this direction; change if your wiring requires it
        backBottom.setDirection(DcMotorSimple.Direction.REVERSE);
        backBottom.setPower(0.0);
    }

    /**
     * Priority rules:
     * 1) If leftStickY is being used (outside DEADZONE) -> mirror BackIntake behavior.
     * 2) Otherwise, follow beltsMode:
     *      mode 1 -> -POWER (forward mapping used previously)
     *      mode 2 -> +POWER (reverse)
     *      mode 0 -> 0.0
     *
     * @param beltsMode from belts.getMode() (0=off,1=forward,2=reverse)
     * @param leftStickY raw left stick Y (not deadzoned by caller; we check DEADZONE here)
     */
    public void update(int beltsMode, float leftStickY) {
        // 1) left-stick overrides: mirror BackIntake mapping
        if (Math.abs(leftStickY) >= DEADZONE) {
            // BackIntake uses: leftStickY > 0 ? -POWER : POWER
            backBottom.setPower(leftStickY > 0 ? POWER : -POWER);
            return;
        }

        // 2) left-stick neutral => follow belts mode
        switch (beltsMode) {
            case 1: // belts forward -> backBottom forward mapping (matches previous behavior)
                backBottom.setPower(-POWER);
                break;
            case 2: // belts reverse -> backBottom reverse
                backBottom.setPower(POWER);
                break;
            default: // off
                backBottom.setPower(0.0);
                break;
        }
    }

    public void stop() {
        backBottom.setPower(0.0);
    }
}
