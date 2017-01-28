package org.metrobots.commands;

import org.metrobots.MetroXboxController;
import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeFuel extends Command{

	public IntakeFuel()
	{
		requires((Subsystem)Robot.intake);
	}
	
    protected void execute() {
    	Robot.intake.intake(Robot.cont1.getButton(MetroXboxController.RB));
    }
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
