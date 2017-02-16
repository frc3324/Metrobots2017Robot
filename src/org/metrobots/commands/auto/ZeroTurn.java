package org.metrobots.commands.auto;

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ZeroTurn extends Command {
	private float turnAngle;

	public ZeroTurn(float angle, double speed) {
		requires((Subsystem) Robot.driveTrain);
		this.turnAngle = angle;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.driveTrain.mecanumDrive(0, 0, 0);
		Robot.driveTrain.resetGyro();
	}

	@Override
	protected void execute() {
		Robot.driveTrain.targetAngle = turnAngle;
	}

	@Override
	protected boolean isFinished() {
		if (Math.abs(Robot.driveTrain.getAngle() - turnAngle) < 10) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}