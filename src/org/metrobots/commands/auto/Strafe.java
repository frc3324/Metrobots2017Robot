package org.metrobots.commands.auto;

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Strafe extends Command {
	
	public double driveTime, speed;
	public double startTime, passedTime;
		

	public Strafe(double speed, double driveTime) {
		requires((Subsystem) Robot.driveTrain);
		this.driveTime = driveTime;
		//System.out.println(Robot.comms.getOrientation(true)[2]);
		this.speed = speed;
	}
	
	@Override
	protected void end() {
		Robot.driveTrain.tankDrive(0, 0);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		passedTime = Utility.getFPGATime() - startTime;
		Robot.driveTrain.mecanumDrive(0, speed, 0);
		//float angle = Robot.comms.getOrientation(true)[2];
		//System.out.println("Angle: " + Float.toString(angle));
	}

	@Override
	protected void initialize() {
		Robot.driveTrain.mecanumDrive(0, 0, 0);
		Robot.driveTrain.resetGyro();
		startTime = Utility.getFPGATime();
		passedTime = 0;
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		if((passedTime / 1000000) > driveTime)
		{
			return true;
		}
		else
			return false;
		// TODO Auto-generated method stub
		
	
	}

}