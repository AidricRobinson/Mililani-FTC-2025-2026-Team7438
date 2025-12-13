package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Constants;


public class EncoderStrafingCommand {
    MecanumDriveSubsystem mecanumDriveSubsystem;
    PIDController m_PIDController;
    Telemetry telemetry;
    LinearOpMode m_linearOpMode;

    public EncoderStrafingCommand(MecanumDriveSubsystem mecanumDriveSubsystem, Telemetry telemetry, LinearOpMode linearOpMode) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        m_PIDController = new PIDController(0.000000000000000000091,0, 0, 15);
        m_linearOpMode = linearOpMode;

    }
    public void EncoderDriveOperate(double distance, String direction){
        int motorTarget = (int) (distance * Constants.EncoderConstants.kCOUNTS_PER_INCH);
        m_PIDController.createSetPoint(motorTarget + (mecanumDriveSubsystem.encoderReading()[0]));

        while (true && m_linearOpMode.opModeIsActive()) {
            m_PIDController.setProcessVariable(mecanumDriveSubsystem.encoderReading()[0]);

            if (Math.abs(m_PIDController.getError()) < 10) {
                break;
            }

            double powerOutput = m_PIDController.getOutput();
            mecanumDriveSubsystem.setLeftBackPower(-powerOutput);
            mecanumDriveSubsystem.setLeftFrontPower(powerOutput);
            mecanumDriveSubsystem.setRightBackPower(powerOutput);
            mecanumDriveSubsystem.setRightFrontPower(-powerOutput); // :D
        }
        mecanumDriveSubsystem.shutdown();
    }
    public void Shutdown() {
        mecanumDriveSubsystem.shutdown();
    }
}
