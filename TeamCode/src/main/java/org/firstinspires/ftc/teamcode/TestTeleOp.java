package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.Manual.CameraFlywheelCommand;
import org.firstinspires.ftc.teamcode.Commands.Manual.FlywheelCommand;
import org.firstinspires.ftc.teamcode.Commands.Manual.LimelightCamera;
import org.firstinspires.ftc.teamcode.Commands.Manual.StorageCommand;
import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.StorageSubsystem;


@TeleOp(name="TEST ONLY")

public class TestTeleOp extends OpMode {
    //declaring subsystems and commands here
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private FlywheelSubsystem flywheelSubsystem;
    private CameraFlywheelCommand flywheelCommand;
    private StorageSubsystem storageSubsystem;
    private StorageCommand storageCommand;
    private Limelight3A limelight;
    private LimelightCamera limelightCamera;

    //When you 8 initialize
    public void init () {
        flywheelSubsystem = new FlywheelSubsystem(this);
        flywheelCommand = new CameraFlywheelCommand(flywheelSubsystem, gamepad1, new PIDController(0.001, 0,0,0.05), limelight, telemetry);
        storageSubsystem = new StorageSubsystem(this);
        storageCommand = new StorageCommand(storageSubsystem, gamepad1, gamepad1);
        mecanumDriveSubsystem = new MecanumDriveSubsystem(this.hardwareMap,this);

        limelight = hardwareMap.get(Limelight3A.class, "7438limelight");
        limelight.setPollRateHz(60);
//        limelight.pipelineSwitch(1); //You configure the pipeline in the http://limelight.local:5801/, make sure to press the button on top middle screen, change ID filter
        limelight.start();
        limelightCamera = new LimelightCamera(mecanumDriveSubsystem, limelight, gamepad1, telemetry);


    }

    @Override
    public void loop(){

        flywheelCommand.operate(gamepad1);
        storageCommand.operate(gamepad1, gamepad2);
        mecanumDriveSubsystem.operate(gamepad1, telemetry);
        limelightCamera.operate(gamepad1);
    }
    public void stop(){
        flywheelCommand.shutdown();
        storageCommand.shutdown();
        mecanumDriveSubsystem.shutdown();
        limelightCamera.shutdown();
    }
}