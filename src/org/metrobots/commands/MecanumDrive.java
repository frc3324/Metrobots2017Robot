package org.metrobots.commands;

import org.metrobots.subsystems.DriveTrain;
import org.metrobots.util.MetroGamepad;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Teleop control of drive train<br>
 * <br>
 * 
 * Feb 5: Refactored. Also, added Javadocs - Cameron
 * 
 */
public class MecanumDrive extends Command {

	public DriveTrain driveTrain;
	private MetroGamepad gamepad;

	public boolean isFieldOriented = false;

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
		double angle = driveTrain.getAngle() * Math.PI / 180;

		if (gamepad.getButton(MetroGamepad.BUTTON_B)) {
			isFieldOriented = true;
		} else if (gamepad.getButton(MetroGamepad.BUTTON_A)) {
			isFieldOriented = false;
		}

		if (gamepad.getButton(MetroGamepad.BUTTON_START)) {
			driveTrain.resetGyro();
		}

		double x = 0;
		double y = 0;

		if (isFieldOriented) {
			x = driverX * Math.cos(angle) - driverY * Math.sin(angle);
			y = driverY * Math.cos(angle) + driverX * Math.sin(angle);
		} else {
			x = driverX;
			y = driverY;
		}

		driveTrain.mecanumDrive(x, y, turn);
	}

	/*
	 * Necessary function that returns false.
	 */
	protected boolean isFinished() {
		return false;
	}

}
