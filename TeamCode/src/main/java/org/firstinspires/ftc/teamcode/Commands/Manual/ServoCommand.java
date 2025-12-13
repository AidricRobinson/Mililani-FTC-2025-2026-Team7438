package org.firstinspires.ftc.teamcode.Commands.Manual;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystems.ServoSubsystem;

public class ServoCommand {
    ServoSubsystem servoSubsystem;
    Gamepad gamepad;

    public ServoCommand(ServoSubsystem servoSubsystem, Gamepad gamepad){
        this.servoSubsystem = servoSubsystem;
        this.gamepad = gamepad;
    }

    public void operate(Gamepad gamepad){
        if(gamepad.dpad_right){
            servoSubsystem.setServoPosition(0.65);
        }
        else {
            servoSubsystem.setServoPosition(0);
        }
    }
    public void shutdown(){
        servoSubsystem.shutdown();
    }
}
