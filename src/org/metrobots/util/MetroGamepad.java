package org.metrobots.util;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Class which makes the Joystick class easier to use<br>
 * <br>
 * 
 * Feb 5: Refactored. Also, added Javadocs - Cameron
 *
 */
public class MetroGamepad {
	/*
	 * Analog Inputs (Axes)
	 */
	public static final int LEFT_X = 0;
	public static final int LEFT_Y = 1;
	public static final int LT = 2;
	public static final int RT = 3;
	public static final int RIGHT_X = 4;
	public static final int RIGHT_Y = 5;

	/*
	 * Digital Inputs (Buttons)
	 */
	public static final int BUTTON_A = 1;
	public static final int BUTTON_B = 2;
	public static final int BUTTON_X = 3;
	public static final int BUTTON_Y = 4;
	public static final int LB = 5;
	public static final int RB = 6;
	public static final int BUTTON_BACK = 7;
	public static final int BUTTON_START = 8;
	public static final int LEFT_CLICK = 9;
	public static final int RIGHT_CLICK = 10;

	public static final double AXIS_DEADBAND = 0.01;

	private Joystick gamepad;

	/**
	 * Creates instance of MetroGamepad
	 * 
	 * @param port
	 *            Port of the gamepad
	 */
	public MetroGamepad(int port) {
		gamepad = new Joystick(port);
	}

	/**
	 * Read the value of an axis
	 * 
	 * @param axis
	 *            The axis you want to get the value from
	 * @return Value of axis, value from -1.0 to 1.0
	 */
	public double getAxis(int axis) {
		try {
			double value = gamepad.getRawAxis(axis);
			double output = 0;
			if (Math.abs(value) > AXIS_DEADBAND) {
				if (axis == LEFT_Y || axis == RIGHT_Y || axis == 3) {
					output = -value;
				} else {
					output = value;
				}
			}
			return output;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Read the angle on the DPad
	 * 
	 * @return Angle of DPad (degrees)
	 */
	public int getDPadAngle() {
		int angle = 0;
		try {
			angle = gamepad.getPOV();
		} catch (Exception e) {
			angle = -1;
		}
		if (angle == -1) {
			return -1;
		} else if (angle == 90) {
			return 0;
		} else if (angle == 180) {
			return 270;
		} else if (angle == 270) {
			return 180;
		} else if (angle == 45) {
			return 45;
		} else if (angle == 135) {
			return 315;
		} else if (angle == 225) {
			return 225;
		} else if (angle == 315) {
			return 135;
		} else if (angle == 0) {
			return 90;
		}

		return angle;
	}

	/**
	 * Read the X component of the DPad
	 * 
	 * @return X component of DPad, value from -1.0 to 1.0
	 */
	public double getDPadX() {
		if (getDPadAngle() == -1) {
			return 0;
		}
		return Math.cos(Math.toRadians(getDPadAngle()));
	}

	/**
	 * Read the Y component of the DPad
	 * 
	 * @return Y component of DPad, value from -1.0 to 1.0
	 */
	public double getDPadY() {
		if (getDPadAngle() == -1) {
			return 0;
		}
		return Math.sin(Math.toRadians(getDPadAngle()));
	}

	/**
	 * Read the value of a button
	 * 
	 * @param button
	 *            The button you want to get the value from
	 * @return Value of the button (boolean)
	 */
	public boolean getButton(int button) {
		boolean buttonVal;
		try {
			buttonVal = gamepad.getRawButton(button);
		} catch (Exception e) {
			buttonVal = false;
		}
		return buttonVal;
	}
}
