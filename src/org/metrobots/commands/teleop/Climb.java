package org.metrobots.commands.teleop;

import org.metrobots.subsystems.Climber;
import org.metrobots.util.MetroGamepad;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Teleop control of climbing mechanism<br>
 * <br>
 * 
 * Feb 5: Refactored. Also, added Javadocs - Cameron
 * 
 */
public class Climb extends Command {

	public Climber climber;
	private MetroGamepad gamepad;

	/**
	 * Get the objects necessary to operate the climber
	 * 
	 * @param cl
	 *            climber object
	 * @param gp
	 *            handheld controller for operating shooter
	 */
	public Climb(Climber cl, MetroGamepad gp) {
		climber = cl;
		gamepad = gp;
	}

	/**
	 * Function constantly run during teleop. Controls:<br>
	 * <b>Y</b>: spin climb winch
	 */
	public void execute() {
		climber.climb(gamepad.getButton(MetroGamepad.BUTTON_A));
	}

	/*
	 * Necessary function that returns false.
	 */
	@Override
	protected boolean isFinished() {
		return false;
	}

}
