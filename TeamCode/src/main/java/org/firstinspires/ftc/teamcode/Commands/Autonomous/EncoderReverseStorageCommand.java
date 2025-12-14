package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.StorageSubsystem;

public class EncoderReverseStorageCommand {
    StorageSubsystem storageSubsystem;
    Telemetry telemetry;
    ElapsedTime elapsedTimed;
    double startTime;
    public EncoderReverseStorageCommand(StorageSubsystem storageSubsystem, Telemetry telemetry, ElapsedTime elapsedTime){
        this.storageSubsystem = storageSubsystem;
        this.elapsedTimed = elapsedTime;

    }
    public void operate(){
        startTime = elapsedTimed.milliseconds();
        while (elapsedTimed.milliseconds() < startTime + 5000){
            double powerOutput = -0.5;
            storageSubsystem.setStoragePower(powerOutput);
        }
        storageSubsystem.shutdown();
    }
}
