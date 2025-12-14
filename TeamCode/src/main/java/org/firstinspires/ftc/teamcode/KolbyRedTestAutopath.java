//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.hardware.limelightvision.LLResult;
//import com.qualcomm.hardware.limelightvision.Limelight3A;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.teamcode.Commands.Autonomous.AutoAlignmentCommand;
//import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderDriveCommand;
//import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderFlywheelCommand;
//import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderReverseStorageCommand;
//import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderStorageCommand;
//import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderStrafingCommand;
//import org.firstinspires.ftc.teamcode.Commands.Autonomous.EncoderTurningCommand;
//import org.firstinspires.ftc.teamcode.Commands.Manual.LimelightCamera;
//import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSubsystem;
//import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
//import org.firstinspires.ftc.teamcode.Subsystems.StorageSubsystem;
//
//@Autonomous(name= "Kolby RED auto 2 cycle", group= "Linear OpMode")
//    public class KolbyRedTestAutopath extends LinearOpMode {
//        private FlywheelSubsystem flywheelSubsystem;
//        private MecanumDriveSubsystem mecanumDriveSubsystem;
//        private StorageSubsystem storageSubsystem;
//
//        private EncoderStorageCommand encoderStorageCommand;
//        private EncoderDriveCommand encoderDriveCommand;
//        private EncoderFlywheelCommand encoderFlywheelCommand;
//        private EncoderStrafingCommand encoderStrafingCommand;
//        private EncoderTurningCommand encoderTurningCommand;
//        private EncoderReverseStorageCommand encoderReverseStorageCommand;
//        private AutoAlignmentCommand autoAlignmentCommand;
//
//
//        private ElapsedTime elapsedTime;
//        private Limelight3A limelight;
//        private LLResult result;
//
//        @Override
//        public void runOpMode() {
//            elapsedTime = new ElapsedTime();
//            limelight = hardwareMap.get(Limelight3A.class, "7438limelight");
//            limelight.setPollRateHz(60);
//            limelight.pipelineSwitch(0); //You configure the pipeline in the http://limelight.local:5801/, make sure to press the button on top middle screen, change ID filter
//            limelight.start();
//            result = limelight.getLatestResult();
//
//            flywheelSubsystem = new FlywheelSubsystem(this);
//            mecanumDriveSubsystem = new MecanumDriveSubsystem(this.hardwareMap, this);
//            storageSubsystem = new StorageSubsystem(this);
//
//            encoderStorageCommand = new EncoderStorageCommand(storageSubsystem, telemetry, elapsedTime);
//            encoderDriveCommand = new EncoderDriveCommand(mecanumDriveSubsystem, telemetry, this);
//            encoderFlywheelCommand = new EncoderFlywheelCommand(flywheelSubsystem, telemetry, elapsedTime);
//            encoderStrafingCommand = new EncoderStrafingCommand(mecanumDriveSubsystem, telemetry, this);
//            encoderTurningCommand = new EncoderTurningCommand(mecanumDriveSubsystem, telemetry, this);
//            encoderReverseStorageCommand = new EncoderReverseStorageCommand(storageSubsystem, telemetry,elapsedTime);
//            autoAlignmentCommand = new AutoAlignmentCommand(mecanumDriveSubsystem,limelight);
//            waitForStart();
//
//            encoderDriveCommand.EncoderDriveOperate(1050, "BACKWARD");
//            autoAlignmentCommand.operate();
//
//            encoderFlywheelCommand.EncoderFlywheelOperate(0.5);
//            // END OF FIRST CYCLE
//////////////////////////////////////////////////////////////////////////////////////////////////////
//// START OF SECOND CYCLE
//            encoderDriveCommand.EncoderDriveOperate(600, "BACKWARD");
//            encoderTurningCommand.EnocderTurningOperate(-45, "RIGHT");
//            encoderTurningCommand.EnocderTurningOperate(-0.9, "RIGHT");
//// INTAKE CLOSE SPIKE LINE
//            encoderStorageCommand.EncoderStorageOperate();
//            encoderDriveCommand.EncoderDriveOperate(-1200, "FORWARD");
//            encoderStorageCommand.shutdown();
//            autoAlignmentCommand.operate();
//            encoderDriveCommand.EncoderDriveOperate(1000, "BACKWARD");
//
//            encoderStrafingCommand.EncoderDriveOperate(1, "LEFT");
//
//// SCORE SECOND CYCLE
//
//            encoderTurningCommand.EnocderTurningOperate(45, "LEFT");
//            autoAlignmentCommand.operate();
//
//            encoderFlywheelCommand.EncoderFlywheelOperate(0.5);
//            encoderStorageCommand.EncoderStorageOperate();
//
////// END OF SECOND CYCLE
////////////////////////////////////////////////////////////////////////////////////////////////////////
////// START OF THIRD CYCLE
////            autoTurnCommand.operate(-40, "RIGHT");
////            autoStrafeCommand.operate(-2.4, "RIGHT");
////// INTAKE MIDDLE SPIKE LINE
////            autoIntakeCommand.operate();
////            autoSlowMecanumCommand.operate(-1500, "FORWARD");
////            autoIntakeCommand.shutdown();
////            autoReverseIntakeCommand.operate();
////            autoMecanumCommand.operate(1250, "BACKWARD");
////
////            autoStrafeCommand.operate(2.6, "LEFT");
////
////// SCORE THIRD CYCLE
////
////            autoTurnCommand.operate(45, "LEFT");
////            autoCameraAlign.operate();
////
////            autoPIDFlywheelCommand.operate(1160, 1500);
////            storageSubsystem.setServoPosition(0);
////
////// END OF THIRD CYCLE
////////////////////////////////////////////////////////////////////////////////////////////////////////
////// FIELD ORIENTED ALIGNMENT
////            autoStrafeCommand.operate(-1, "RIGHT");
////////////////////////////////////////////////////////////////////////////////////////////////////////
////// SHUTDOWNS
//
//            flywheelSubsystem.shutdown();
//            mecanumDriveSubsystem.shutdown();
//            storageSubsystem.shutdown();
//        }
//}
