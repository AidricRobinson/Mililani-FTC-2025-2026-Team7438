package org.firstinspires.ftc.teamcode.Commands.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Constants;


public class EncoderTurningCommand {
    MecanumDriveSubsystem mecanumDriveSubsystem;
    PIDController m_PIDController;
    Telemetry telemetry;
    LinearOpMode m_linearOpMode;

    public EncoderTurningCommand(MecanumDriveSubsystem mecanumDriveSubsystem, Telemetry telemetry, LinearOpMode linearOpMode) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        m_PIDController = new PIDController(0.006,0, 0, 0.05);
        m_linearOpMode = linearOpMode;


    }
    public void EnocderTurningOperate(double distance, String direction){
        int motorTarget = (int) (distance);
        m_PIDController.createSetPoint(motorTarget + (mecanumDriveSubsystem.getYaw()));

        while (true && m_linearOpMode.opModeIsActive()) {
            m_PIDController.setProcessVariable(-mecanumDriveSubsystem.getYaw());

            if (Math.abs(m_PIDController.getError()) < 10) {
                break;
            }

            double powerOutput = m_PIDController.getOutput();
            mecanumDriveSubsystem.setLeftBackPower(powerOutput);
            mecanumDriveSubsystem.setLeftFrontPower(powerOutput);
            mecanumDriveSubsystem.setRightBackPower(-powerOutput);
            mecanumDriveSubsystem.setRightFrontPower(-powerOutput); // :D
        }
        mecanumDriveSubsystem.shutdown();
    }
    public void Shutdown() {
        mecanumDriveSubsystem.shutdown();
    }
}
