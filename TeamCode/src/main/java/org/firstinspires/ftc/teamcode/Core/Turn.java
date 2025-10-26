package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.Servo;

public class Turn {
    private final Servo turn;

    public Turn(Servo turn) {
        this.turn = turn;
    }

    public void init() {
        turn.setDirection(Servo.Direction.FORWARD);
    }

    public void update(float leftStickY) {
        if (leftStickY > 0) {
            turn.setPosition(-1.0);
        } else if (leftStickY < 0) {
            turn.setPosition(1.0);
        } else {
            turn.setPosition(0.0);
        }
    }
}
