package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderDriveCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderFlywheelCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderStorageCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderStrafingCommand;
import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderTurningCommand;
import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.StorageSubsystem;


@Autonomous(name= "BlueAutoPath1", group= "Linear OpMode")
public class BlueAutoPath1 extends LinearOpMode{
    private FlywheelSubsystem flywheelSubsystem;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private StorageSubsystem storageSubsystem;

    private EncoderStorageCommand encoderStorageCommand;
    private EncoderDriveCommand encoderDriveCommand;
    private EncoderFlywheelCommand encoderFlywheelCommand;
    private EncoderStrafingCommand encoderStrafingCommand;
    private EncoderTurningCommand encoderTurningCommand;

    private ElapsedTime elapsedTime;

    @Override
    public void runOpMode(){
        elapsedTime = new ElapsedTime();

        flywheelSubsystem = new FlywheelSubsystem(this);
        mecanumDriveSubsystem = new MecanumDriveSubsystem(this.hardwareMap,this);
        storageSubsystem = new StorageSubsystem(this);

        encoderStorageCommand = new EncoderStorageCommand(storageSubsystem,telemetry, elapsedTime);
        encoderDriveCommand = new EncoderDriveCommand(mecanumDriveSubsystem, telemetry, this);
        encoderFlywheelCommand = new EncoderFlywheelCommand(flywheelSubsystem, telemetry, elapsedTime);
        encoderStrafingCommand = new EncoderStrafingCommand(mecanumDriveSubsystem, telemetry, this);
        encoderTurningCommand = new EncoderTurningCommand(mecanumDriveSubsystem, telemetry, this);

        waitForStart();
//        encoderDriveCommand.EncoderDriveOperate(700, "FORWARD");
//        encoderTurningCommand.EnocderTurningOperate(30,"RIGHT");
//        encoderDriveCommand.EncoderDriveOperate(-90, "BACKWARD");
        encoderFlywheelCommand.EncoderFlywheelOperate(0.95);
        encoderStorageCommand.EncoderStorageOperate(0.25);
        sleep(1000);
        //reload
        encoderStorageCommand.shutdown();
//        encoderTurningCommand.EnocderTurningOperate(60, "LEFT");
        sleep(1000);
//        encoderTurningCommand.EnocderTurningOperate(120, "RIGHT");
//        sleep(10);
//        encoderTurningCommand.EnocderTurningOperate(60, "LEFT");
        encoderStorageCommand.EncoderStorageOperate(0.25);
        sleep(1000);
        //reload
        encoderStorageCommand.shutdown();
//        encoderTurningCommand.EnocderTurningOperate(60, "RIGHT");
        sleep(1000);
//        encoderTurningCommand.EnocderTurningOperate(120, "LEFT");
//        sleep(10);
//        encoderTurningCommand.EnocderTurningOperate(60, "RIGHT");
        encoderStorageCommand.EncoderStorageOperate(0.25);
        encoderFlywheelCommand.shutdown();
        encoderStorageCommand.shutdown();
        encoderDriveCommand.EncoderDriveOperate(100, "FORWARD");
        encoderTurningCommand.EnocderTurningOperate(70, "RIGHT");
//        encoderDriveCommand.EncoderDriveOperate(50,"FORWARD");
//        encoderFlywheelCommand.EncoderFlywheelOperate(1);
//        encoderStorageCommand.EncoderStorageOperate();
// Hii!!! :D
    }
}
