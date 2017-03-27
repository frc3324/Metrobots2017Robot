package org.metrobots.commands.auto;

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pause extends Command {
	
	private double seconds;
	public double startTime, passedTime;

	public Pause(double seconds) {
		this.seconds = seconds;
	}

	@Override
	protected void initialize() {
		Robot.driveTrain.mecanumDrive(0, 0, 0);
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
		if((passedTime / 1100000) > seconds) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}