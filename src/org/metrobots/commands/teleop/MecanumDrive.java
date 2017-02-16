package org.metrobots.commands.teleop;

import org.metrobots.subsystems.DriveTrain;
import org.metrobots.util.MetroGamepad;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Teleop control of drive train<br>
 * <br>
 * 
 * 
 * Feb 5: Refactored. Also, added Javadocs - Cameron
 * 
 */
public class MecanumDrive extends Command {

	public DriveTrain driveTrain;
	private MetroGamepad gamepad;

	/**
	 * Get the objects necessary to operate the drive train
	 * 
	 * @param dt
	 *            drive train object
	 * @param gp
	 *            handheld controller for operating shooter
	 */
	public MecanumDrive(DriveTrain dt, MetroGamepad gp) {
		driveTrain = dt;
		gamepad = gp;
	}

	/**
	 * Initialize stuff for driving the robot
	 */
	public void initialize() {
		driveTrain.mecanumDrive(0.0, 0.0, 0.0);
	}

	/**
	 * Function constantly run during teleop. Controls:<br>
	 * <b>Left X Axis:</b> left/right motion<br>
	 * <b>Left Y Axis:</b> forward/backward motion<br>
	 * <b>Right X Axis:</b> turning motion<br>
	 * <b>B</b>: Enable field orientation<br>
	 * <b>A</b>: Disable field orientation<br>
	 * <b>START</b>: Reset gyro
	 */
	public void execute() {
		double driverX = gamepad.getAxis(MetroGamepad.LEFT_X);
		double driverY = gamepad.getAxis(MetroGamepad.LEFT_Y);
		double turn = gamepad.getAxis(MetroGamepad.RIGHT_X);

		if (gamepad.getButton(MetroGamepad.BUTTON_B)) {
			driveTrain.setFieldOriented(true);
		} else if (gamepad.getButton(MetroGamepad.BUTTON_A)) {
			driveTrain.setFieldOriented(false);
		}

		if (gamepad.getButton(MetroGamepad.BUTTON_START)) {
			driveTrain.resetGyro();
		}
		
		/**
		 * These are added to make the robot more controllable
		 */
		driverX = driverX * Math.abs(driverX);
		driverY = driverY * Math.abs(driverY);

		driveTrain.mecanumDrive(driverX, driverY, turn);
	}

	/*
	 * Necessary function that returns false.
	 */
	protected boolean isFinished() {
		return false;
	}

}
