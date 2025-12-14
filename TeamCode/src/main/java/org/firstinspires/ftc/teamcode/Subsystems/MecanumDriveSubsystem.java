package org.firstinspires.ftc.teamcode.Subsystems;

import android.graphics.Path;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.onbotjava.handlers.file.NewFile;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;


public class MecanumDriveSubsystem {
    //Make 4 motors (the four corne`rs)
    private final BNO055IMU imu;
//    BHI260AP imu;
    DcMotorEx leftFront;
    DcMotorEx leftBack;
    DcMotorEx rightFront;
    DcMotorEx rightBack;
    boolean toggle;

//    DcMotorEx xThroughbore;
//    DcMotorEx yThroughbore;
    private boolean slowModeOn;
   public MecanumDriveSubsystem(HardwareMap hardwareMap, OpMode opMode){
       leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
       leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
       rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
       rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");

       imu = opMode.hardwareMap.get(BNO055IMU.class, "imu");
       BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
       imu.initialize(parameters);
       toggle = false;

//       xThroughbore = hardwareMap.get(DcMotorEx.class, "xThroughbore");
//       yThroughbore = hardwareMap.get(DcMotorEx.class, "yThroughbore");



       leftFront.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
       leftBack.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
       rightFront.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
       rightBack.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

       leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

       leftFront.setDirection(DcMotorEx.Direction.REVERSE);
       leftBack.setDirection(DcMotorEx.Direction.FORWARD);
       rightFront.setDirection(DcMotorEx.Direction.REVERSE);
       rightBack.setDirection(DcMotorEx.Direction.FORWARD);

   }
    public void operate(Gamepad gamepad, Telemetry telemetry) {


        double y =  gamepad.left_stick_y;
        double x = gamepad.left_stick_x;
        double rx = gamepad.right_stick_x;


        double botHeading = getYawRadians();
        double rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
        double rotY = x * Math.sin(botHeading) + y * Math.cos(botHeading);
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);

        if(gamepad.x && toggle){
            toggle = false;
        }
        else if(gamepad.x){
            toggle = true;
        }
        if (gamepad.right_trigger > 0.2) {

            leftFront.setPower((-rotY+rotX+rx) / denominator * 0.5);
            leftBack.setPower((-rotY-rotX+rx) / denominator * 0.5);
            rightFront.setPower((-rotY-rotX-rx) / denominator * 0.5);
            rightBack.setPower((-rotY+rotX-rx) / denominator * 0.5);
        }
        else {
            leftFront.setPower((-rotY+rotX+rx) / denominator);
            leftBack.setPower((-rotY-rotX+rx) / denominator);
            rightFront.setPower((-rotY-rotX-rx) / denominator);
            rightBack.setPower((-rotY+rotX-rx)/ denominator);
        }

        telemetry.addData("Slow mode", slowModeOn);
        telemetry.update();

    }

    public void setLeftBackPower(double power){
       leftBack.setPower(power);
    }
    public void setRightBackPower(double power){
       rightBack.setPower(power);
    }
    public void setLeftFrontPower(double power){
        leftFront.setPower(power);
    }
    public void setRightFrontPower(double power){
        rightFront.setPower(power);
    }

public double[] encoderReading () {
    double[] encoderReading = new double[4];
    encoderReading[0] = leftFront.getCurrentPosition();
    encoderReading[1] = leftBack.getCurrentPosition();
    encoderReading[2] = rightFront.getCurrentPosition();
    encoderReading[3] = rightBack.getCurrentPosition();

    return encoderReading;
}
public double getYaw(){
       return imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;
    }
    public double getYawRadians(){
       return imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS).thirdAngle;
    }

    public void shutdown() {
       leftFront.setPower(0);
       leftBack.setPower(0);
       rightFront.setPower(0);
       rightBack.setPower(0);
    }

}
