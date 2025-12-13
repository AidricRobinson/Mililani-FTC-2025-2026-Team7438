package org.firstinspires.ftc.teamcode.Commands.Manual;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Subsystems.*;

public class CameraFlywheelCommand {
    private FlywheelSubsystem flywheelSubsystem;
    private Gamepad gamepad;
    private PIDController pidController;
    private LLResult result;
    private Telemetry telemetry;
    private Limelight3A limelight3A;


    public CameraFlywheelCommand(FlywheelSubsystem flywheelSubsystem, Gamepad gamepad, PIDController pidController, Limelight3A limelight3A, Telemetry telemetry) {
        this.flywheelSubsystem = flywheelSubsystem;
        this.gamepad = gamepad;
        this.pidController = new PIDController(0.0055, 0, 0.0001, 0.2);
        this.limelight3A = limelight3A;
        this.telemetry = telemetry;
    }

    public void operate(Gamepad gamepad) {
        if(gamepad.right_bumper){
            pidController.createSetPoint(1300);
            pidController.setProcessVariable(flywheelSubsystem.getFlywheelVelocity());
            flywheelSubsystem.setFlywheelPower(pidController.getOutput());
            telemetry.addData("Output", pidController.getOutput());
            telemetry.update();
        }
        else if(gamepad.right_trigger > 0.2) {
            pidController.createSetPoint(1800);
            pidController.setProcessVariable(flywheelSubsystem.getFlywheelVelocity());
            flywheelSubsystem.setFlywheelPower(pidController.getOutput());
            telemetry.addData("Output", pidController.getOutput());
            telemetry.update();
        }
        else {
            flywheelSubsystem.setFlywheelPower(0);
        }
    }
    public void shutdown() {
        //use your shutdown method
        flywheelSubsystem.shutdown();
    }
}

