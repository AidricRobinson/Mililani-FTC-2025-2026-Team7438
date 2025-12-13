package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class StorageSubsystem {
    DcMotorEx storageMotor;

    public StorageSubsystem(OpMode opMode) {
        storageMotor = opMode.hardwareMap.get(DcMotorEx.class, "storageMotor");
        storageMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        storageMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        storageMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }
    public void setStoragePower (double power){
        storageMotor.setPower(power);
    }
    public void setStorageEncoder(int targetCounts, String Direction){
        storageMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        if (Direction == "FOWARD"){
            storageMotor.setTargetPosition(targetCounts);
        }
        else if (Direction == "BACKWARD"){
            storageMotor.setTargetPosition(-targetCounts);
        }
        storageMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public double getFlywheelEncoder(){
        return storageMotor.getCurrentPosition();
    }
    public void shutdown (){
        storageMotor.setPower(0);
    }
    public boolean isBusyCheck() {
        boolean isBusy = true;
        if (storageMotor.isBusy() == true){
        }
        else {
            isBusy = false;
        }
        return isBusy;
    }
}








