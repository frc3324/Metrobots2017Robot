package org.metrobots.commands;

import org.metrobots.MetroXboxController;
import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MecanumDrive extends Command {
	
	public boolean isFieldOriented = false;

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
		double driverX = Robot.cont1.getAxis(MetroXboxController.LEFT_X);
		double driverY = Robot.cont1.getAxis(MetroXboxController.LEFT_Y);
		double turn = Robot.cont1.getAxis(MetroXboxController.RIGHT_X);
		double angle = Robot.navx.getAngle() * Math.PI / 180;
		
		if (Robot.cont1.getButton(MetroXboxController.BUTTON_B)) {
			isFieldOriented = true;
		} else if (Robot.cont1.getButton(MetroXboxController.BUTTON_A)) {
			isFieldOriented = false;
		}
		
		if (Robot.cont1.getButton(MetroXboxController.BUTTON_START)) {
			Robot.navx.reset();
		}
		
		double x = 0;
		double y = 0;
		
		
		if (isFieldOriented) {
			x = driverX * Math.cos(angle) - driverY * Math.sin(angle);
			y = driverY * Math.cos(angle) + driverX * Math.sin(angle);
			
		} else {
			x = driverX;
			y = driverY;
		}
		
		Robot.driveTrain.mecanumDrive(x, y, turn);
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
