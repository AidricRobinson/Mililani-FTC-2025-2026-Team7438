package org.firstinspires.ftc.teamcode;

public class Constants {
    public static class EncoderConstants{
        public static final double kCOUNTS_PER_ROTATION = 0;
        public static final double kGEAR_DRIVE_REDUCTION = 0;
        public static final double kWHEEL_DIAMETER = 0;
        public static final double kWHEEL_CIRCUMFERENCE = kWHEEL_DIAMETER * Math.PI;
        public static final double kCOUNTS_PER_INCH = (kCOUNTS_PER_ROTATION * kGEAR_DRIVE_REDUCTION) / kWHEEL_CIRCUMFERENCE;
    }
    public static class AprilTagConstants {
        public static final int kBlueGoalID = 20;
        public static final int kRedGoalID = 24;
        public static final double kAlignmentOffset = 8.84;
        public static final double kFlywheelEquationA = 8.006546538;
        public static final double kFlywheelEquationB = -105.4470598;
        public static final double kFlywheelEquationC = 1450;
    }
}
