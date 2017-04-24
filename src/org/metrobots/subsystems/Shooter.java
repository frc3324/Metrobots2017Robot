package org.metrobots.subsystems;

import org.metrobots.Constants;
import org.metrobots.util.OpticalEncoder;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Shooter mechanism for Metrobot<br>
 * Uses PID Controller to control shooter speed<br>
 * 
 * Feb 5: Refactored shooter to use PIDSubsystem, utilizing the built in PID
 * controller. Also, added Javadocs - Cameron
 * 
 * Feb 8: Added Agitator; may need tweaked. Boolean is "clear" - Xander
 * 
 */
public class Shooter extends PIDSubsystem {

	/*
	 * Declare objects necessary for the shooter subsystem
	 */
	public static SpeedController flywheel;
	public static SpeedController feeder;
	public static SpeedController agitator;
	public static OpticalEncoder oEncoder;
	public static Encoder encoder;
	public static double speed;
	public double scaler = 0.002;
	

	/**
	 * Shooter object
	 * 
	 * @param flywheelMotor
	 *            motor for the flywheel
	 * @param feederMotor
	 *            motor used to feed fuel into flywheel
	 * @param opticalEncoder
	 *            encoder used for speed control of flywheel
	 * @param agitatorMotor
	 * 			  moter used to clear out storage; may need tweaked
	 */
	public Shooter(SpeedController flywheelMotor, SpeedController feederMotor, SpeedController agitatorMotor, OpticalEncoder opticalEncoder) {
		super("Shooter", Constants.kShooterP, Constants.kShooterI, Constants.kShooterD); // Create PIDSubsystem for the shooter flywheel
		setAbsoluteTolerance(Constants.kShooterTolerance); // Set tolerance for the flywheel PID
		getPIDController().setContinuous(false); // Make the PID not continuous
		getPIDController().enable();
		getPIDController().setOutputRange(0, 10000); // Tells PID Controller never to reverse the flywheel
	
		/*
		 * Initialize objects for shooter based off of the objects passed from robot
		 */
		flywheel = flywheelMotor;
		feeder = feederMotor;
		agitator = agitatorMotor;
		oEncoder = opticalEncoder;
	}
	public Shooter(SpeedController flywheelMotor, SpeedController feederMotor, SpeedController agitatorMotor, Encoder encoder) {
		super("Shooter" , Constants.kShooterP, Constants.kShooterI, Constants.kShooterD);
		setAbsoluteTolerance(Constants.kShooterTolerance); // Set tolerance for the flywheel PID
		getPIDController().setContinuous(false); // Make the PID not continuous
		getPIDController().enable();
		getPIDController().setOutputRange(0, 10000); // Tells PID Controller never to reverse the flywheel
	
		/*
		 * Initialize objects for shooter based off of the objects passed from robot
		 */
		flywheel = flywheelMotor;
		feeder = feederMotor;
		agitator = agitatorMotor;
		this.encoder = encoder;
	}
	/**
	 * Feeds fuel into shooter
	 * 
	 * @param feed
	 *            boolean which tells robot whether or not it should feed fuel
	 *            into the shooter
	 */
	public void feed(boolean feed) {
		if (feed) {
			feeder.set(-Constants.kFeederSpeed);
		} else {
			feeder.set(0);
		}
	}

	public void agitate(double speed) {
		agitator.set(speed);
		/*if (speed) {
			agitator.set(0.5);
		} else {
			agitator.set(0.0);
		}*/
	}
	
	/**
	 * Sends a boolean to feed, depending on whether the flywheel is up to speed
	 * or not
	 */
	public void launchWhenReady() {
		feed(getPIDController().onTarget());
	}

	/**
	 * Changes setpoint(aka target speed) of PID controller
	 * 
	 * @param targetRPM
	 *            RPM that we want the flywheel to spin at
	 */
	public void setTargetSpeed(int targetRPM) {
		getPIDController().setSetpoint(targetRPM);
		speed = targetRPM;
		//flywheel.set(targetRPM/Constants.maxShooterRPM);
	}

	/**
	 * Returns the setpoint(aka target speed) of PID controller
	 * 
	 * @return setpoint of shooter PID
	 */
	public double getTargetSpeed() {
		return getPIDController().getSetpoint();
	}

	/**
	 * Returns the current RPM of the shooter
	 * 
	 * @return shooter RPM
	 */
	public double getRPM() {
		if (encoder == null) {
			return oEncoder.getRPM();
		} else {
			return encoder.getRate();
		}
	}

	/**
	 * Sets the percent that the RPM can be off to still be considered a
	 * 'correct' RPM
	 * 
	 * @param percent
	 *            percent that the RPM can be off of the target and still be
	 *            considered on target (aka deadband)
	 */
	public void setTolerance(double percent) {
		getPIDController().setPercentTolerance(percent);
	}

	/**
	 * Sensor value that is sent to the PID controller
	 * 
	 * @return shooter RPM
	 */
	@Override
	public double returnPIDInput() {
		return getRPM();
	}

	/**
	 * Sets the motor speed to the PID output, divided by the max RPM, since PID
	 * will return value between 0 and maxShooterRPM
	 * 
	 * @param output
	 *            speed to set motor, but takes an RPM rather than value from
	 *            0.0-1.0. This is then scaled to value from 0.0-1.0
	 */
	public void usePIDOutput(double output) {
		if (getTargetSpeed() != 0) {
			flywheel.set(speed / Constants.maxShooterRPM);
		} else {
			flywheel.set(0);
		}
	}
	
	public void modulateShooterSpeed(double rightY_2) {
		Constants.kFlywheelSpeed += rightY_2 * scaler;
	}
	
	/*
	 * Necessary method that contains nothing
	 */
	protected void initDefaultCommand() {
	}
}
