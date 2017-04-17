package org.metrobots.commands.auto;

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Brake extends Command {
	public boolean isBrake;

	public Brake(boolean val) {
		requires((Subsystem) Robot.driveTrain);
		isBrake = val;
	}
	
	protected void end() {
	}

	protected void execute() {
			
	}

	@Override
	protected void initialize() {
		Robot.driveTrain.mecanumDrive(0, 0, 0);
		Robot.driveTrain.setBrakeMode(isBrake);
		
	}

	@Override
	protected void interrupted() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}