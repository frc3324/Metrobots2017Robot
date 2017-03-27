package org.metrobots.commands.auto;

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PlaceGear extends Command {

	public int lastVisionDirection = 1;
	public int lastVisionMagnitude = 1;
	public int visionY;
	public int lastVisionY = 50;

	public PlaceGear() {
		requires((Subsystem) Robot.driveTrain);
	}
	
	@Override
	protected void initialize() {
		Robot.driveTrain.mecanumDrive(0, 0, 0);
		Robot.driveTrain.setFieldOriented(false);
		Robot.driveTrain.resetGyro();
		Robot.driveTrain.resetHoldAngle();
		Robot.driveTrain.setIsHoldingAngle(true);
		Robot.driveTrain.setBrakeMode(true);

	}

	protected void execute() {
		double visionSpeed = 0.0;
		int visionDirection = 1;
		int visionMagnitude = 1;
		try {
			visionDirection = Robot.comms.getDirection();
			visionY = Robot.comms.getYOffset();
			visionMagnitude = Robot.comms.getMagnitude();
			
			if (Math.abs(visionY) > 20) {
				double val = ((double)visionY) / 200;
				if (val > 0.4) {
					val = 0.4;
				} else if (val < -0.4) {
					val = -0.4;
				}
				System.out.println("Strafe speed " + val);
				Robot.driveTrain.mecanumDrive(val, 0, 0);
			} else {
				Robot.driveTrain.mecanumDrive(0, 0, 0);
			}
			/*if (visionDirection != 0) {
				Robot.driveTrain.mecanumDrive(visionStrafe, 0, 0); // Strafe dependent on peg location
			} else {
				if (visionMagnitude == 3) { // forward fast speed
					visionSpeed = 0.5;
				} else if (visionMagnitude == 2) { // forward medium speed
					visionSpeed = 0.3;
				} else if (visionMagnitude == 1) { // forward low speed
					visionSpeed = 0.25;
				} else { // don't move
					visionSpeed = 0.0;
				}
				visionSpeed = 0.0;
				Robot.driveTrain.mecanumDrive(0.0, -visionSpeed, 0.0);
			}*/

			lastVisionDirection = visionDirection;
			lastVisionMagnitude = visionMagnitude;
			lastVisionY = visionY;
			
		} catch (Exception e) {
			System.out.println("Place Gear auto failed to get comms values: " + e.getMessage());
			Robot.driveTrain.mecanumDrive(0, 0, 0);
		}

	}
	
	@Override
	protected boolean isFinished() {
		try {
			if (Math.abs(visionY) < 20 && Math.abs(lastVisionY) < 20 && Math.abs(Robot.comms.getYOffset()) < 20) {
				return true;
			}			
		} catch (Exception e) {
			
		}
		return false;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}
	
}
