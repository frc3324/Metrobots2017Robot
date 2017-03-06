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
	 * Sensor ports
	 */
	public final static int opticalEncoderPort = 0;
	public final static SPI.Port navxPort = SPI.Port.kMXP;

	/*
	 * Driver station ports
	 */
	public final static int motionGamepadPort = 0;
	public final static int mechanismGamepadPort = 1;

	/*
	 * 
	 * Shooter variables
	 */
	public final static double kShooterP = 20.0; //80
	public final static double kShooterI = 0.0;
	public final static double kShooterD = 100.0; //175
	public final static double kShooterTolerance = 0.1;
	public final static double maxShooterRPM = 5675.0;
	public final static int keyShootingRPM = 3200;
	public final static int opticalEncoderLow = 0;
	public final static int opticalEncoderHigh = 2000;
	public static double kFlywheelSpeed = 0.8;
	public final static double kFeederSpeed = 1.0;
	
	/*
	 * DriveTrain variables
	 */
	public final static double kDriveHoldAngleP = .01;
	public final static double driveTrainAngleDeadband = 3;

}
