package org.metrobots;

import edu.wpi.first.wpilibj.SPI;

/**
 * Class with tons of constants that won't need to change on the fly.<br>
 * Examples: motor controller ports, sensor ports, xbox controller ports,
 * etc.<br>
 * <br>
 * 
 * Feb 5: Refactored. Also, added Javadocs - Cameron
 *
 * Feb 8: Added agitator Motor port; needs put on the robot - Xander
 *
 */
public class Constants {

	/*
	 * Motor ports
	 */
	public final static int flMotorPort = 0;
	public final static int blMotorPort = 1;
	public final static int frMotorPort = 2;
	public final static int brMotorPort = 3;
	public final static int intakeMotorPort = 8;//6
	public final static int climbMotorPort = 5;
	public final static int launchMotorPort = 4;
	public final static int feederMotorPort = 7;
	public final static int agitatorMotorPort = 6;//8
	
	/*
	 * Pneumatic Ports
	 */
	public final static int gearPushPort = 0;
	public final static int gearPullPort = 1;

	/*
	 * Sensor ports
	 */
	public final static int opticalEncoderPort = 0; // Analog
	public final static SPI.Port navxPort = SPI.Port.kMXP;
	public final static int flEncoder1 = 0;
	public final static int flEncoder2 = 1;
	public final static int blEncoder1 = 2;
	public final static int blEncoder2 = 3;
	public final static int frEncoder1 = 4;
	public final static int frEncoder2 = 5;
	public final static int brEncoder1 = 6;
	public final static int brEncoder2 = 7;
	public final static int shooterEncoder1 = 10; // Pin 0 on MXP theoretically
	public final static int shooterEncoder2 = 11; // Pin 1 on MXP
	public final static int ultrasonicOut = 8;
	public final static int ultrasonicIn = 9;

	/*
	 * Driver station ports
	 */
	public final static int motionGamepadPort = 0;
	public final static int mechanismGamepadPort = 1;

	/*
	 * 
	 * Shooter variables
	 */
	public final static double kShooterP = 5000.0; //80, 20 700
	public final static double kShooterI = 0.0;
	public final static double kShooterD = 0.0; //175, 100 300
	public final static double kShooterTolerance = 0.1;
	public final static double maxShooterRPM = 5675.0;
	public final static int keyShootingRPM = 4000;
	public static int airshipShootingRPM = 4500;
	public final static int opticalEncoderLow = 0;
	public final static int opticalEncoderHigh = 2000;
	public static double kFlywheelSpeed = 0.8;
	public final static double kFeederSpeed = 1.0;
	
	/*
	 * DriveTrain variables
	 */
	public static double kDriveHoldAngleP = 0.007;
	public final static double driveTrainAngleDeadband = 3;

}
