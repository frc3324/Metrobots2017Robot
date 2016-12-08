package org.metrobots;

import edu.wpi.first.wpilibj.Joystick;

public class MetroXboxController {
	// Analog Inputs (Axes)
	public static final int LEFT_X = 0;
	public static final int LEFT_Y = 1;
	public static final int LT = 2;
	public static final int RT = 3;//fix these values
	public static final int RIGHT_X = 4;
	public static final int RIGHT_Y = 5;
	// Digital Inputs (Buttons)
	public static final int BUTTON_A = 1, BUTTON_B = 2, BUTTON_X = 3,
					BUTTON_Y = 4;
	public static final int LB = 5, RB = 6;
	public static final int BUTTON_BACK = 7, BUTTON_START = 8;
	public static final int LEFT_CLICK = 9, RIGHT_CLICK = 10;

	public static final double AXIS_DEADBAND = 0.01;

	public boolean prevA, prevB, prevX, prevY, prevLB, prevRB, prevBack,
					prevStart, prevLC, prevRC;
	public boolean toggleA, toggleB, toggleX, toggleY, toggleLB, toggleRB,
					toggleBack, toggleStart, toggleLC, toggleRC;

	public boolean previousButton[];
	public boolean toggleButton[];

	public Joystick joystick;

	public MetroXboxController(int port) {
		joystick = new Joystick(port);

		prevA = false;
		prevB = false;
		prevX = false;
		prevY = false;
		prevLB = false;
		prevRB = false;
		prevBack = false;
		prevStart = false;
		prevLC = false;
		prevRC = false;

		toggleA = false;
		toggleB = false;
		toggleX = false;
		toggleY = false;
		toggleLB = false;
		toggleRB = false;
		toggleBack = false;
		toggleStart = false;
		toggleLC = false;
		toggleRC = false;

		previousButton = new boolean[10];
		toggleButton = new boolean[10];

		for (int i = 0; i < previousButton.length; i++) {
			previousButton[i] = false;
		}

		for (int i = 0; i < toggleButton.length; i++) {
			toggleButton[i] = false;
		}

	}

	public double getAxis(int axis) {
		try {
			double value = joystick.getRawAxis(axis);
			double output = 0;
			if (Math.abs(value) > AXIS_DEADBAND) {
				if (axis == LEFT_Y || axis == RIGHT_Y || axis == 3) {
					output = -value;
				} else {
					output = value;
				}
			}
			return output;
		}
		catch (Exception e) {
			return 0;
		}
	}

	public int getDPadAngle() {
		int angle = 0;
		try {
			angle = joystick.getPOV();
		}
		catch (Exception e) {
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

	public double getDPadX() {
		if (this.getDPadAngle() == -1) {
			return 0;
		}
		return Math.cos(Math.toRadians(this.getDPadAngle()));
	}

	public double getDPadY() {
		if (this.getDPadAngle() == -1) {
			return 0;
		}
		return Math.sin(Math.toRadians(this.getDPadAngle()));
	}

	public boolean getButton(int button) {
		boolean buttonVal;
		try {
			buttonVal = joystick.getRawButton(button);
		}
		catch (Exception e) {
			buttonVal = false;
		}
		previousButton[button] = buttonVal;
		return buttonVal;
	}

	public boolean toggleWhenPressed(int button) {
		boolean buttonVal;
		try {
			buttonVal = joystick.getRawButton(button);
		}
		catch (Exception e) {
			buttonVal = false; 
		}
		try {
			if (!previousButton[button] && buttonVal)
				toggleButton[button] = !toggleButton[button];
			previousButton[button] = buttonVal;
			return toggleButton[button];
		} catch (Exception e) {
			return false;
		}
	}
}
