package org.metrobots.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{
	
	public static SpeedController launch;
	public static SpeedController feeder;
	
	public Shooter(SpeedController s, SpeedController f)
	{
		launch = s;
		feeder = f;
	}
	
	public void feed (boolean feed)
	{
		if(feed == true){
			feeder.set(-1.0);
		}
		if(feed == false){
			feeder.set(0);
		}
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
