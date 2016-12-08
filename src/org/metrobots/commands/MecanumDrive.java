package org.metrobots.commands;

import org.metrobots.MetroXboxController;
import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MecanumDrive extends Command {

	public MecanumDrive() {
		requires((Subsystem)Robot.driveTrain);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.driveTrain.mecanumDrive(0.0, 0.0, 0.0);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.driveTrain.mecanumDrive(Robot.cont1.getAxis(MetroXboxController.LEFT_X), Robot.cont1.getAxis(MetroXboxController.LEFT_Y), Robot.cont1.getAxis(MetroXboxController.RIGHT_X));
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
