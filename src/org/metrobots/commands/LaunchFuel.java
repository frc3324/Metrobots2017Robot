package org.metrobots.commands;

import org.metrobots.Constants;
import org.metrobots.subsystems.Shooter;
import org.metrobots.util.MetroGamepad;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Teleop control of shooter mechanism<br>
 * <br>
 * 
 * Feb 5: Refactored. Also, added Javadocs - Cameron
 * 
 */
public class LaunchFuel extends Command {

	public Shooter shooter;
	public MetroGamepad gamepad;

	/**
	 * Get the objects necessary to operate the shooter
	 * 
	 * @param sh
	 *            shooter object
	 * @param gp
	 *            handheld controller for operating shooter
	 */
	public LaunchFuel(Shooter sh, MetroGamepad gp) {
		shooter = sh;
		gamepad = gp;
	}

	/**
	 * Function constantly run during teleop. Controls:<br>
	 * <b>X</b>: set RPM to speed for key<br>
	 * <b>Y</b>: stop shooter<br>
	 * <b>B</b>: load fuel into shooter, if it is up to speed
	 */
	public void execute() {
		if (gamepad.getButton(MetroGamepad.BUTTON_X)) {
			shooter.setTargetSpeed(Constants.keyShootingRPM);
		} else if (gamepad.getButton(MetroGamepad.BUTTON_Y)) {
			shooter.setTargetSpeed(0);
		}
		if (gamepad.getButton(MetroGamepad.BUTTON_B)) {
			shooter.launchWhenReady();
		}
	}

	/*
	 * Necessary function that returns false.
	 */
	protected boolean isFinished() {
		return false;
	}

}