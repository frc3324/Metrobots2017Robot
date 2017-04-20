package org.metrobots.commands.auto;

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShootGear extends Command {

	public double startTime, passedTime;
	
	public ShootGear() {
		requires((Subsystem) Robot.gearMech);
	}

	@Override
	protected void initialize() {
		startTime = Utility.getFPGATime();
		passedTime = 0;
	}
	
	@Override
	protected void execute() {
		passedTime = Utility.getFPGATime() - startTime;
		if((passedTime / 1100000) < 1.5) {
			Robot.gearMech.ejectGear();
		} else {
			Robot.gearMech.unejectGear();
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if((passedTime / 1100000) >= 1.5) {
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
