package org.firstinspires.ftc.teamcode.Commands.Manual;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.*;


public class LimelightCamera {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private Gamepad gamepad;
    private LLResult result;
    private Telemetry telemetry;
    private Limelight3A limelight;
    private LLResultTypes.FiducialResult FResult;


    public LimelightCamera(MecanumDriveSubsystem mecanumDriveSubsystem, Limelight3A limelight, Gamepad gamepad, Telemetry telemetry){
        this.gamepad = gamepad;
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.telemetry = telemetry;
        this.limelight = limelight;
    }
    public void operate(Gamepad gamepad) {
        if (gamepad.a) {
            result = limelight.getLatestResult();
            double tx = 0;
            if (result != null && result.isValid()) {
                tx = result.getTx(); // How far left or right the target is (degrees)
            } else {
                tx = 0;
            }
            while(tx > 1) {
                result = limelight.getLatestResult();
                tx = 0;
                if (result != null && result.isValid()) {
                    tx = result.getTx(); // How far left or right the target is (degrees)
                } else {
                    tx = 0;
                }
                double kp = 0.1;
                double KFF = 0.05;
                double output = kp * (tx) + KFF;

                mecanumDriveSubsystem.setLeftFrontPower(output);
                mecanumDriveSubsystem.setLeftBackPower(output);
                mecanumDriveSubsystem.setRightBackPower(-output);
                mecanumDriveSubsystem.setRightFrontPower(-output);
                telemetry.addData("Output", output);
                telemetry.addData("tx", tx);
                telemetry.update();
                //Renewing the tx
                result = limelight.getLatestResult();
                if (result != null && result.isValid()) {
                    tx = result.getTx(); // How far left or right the target is (degrees)
                } else {
                    tx = 0;
                }
            }
        }

    }

    public void shutdown(){
        mecanumDriveSubsystem.shutdown();
    }
}
