package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;

public class AutoAlignmentCommand {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private LLResult result;
    private Limelight3A limelight;


    public AutoAlignmentCommand(MecanumDriveSubsystem mecanumDriveSubsystem, Limelight3A limelight){
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.limelight = limelight;
    }
    public void operate() {
            result = limelight.getLatestResult();
            double tx = 0;
            if (result != null && result.isValid()) {
                tx = result.getTx(); // How far left or right the target is (degrees)
            } else {
                tx = 20;
            }
            while(Math.abs(tx) > 0.5) {
                result = limelight.getLatestResult();
                tx = 0;
                if (result != null && result.isValid()) {
                    tx = result.getTx(); // How far left or right the target is (degrees)
                } else {
                    tx = 0;
                }
                double kp = 0.7;
                double KFF = 0.05;
                double output = kp * (tx) + KFF;

                mecanumDriveSubsystem.setLeftFrontPower(output);
                mecanumDriveSubsystem.setLeftBackPower(output);
                mecanumDriveSubsystem.setRightBackPower(-output);
                mecanumDriveSubsystem.setRightFrontPower(-output);
                //Renewing the tx
                result = limelight.getLatestResult();
                if (result != null && result.isValid()) {
                    tx = result.getTx(); // How far left or right the target is (degrees)
                } else {
                    tx = 0;
                }
            }
            mecanumDriveSubsystem.shutdown();


    }

    public void shutdown(){
        mecanumDriveSubsystem.shutdown();
    }
}


