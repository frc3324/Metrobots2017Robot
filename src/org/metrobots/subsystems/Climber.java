package org.metrobots.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem{
	
	public static SpeedController climb;
	
	public Climber(SpeedController c)
	{
		climb = c;
	}
	
	public void launch (boolean up)
	{
		if (up)
		{
			climb.set(1.0);
		}
		else
		{
			climb.set(0);
		}
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public void climb(boolean button) {
		// TODO Auto-generated method stub
		
	}

}
