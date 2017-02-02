package org.metrobots.commands;

import org.metrobots.MetroXboxController;
import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climb extends Command{
	
	public Climb()
	{
		requires((Subsystem)Robot.climb);
	}

	protected void execute() {
    	Robot.climb.climb(Robot.cont1.getButton(MetroXboxController.BUTTON_Y));
    	//Robot.climb.climb(Robot.cont1.getButton(MetroXboxController.Button_A));
	}	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
