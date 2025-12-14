package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutoAlignmentCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderDriveCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderFlywheelCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderReverseStorageCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderStorageCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderStrafingCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderTurningCommand;
import org.firstinspires.ftc.teamcode.Commands.Manual.LimelightCamera;
import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.StorageSubsystem;

@Autonomous(name= "ACTUAL RED AUTO BACK", group= "Linear OpMode")
public class ACTUALRedBackAuto extends LinearOpMode {
    private FlywheelSubsystem flywheelSubsystem;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private StorageSubsystem storageSubsystem;

    private EncoderStorageCommand encoderStorageCommand;
    private EncoderDriveCommand encoderDriveCommand;
    private EncoderFlywheelCommand encoderFlywheelCommand;
    private EncoderStrafingCommand encoderStrafingCommand;
    private EncoderTurningCommand encoderTurningCommand;
    private EncoderReverseStorageCommand encoderReverseStorageCommand;
    private AutoAlignmentCommand autoAlignmentCommand;


    private ElapsedTime elapsedTime;
    private Limelight3A limelight;
    private LLResult result;

    @Override
    public void runOpMode() {
        elapsedTime = new ElapsedTime();
        limelight = hardwareMap.get(Limelight3A.class, "7438limelight");
        limelight.setPollRateHz(60);
        limelight.pipelineSwitch(0); //You configure the pipeline in the http://limelight.local:5801/, make sure to press the button on top middle screen, change ID filter
        limelight.start();
        result = limelight.getLatestResult();

        flywheelSubsystem = new FlywheelSubsystem(this);
        mecanumDriveSubsystem = new MecanumDriveSubsystem(this.hardwareMap, this);
        storageSubsystem = new StorageSubsystem(this);

        encoderStorageCommand = new EncoderStorageCommand(storageSubsystem, telemetry, elapsedTime);
        encoderDriveCommand = new EncoderDriveCommand(mecanumDriveSubsystem, telemetry, this);
        encoderFlywheelCommand = new EncoderFlywheelCommand(flywheelSubsystem, telemetry, elapsedTime);
        encoderStrafingCommand = new EncoderStrafingCommand(mecanumDriveSubsystem, telemetry, this);
        encoderTurningCommand = new EncoderTurningCommand(mecanumDriveSubsystem, telemetry, this);
        encoderReverseStorageCommand = new EncoderReverseStorageCommand(storageSubsystem, telemetry,elapsedTime);
        autoAlignmentCommand = new AutoAlignmentCommand(mecanumDriveSubsystem,limelight);
        waitForStart();
/// //////////////////////////////////////////////////////////////////////////////////////////////// 60
        encoderFlywheelCommand.EncoderFlywheelOperate(0.95);
        encoderStorageCommand.EncoderStorageOperate(0.3);
        sleep(4000);
        /// ///////////////////////////////////////////////////////

        encoderTurningCommand.EnocderTurningOperate(80, "RIGHT");
        encoderDriveCommand.EncoderDriveOperate(1000, "FORWARD");
//        encoderDriveCommand.EncoderDriveOperate(-500, "BACKWARD");






//        encoderTurningCommand.EnocderTurningOperate(-90, "LEFT");
//        encoderStorageCommand.EncoderStorageOperate();
//        encoderDriveCommand.EncoderDriveOperate(1000, "FORWARD");
//        storageSubsystem.shutdown();
//        encoderReverseStorageCommand.operate();
//        encoderDriveCommand.EncoderDriveOperate(-1000, "BACKWARD");
//        autoAlignmentCommand.operate();
//        encoderFlywheelCommand.EncoderFlywheelOperate(0.75);
//        encoderStorageCommand.EncoderStorageOperate();
//        sleep(1000);
//////////////////////////////////////////////////////////////////////////////////////////////////////
//// SHUTDOWNS

        flywheelSubsystem.shutdown();
        mecanumDriveSubsystem.shutdown();
        storageSubsystem.shutdown();
    }
}
