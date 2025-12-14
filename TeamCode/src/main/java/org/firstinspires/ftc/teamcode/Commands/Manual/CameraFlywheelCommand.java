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
    private Limelight3A limelight;
    private double ta;


    public CameraFlywheelCommand(FlywheelSubsystem flywheelSubsystem, Gamepad gamepad, PIDController pidController, Limelight3A limelight, Telemetry telemetry) {
        this.flywheelSubsystem = flywheelSubsystem;
        this.gamepad = gamepad;
        this.pidController = new PIDController(0.055, 0, 0.0001, 0.2);
        this.limelight = limelight;
        this.telemetry = telemetry;
        ta = 0;
    }

    public void operate(Gamepad gamepad) {
        if(gamepad.right_bumper){
             result = limelight.getLatestResult();
            if(result.isValid() && result != null){
                ta = result.getTa();
            }
            else{
                ta = 0;
            }
            pidController.createSetPoint(2700);
            pidController.setProcessVariable(60 * flywheelSubsystem.getFlywheelVelocity() / 28);
            flywheelSubsystem.setFlywheelPower(pidController.getOutput());
            telemetry.addData("RPM", 60 * flywheelSubsystem.getFlywheelVelocity() / 28);
            telemetry.addData("Actual Ta", ta);
            telemetry.update();
        }
        else if(gamepad.right_trigger > 0.2) {
            result = limelight.getLatestResult();
            if(result.isValid() && result != null){
                ta = result.getTa();
            }
            else{
                ta = 0;
            }
            pidController.createSetPoint(3214);
            pidController.setProcessVariable(60 * flywheelSubsystem.getFlywheelVelocity() / 28);
            flywheelSubsystem.setFlywheelPower(pidController.getOutput());
            telemetry.addData("RPM", 60 * flywheelSubsystem.getFlywheelVelocity() / 28);
            telemetry.addData("Actual Ta", ta);
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

