package org.metrobots.commands.auto;

import org.metrobots.Robot;
import org.metrobots.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SpinFlywheel extends Command{
	
	public int speed;
	
	public SpinFlywheel(int speed) {
		requires((Subsystem) Robot.shooter);
		this.speed = speed;
	}

	@Override
	protected void end() {
	}
	
	@Override
	protected void execute() {
		Robot.shooter.setTargetSpeed(speed);
	}
	
	@Override
	protected void initialize() {
	}
	
	@Override
	protected void interrupted() {
		
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
