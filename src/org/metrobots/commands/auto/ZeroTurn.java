package org.metrobots.commands.auto;

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ZeroTurn extends Command {
	
	private float turnAngle;
	public double startTime, passedTime;

	public ZeroTurn(float angle, double speed) {
		requires((Subsystem) Robot.driveTrain);
		this.turnAngle = angle;
	}

	@Override
	protected void initialize() {
		Robot.driveTrain.mecanumDrive(0, 0, 0);
		Robot.driveTrain.resetGyro();
		Robot.driveTrain.resetHoldAngle();
		Robot.driveTrain.setIsHoldingAngle(true);
		Robot.driveTrain.setTargetAngle(turnAngle);
		Robot.driveTrain.setHoldAngleP(0.01);
		startTime = Utility.getFPGATime();
		passedTime = 0;
	}

	@Override
	protected void execute() {
		passedTime = Utility.getFPGATime() - startTime;
		Robot.driveTrain.mecanumDrive(0, 0, 0);
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
		Robot.driveTrain.setHoldAngleP(0.005);
	}

	@Override
	protected void interrupted() {}

}