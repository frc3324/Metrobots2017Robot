package org.metrobots.subsystems;

import org.metrobots.Constants;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drive train subsystem for metrobot<br>
 * <br>
 * 
 * Feb 5: Refactored. Also, added Javadocs - Cameron
 * Feb 16: Move most of the mecanum code from the MecanumDrive file to here - Cameron
 * 
 */
public class DriveTrain extends Subsystem {

	public static CANTalon fl, bl, fr, br;
	public static AHRS navx;
	
	public boolean isFieldOriented;
	public boolean isHoldingAngle;
	public boolean wasTurning;
	public double targetAngle;

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
		double angle = getAngle() * Math.PI / 180;
		
		if (turn != 0.0 && wasTurning && isHoldingAngle) {
			targetAngle = angle;
		}
		
		if (turn == 0.0 && isHoldingAngle) {
			turn = Constants.kDriveHoldAngleP * (targetAngle - angle);
		}
		wasTurning = (turn != 0);

		double adjX = 0;
		double adjY = 0;

		if (isFieldOriented) {
			adjX = x * Math.cos(angle) - y * Math.sin(angle);
			adjY = y * Math.cos(angle) + x * Math.sin(angle);
		} else {
			adjX = x;
			adjY = y;
		}

		fl.set(adjX + adjY + turn);
		bl.set(-adjX + adjY + turn);
		fr.set(adjX - adjY + turn);
		br.set(-adjX - adjY + turn);
		
	}
	
	/**
	 * Determine whether the robot will hold its angle or not
	 * @param fo True if you want to field oriented control, false if you don't
	 */
	public void setFieldOriented(boolean fo) {
		isFieldOriented = fo;
	}
	
	/**
	 * Determine whether the robot will hold its angle or not
	 * @param ha True if you want to hold angle, false if you don't
	 */
	public void setIsHoldingAngle(boolean ha) {
		isHoldingAngle = ha;
	}
	
	public boolean isOnAngle() {
		return Math.abs(targetAngle - getAngle()) < Constants.driveTrainAngleDeadband;
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
