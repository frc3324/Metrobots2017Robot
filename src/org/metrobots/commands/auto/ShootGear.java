package org.metrobots.commands.auto;

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShootGear extends Command {
	
	
	public ShootGear() {
		requires((Subsystem) Robot.driveTrain);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.driveTrain.mecanumDrive(0, 0, 0);
	}
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.gearMech.ejectGear();
		Robot.gearMech.unejectGear();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}


}
