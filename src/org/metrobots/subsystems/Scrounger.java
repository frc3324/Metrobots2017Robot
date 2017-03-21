package org.metrobots.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Scrounger mechanism for Metrobot<br>
 * <br>
 * 
 * Feb 5: Refactored. Also, added Javadocs - Cameron
 * 
 */
public class Scrounger extends Subsystem {

	public static SpeedController scrounger;

	/**
	 * Scrounger object
	 * 
	 * @param scroungerMotor
	 *            motor that is on the scrounger
	 */
	public Scrounger(SpeedController scroungerMotor) {
		scrounger = scroungerMotor;
	}

	/**
	 * Spins scrounger to intake fuel off the ground
	 * 
	 * @param in
	 *            boolean which tells robot whether or not it should eat fuel
	 *            from the ground
	 */
	public void intake(double in) {
		scrounger.set(in);
	}

	/*
	 * Necessary method that contains nothing
	 */
	protected void initDefaultCommand() {
	}

}
