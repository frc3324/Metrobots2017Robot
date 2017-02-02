package org.metrobots.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{
	
	public static SpeedController launch;
	
	public Shooter(SpeedController s)
	{
		launch = s;
	}
	
	public void launch (double speed)
	{
		launch.set(speed);
		/*if (out)
		{
			launch.set(1.0);
		}
		else
		{
			launch.set(0);
		}*/
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
