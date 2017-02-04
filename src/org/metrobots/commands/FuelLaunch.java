package org.metrobots.commands;

import org.metrobots.MetroXboxController;
import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FuelLaunch extends Command{
	
	private double speed = 0.0;
	
	public FuelLaunch()
	{
		requires((Subsystem)Robot.launch);
	}

	protected void execute() {
    	//Robot.launch.launch(Robot.cont1.getAxis(MetroXboxController.LT));
		if (Robot.cont1.getButton(MetroXboxController.BUTTON_X)) {
			speed = 0.75;
		} else if (Robot.cont1.getButton(MetroXboxController.BUTTON_Y)) {
			speed = 0;
		}
		Robot.launch.launch(speed);
		Robot.launch.feed(Robot.cont1.getButton(MetroXboxController.BUTTON_B));
	}	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
