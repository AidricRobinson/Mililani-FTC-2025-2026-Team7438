package org.firstinspires.ftc.teamcode.Commands.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.StorageSubsystem;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Constants;

public class EncoderStorageCommand {
    StorageSubsystem storageSubsystem;
    Telemetry telemetry;
    ElapsedTime elapsedTimed;
    double startTime;
    public EncoderStorageCommand(StorageSubsystem storageSubsystem, Telemetry telemetry, ElapsedTime elapsedTime){
        this.storageSubsystem = storageSubsystem;
        this.elapsedTimed = elapsedTime;

    }
    public void EncoderStorageOperate(){
        startTime = elapsedTimed.milliseconds();
        while (elapsedTimed.milliseconds() < startTime + 2000){
            double powerOutput = 0.85;
            storageSubsystem.setStoragePower(powerOutput);
        }
    }
    public void shutdown(){
        storageSubsystem.shutdown();
    }
}
