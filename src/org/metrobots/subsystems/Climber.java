package org.metrobots.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Climber mechanism for Metrobot<br>
 * <br>
 * 
 * Feb 5: Refactored. Also, added Javadocs - Cameron
 * 
 */
public class Climber extends Subsystem {

	public static SpeedController climber;

	/**
	 * Climber object
	 * 
	 * @param climbMotor
	 *            motor that is on the climber
	 */
	public Climber(SpeedController climbMotor) {
		climber = climbMotor;
		climber.set(0);
	}

	/**
	 * Spins winch to pick up robot
	 * 
	 * @param up
	 *            boolean which tells robot whether or not it should winch
	 */
	public void climb(boolean up) {
		if (up) {
			climber.set(1.0);
		} else {
			climber.set(0);
		}
	}

	/*
	 * Necessary method that contains nothing
	 */
	protected void initDefaultCommand() {
	}

}
