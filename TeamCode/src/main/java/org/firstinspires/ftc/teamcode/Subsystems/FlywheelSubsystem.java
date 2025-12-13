package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class FlywheelSubsystem {
    DcMotorEx rightFlywheelMotor;
    DcMotorEx leftFlywheelMotor;

    public FlywheelSubsystem(OpMode opMode) {
        rightFlywheelMotor = opMode.hardwareMap.get(DcMotorEx.class, "rightFlywheel");
        leftFlywheelMotor = opMode.hardwareMap.get(DcMotorEx.class, "leftFlywheel");

        rightFlywheelMotor.setDirection(DcMotorEx.Direction.FORWARD);
        leftFlywheelMotor.setDirection(DcMotorEx.Direction.FORWARD);

        rightFlywheelMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        leftFlywheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightFlywheelMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        leftFlywheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setFlywheelPower(double power) {
        rightFlywheelMotor.setPower(power);
        leftFlywheelMotor.setPower(power);
    }
    public double getFlywheelEncoder(){
        return rightFlywheelMotor.getCurrentPosition();
    }
    public void setFlywheelVelocity(double rpm){
        rightFlywheelMotor.setVelocity(rpm);
    }
    public double getFlywheelVelocity(){
        return rightFlywheelMotor.getVelocity();
    }
    public void shutdown() {
        rightFlywheelMotor.setPower(0);
    }

    public boolean isBusyCheck() {
        boolean isBusy = true;
        if (rightFlywheelMotor.isBusy() == true && leftFlywheelMotor.isBusy() == true) {
        } else {
            isBusy = false;
        }
        return isBusy;
    }
}
