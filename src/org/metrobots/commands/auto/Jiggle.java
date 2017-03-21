package org.metrobots.commands.auto;

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Jiggle extends Command {
	
	public double jiggleCount, degree;
	public double initialGyroAngle;
		

	public Jiggle(double jiggleCount, double degree) {
		requires((Subsystem) Robot.driveTrain);
		this.jiggleCount = jiggleCount;
		this.degree = degree;
	
	}
	
	protected void end() {
		Robot.driveTrain.tankDrive(0, 0);
		
	}

	protected void execute() {
		Robot.driveTrain.setTargetAngle(initialGyroAngle + degree);
		if (Math.abs(Robot.driveTrain.getAngle()) - (initialGyroAngle + degree) < 0.5) {
			degree = -degree;
			jiggleCount--;
			
		}
			
	}

	@Override
	protected void initialize() {
		Robot.driveTrain.mecanumDrive(0, 0, 0);
		initialGyroAngle = Robot.driveTrain.getAngle();
		Robot.driveTrain.resetGyro();
		
		
	}

	@Override
	protected void interrupted() {
	}

	@Override
	protected boolean isFinished() {
		
			return jiggleCount <= 0;
	}

}