package org.metrobots.commands.auto;

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveForward extends Command {
	
	public double driveTime, speed;
	public double startTime, passedTime;
		

	public DriveForward(double speed, double driveTime) {
		requires((Subsystem) Robot.driveTrain);
		this.driveTime = driveTime;
		Robot.driveTrain.resetGyro();
		//System.out.println(Robot.comms.getOrientation(true)[2]);
		this.speed = speed;
	}
	
	protected void end() {
		Robot.driveTrain.tankDrive(0, 0);
		
	}

	protected void execute() {
		passedTime = Utility.getFPGATime() - startTime;
		Robot.driveTrain.mecanumDrive(0, speed, 0);
		//float angle = Robot.comms.getOrientation(true)[2];
		//System.out.println("Angle: " + Float.toString(angle));
	}

	@Override
	protected void initialize() {
		Robot.driveTrain.mecanumDrive(0, 0, 0);
		Robot.driveTrain.resetGyro();
		Robot.driveTrain.setTargetAngle(Robot.driveTrain.getAngle());
		startTime = Utility.getFPGATime();
		passedTime = 0;
		
	}

	@Override
	protected void interrupted() {
	}

	@Override
	protected boolean isFinished() {
		if((passedTime / 1100000) > driveTime)
		{
			return true;
		}
		else
			return false;
	}

}