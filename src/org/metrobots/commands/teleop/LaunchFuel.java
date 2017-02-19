package org.metrobots.commands.teleop;

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
 * Feb 8: Added agitator. - Xander
 * 
 */
public class LaunchFuel extends Command {

	public Shooter shooter;
	private MetroGamepad gamepad;

	

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
		shooter.enable();
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
			//shooter.flywheel.set(.78);
			shooter.setTargetSpeed(Constants.keyShootingRPM);
		} else if (gamepad.getButton(MetroGamepad.BUTTON_Y)) {
			shooter.setTargetSpeed(0);
			//shooter.flywheel.set(0);
		}
		shooter.agitate(gamepad.getAxis(MetroGamepad.LEFT_Y));
		if (gamepad.getButton(MetroGamepad.BUTTON_B)) {
			shooter.feed(true);
			//shooter.agitate(true);
		} else {
			shooter.feed(false);
			//shooter.agitate(false);
		}
		
		
			
		
	}

	/*
	 * Necessary function that returns false.
	 */
	protected boolean isFinished() {
		return false;

	}
}