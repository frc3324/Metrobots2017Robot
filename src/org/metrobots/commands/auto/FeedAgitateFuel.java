package org.metrobots.commands.auto;

import org.metrobots.Robot;
import org.metrobots.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FeedAgitateFuel extends Command{
	
	public boolean feed;
	
	public FeedAgitateFuel(boolean feed) {
		requires((Subsystem) Robot.shooter);
		this.feed = feed;
	}

	@Override
	protected void end() {
	}
	
	@Override
	protected void execute() {
		Robot.shooter.agitate(feed ? 1.0 : 0.0);
		Robot.shooter.feed(feed);
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
