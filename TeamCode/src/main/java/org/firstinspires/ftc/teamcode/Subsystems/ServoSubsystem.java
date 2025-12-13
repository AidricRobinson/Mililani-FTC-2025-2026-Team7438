package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoSubsystem {
    Servo servo;

    public ServoSubsystem(OpMode opMode){
        servo = opMode.hardwareMap.get(Servo.class, "leg");

    }
    public void setServoPosition(double position){
        servo.setPosition(position);
    }
    public void shutdown(){
        servo.setPosition(0);
    }
}
