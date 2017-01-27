package org.metrobots.commands;

import org.metrobots.MetroXboxController;
import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FuelLaunch extends Command{
	
	public FuelLaunch()
	{
		requires((Subsystem)Robot.launch);
	}

	protected void execute() {
    	Robot.launch.launch(Robot.cont1.getButton(MetroXboxController.LB));
	}	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
