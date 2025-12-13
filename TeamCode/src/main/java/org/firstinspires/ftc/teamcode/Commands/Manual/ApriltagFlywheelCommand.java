package org.firstinspires.ftc.teamcode.Commands.Manual;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSubsystem;

public class ApriltagFlywheelCommand {
    FlywheelSubsystem flywheelSubsystem;
    Gamepad gamepad;
    Telemetry telemetry;
    PIDController pidController;
    Limelight3A limelight;
    LLResult result;
    double rpm;

    public ApriltagFlywheelCommand(FlywheelSubsystem flywheelSubsystem, Gamepad gamepad, Telemetry telemetry, Limelight3A limelight) {
        this.flywheelSubsystem = flywheelSubsystem;
        pidController = new PIDController(0.0055, 0, 0.0001, 0.2);
        this.gamepad = gamepad;
        this.telemetry = telemetry;
        this.limelight = limelight;
        rpm = 0;
    }

    public void operate(Gamepad gamepad, FlywheelSubsystem flywheelSubsystem) {
        double a = Constants.AprilTagConstants.kFlywheelEquationA;
        double b = Constants.AprilTagConstants.kFlywheelEquationB;
        double c = Constants.AprilTagConstants.kFlywheelEquationC;

        result = limelight.getLatestResult();
        rpm = 0;
        if (result != null && result.isValid()) {
            double x = result.getTa();
            rpm = (a * Math.pow(x, 2)) + (b*x) + (c);
        }
        if (gamepad.right_bumper && result!= null && result.isValid()) {
            pidController.createSetPoint(rpm);
            pidController.setProcessVariable(flywheelSubsystem.getFlywheelVelocity());
            double powerOutput = pidController.getOutput();
            flywheelSubsystem.setFlywheelPower(powerOutput);
        }
        else if (gamepad.right_bumper) {
            pidController.createSetPoint(1125);
            pidController.setProcessVariable(flywheelSubsystem.getFlywheelVelocity());
            double powerOutput = pidController.getOutput();
            flywheelSubsystem.setFlywheelPower(powerOutput);
        }
        else {
            flywheelSubsystem.setFlywheelPower(0);
        }


    }

    public void shutdown(){
        flywheelSubsystem.shutdown();
    }
}