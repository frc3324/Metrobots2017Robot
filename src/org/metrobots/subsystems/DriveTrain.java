package org.metrobots.subsystems;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drive train subsystem for metrobot<br>
 * <br>
 * 
 * Feb 5: Refactored. Also, added Javadocs - Cameron
 * 
 */
public class DriveTrain extends Subsystem {

	public static CANTalon fl, bl, fr, br;
	public static AHRS navx;

	/**
	 * Drive train object
	 * 
	 * @param flMotor
	 *            front left motor
	 * @param blMotor
	 *            back left motor
	 * @param frMotor
	 *            front right motor
	 * @param brMotor
	 *            back right motor
	 * @param navxSensor
	 *            IMU sensor
	 */
	public DriveTrain(CANTalon flMotor, CANTalon blMotor, CANTalon frMotor, CANTalon brMotor, AHRS navxSensor) {

		fl = flMotor;
		bl = blMotor;
		fr = frMotor;
		br = brMotor;

		navx = navxSensor;
	}

	/**
	 * Move motors like a tank drive
	 * 
	 * @param left
	 *            value from -1.0 to 1.0 for the left side of the drive train
	 * @param right
	 *            value from -1.0 to 1.0 for the right side of the drive train
	 */
	public void tankDrive(double left, double right) {
		fl.set(left);
		bl.set(left);
		fr.set(right);
		br.set(right);
	}

	/**
	 * Move motors like a mecanum drive
	 * 
	 * @param x
	 *            strafe component of drive motion
	 * @param y
	 *            forward/backward component of drive motion
	 * @param turn
	 *            rotation component of drive motion
	 */
	public void mecanumDrive(double x, double y, double turn) {
		fl.set(x + y + turn);
		bl.set(-x + y + turn);
		fr.set(x - y + turn);
		br.set(-x - y + turn);
	}

	/**
	 * Get gyro angle
	 * 
	 * @return gyro angle
	 */
	public double getAngle() {
		return navx.getAngle();
	}

	/**
	 * Reset gyro value back to 0
	 */
	public void resetGyro() {
		navx.reset();
	}

	/*
	 * Necessary method that contains nothing
	 */
	protected void initDefaultCommand() {
	}
}
