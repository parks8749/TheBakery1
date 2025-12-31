//package org.firstinspires.ftc.teamcode.Core;
//
//public class ServoToggle
//{
//    private boolean lastButtonState = false;
//
//    public interface ToggleAction
//    {
//        void execute();
//    }
//
//    private final ToggleAction action;
//
//    public ServoToggle(ToggleAction action)
//    {
//        this.action = action;
//    }
//
//    public void update(boolean currentButtonState)
//    {
//        if (currentButtonState && !lastButtonState)
//        {
//            action.execute();
//        }
//        lastButtonState = currentButtonState;
//    }
//}