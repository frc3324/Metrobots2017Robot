package org.metrobots.commands.auto;

import org.metrobots.Robot;
import org.metrobots.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShootFuel extends Command{
	
	public double driveTime, speed;
	
	public ShootFuel(double driveTime, double speed) {
		requires((Subsystem) Robot.shooter);
		this.driveTime = driveTime;
		this.speed = speed;
	}

	@Override
	protected void end() {
		Shooter.flywheel.set(0);
		Shooter.feeder.set(0);
		Shooter.agitator.set(0);
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void execute() {
		Shooter.flywheel.set(speed);
		Shooter.feeder.set(-1.0);
		Shooter.agitator.set(1.0);
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void initialize() {
		Shooter.flywheel.set(0);
		Shooter.feeder.set(0);
		Shooter.agitator.set(0);
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void interrupted() {
		
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
