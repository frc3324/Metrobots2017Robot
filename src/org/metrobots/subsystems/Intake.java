package org.metrobots.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{

	public static SpeedController intake;
	
	public Intake(SpeedController i)
	{
		intake = i;	
	}
	
	public void intake (boolean in)
	{
		if (in)
		{
			intake.set(1.0);
		}
		else
		{
			intake.set(0);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
