package org.firstinspires.ftc.teamcode.Commands.Manual;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.Subsystems.*;
public class StorageCommand {
    StorageSubsystem storageSubsystem;
    Gamepad gamepad;
    Gamepad gamepad2;
    public StorageCommand(StorageSubsystem storageSubsystem, Gamepad gamepad, Gamepad gamepad2) {
        this.storageSubsystem = storageSubsystem;
        this.gamepad = gamepad;
        this.gamepad2 = gamepad2;
    }
    public void operate(Gamepad gamepad, Gamepad gamepad2) {
            if (gamepad.left_trigger > 0.2 || gamepad2.left_trigger > 0.2) {
                storageSubsystem.setStoragePower(0.8);
            }
            else if(gamepad.left_bumper || gamepad2.left_bumper) {
                storageSubsystem.setStoragePower(-0.5);
            }
            else {
                storageSubsystem.setStoragePower(0);
            }
    }
    public void shutdown(){
        storageSubsystem.shutdown();
    }
}