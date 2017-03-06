package org.metrobots.subsystems;

import org.metrobots.Constants;

import org.metrobots.commands.auto.DriveForward;
import org.metrobots.util.MetroGamepad;
import org.metrobots.Robot;
//github.com/frc3324/Metrobots2017Robot.git

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
	public boolean wasHoldingAngle;
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
		double angle = getAngle();
		
		/*if (Math.abs(turn) > 0.05 && wasTurning && isHoldingAngle) {
			targetAngle = angle;
		}*/
		
		turn = Math.abs(turn) > 0.025 ? turn : 0;
		
		if (turn == 0.0 && isHoldingAngle) {
			turn = Constants.kDriveHoldAngleP * (targetAngle - angle);
		}
		wasTurning = (Math.abs(turn) > 0.05);

		double adjX = 0;
		double adjY = 0;

		angle = Math.toRadians(angle);
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
	/*public void mecanumDriveAngle(double leftX,double leftY, double rightX, double rightY, double turn){
		double angle = getAngle() * Math.PI / 180;
		double adjX = 0;
		double adjY = 0;
		double hypR = 0;
		hypR = Math.sqrt((rightX*rightX)+(rightY*rightY));
		if (Math.asin(rightY/hypR) == Math.acos(rightX/hypR)){
			targetAngle = Math.asin(rightY/hypR);
		}
		if (isFieldOriented) {
			adjX = leftX * Math.cos(angle) - leftY * Math.sin(angle);
			adjY = leftY * Math.cos(angle) + leftX * Math.sin(angle);
		} else {
			adjX = leftX;
			adjY = leftY;
		}
		
		fl.set(adjX + adjY + turn);
		bl.set(-adjX + adjY + turn);
		fr.set(adjX - adjY + turn);
		br.set(-adjX - adjY + turn);
	}*/
	/**
	 * Determine whether the robot will hold its angle or not
	 * @param fo True if you want to field oriented control, false if you don't
	 */
	public void setFieldOriented(boolean fo) {
		isFieldOriented = fo;
	}
	
	public void setTargetAngle(double angle) {
		targetAngle = angle;
	}
	
	/**
	 * Determine whether the robot will hold its angle or not
	 * @param ha True if you want to hold angle, false if you don't
	 */
	public void setIsHoldingAngle(boolean ha) {
		isHoldingAngle = ha;
	}
	
	public void holdThisAngle() {
		wasHoldingAngle = isHoldingAngle;
		if (!wasHoldingAngle) {
			resetHoldAngle();
		}
		isHoldingAngle = true;
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
		return navx.getAngle() % 360;
	}
	
	public double getTargetAngle() {
		return targetAngle;
	}
	
	public void resetHoldAngle(){
		//navx.reset();
		targetAngle = getAngle();
	}
	/**
	 * Reset gyro value back to 0
	 */
	public void resetGyro() {
		navx.reset();
	}
	
	public void visionGear() {
		int visionDirection = org.metrobots.Robot.comms.getDirection();
		int visionMagnitude = org.metrobots.Robot.comms.getMagnitude();
		double visionSpeed = 0.0;
		
		if ((visionDirection) == 1) {
			if (visionDirection == 1) { //move robot right
				visionSpeed = 0.5;
				mecanumDrive(1.0, 0.0, 0.0);
			}
		
			else if (visionDirection == -1) { //move robot left
				visionSpeed = -0.5;
				mecanumDrive(1.0, 0.0, 0.0);
			}
			
			mecanumDrive(0, visionSpeed, 0);
		
		} else { //don't move left or right; ready to move forward
			visionSpeed = 0;
			if (visionMagnitude == 3) { //move robot forward fast speed
				visionSpeed = 0.75;
				mecanumDrive(0.0, 1.0, 0.0);
			}
		
			else if (visionMagnitude == 2) { //move robot forward medium speed
				visionSpeed = 0.50;
				mecanumDrive(0.0, 1.0, 0.0);
			}
		
			else if (visionMagnitude == 1) { //move robot forward low speed
				visionSpeed = 0.25;
				mecanumDrive(0.0, 1.0, 0.0);
			}
		
			else if (visionMagnitude == 0) { //don't move robot forward
				visionSpeed = 0.0;
			}
			 mecanumDrive(visionSpeed, 0, 0);
			
			}
		}
	
	/*
	 * Necessary method that contains nothing
	 */
	protected void initDefaultCommand() {
	}
}