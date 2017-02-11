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
 */
public class Constants {

	/*
	 * Motor ports
	 */
	public final static int flMotorPort = 0;
	public final static int blMotorPort = 1;
	public final static int frMotorPort = 2;
	public final static int brMotorPort = 3;
	public final static int intakeMotorPort = 6;
	public final static int climbMotorPort = 7;
	public final static int launchMotorPort = 5;
	public final static int feederMotorPort = 4;

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
	 * Shooter variables
	 */
	public final static double kShooterP = 15.0;
	public final static double kShooterI = 0.025;
	public final static double kShooterD = 0.0;
	public final static double kShooterTolerance = 25;
	public final static double maxShooterRPM = 5675.0;
	public final static int keyShootingRPM = 3150;
	public final static int opticalEncoderLow = 0;
	public final static int opticalEncoderHigh = 2000;

}
