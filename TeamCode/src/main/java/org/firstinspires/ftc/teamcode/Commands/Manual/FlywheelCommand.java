package org.firstinspires.ftc.teamcode.Commands.Manual;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Subsystems.*;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

public class FlywheelCommand {
    private FlywheelSubsystem flywheelSubsystem;
    private Gamepad gamepad;
    private PIDController pidController;
    private LLResult result;
    private Telemetry telemetry;
    private Limelight3A limelight3A;


    public FlywheelCommand(FlywheelSubsystem flywheelSubsystem, Gamepad gamepad, PIDController pidController, Limelight3A limelight3A, Telemetry telemetry) {
        this.flywheelSubsystem = flywheelSubsystem;
        this.gamepad = gamepad;
        this.pidController = new PIDController(0.001,0,0,0.05);
        this.limelight3A = limelight3A;
        this.telemetry = telemetry;
    }

    public void operate(Gamepad gamepad) {
//        if (gamepad.right_bumper) {
//            flywheelSubsystem.setFlywheelPower(0.75);
//        }
//
//        else if(gamepad.right_trigger > 0.2){
//            flywheelSubsystem.setFlywheelPower(0.9);
//       }
//        else {
//            flywheelSubsystem.setFlywheelPower(0);
//        }




        ////////////////////////////////////////////////////////////////////////////////////////////
//         if(gamepad.right_bumper){
//             flywheelSubsystem.setFlywheelVelocity(1000);
//
//         }
//
//        else if(gamepad.right_trigger > 0.2) {
//            result = limelight3A.getLatestResult();
//            double ta = 0;
//            if (result != null && result.isValid()) {
//                ta = result.getTa(); // How far left or right the target is (degrees)
//            }
//            else{
//                ta = 0;
//            }
//            double powerSet = 0;
//            if(ta == 0 || ta == 1){
//                powerSet = 1000;
//            }
//            else{
//                powerSet = (1500 / ta);
//            }
//            pidController.createSetPoint(powerSet);
//            pidController.setProcessVariable(flywheelSubsystem.getFlywheelVelocity());
//            double error = pidController.getError();
//            double output = pidController.getOutput();
//            flywheelSubsystem.setFlywheelPower(output);
//            result = limelight3A.getLatestResult();
//            telemetry.addData("Ta", ta);
//            telemetry.addData("Output", output);
//            telemetry.addData("isValid", result.isValid());
//            telemetry.addData("null", result == null);
//            telemetry.update();
//            flywheelSubsystem.shutdown();
//        }


        ////////////////////////////////////////////////////////////////////////////////////

        if(gamepad.right_bumper){
            pidController.createSetPoint(2500);
            pidController.setProcessVariable(flywheelSubsystem.getFlywheelVelocity());
            flywheelSubsystem.setFlywheelPower(pidController.getOutput());
            telemetry.addData("Output", pidController.getOutput());
            telemetry.update();
        }
        else if(gamepad.right_trigger > 0.2) {
            pidController.createSetPoint(3000);
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

