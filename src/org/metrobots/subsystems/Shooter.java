package org.metrobots.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{
	
	public static SpeedController launch;
	public static SpeedController feeder;
	public static Counter count;
	//public static PIDController pid;
	
	public Shooter(SpeedController s, SpeedController f, Counter c)
	{
		launch = s;
		feeder = f;
		count = c;
		/*pid = p;
	
		pid.enable();*/
	
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
	public void launch (int targetRPM)
	{
		if (targetRPM != 0) {
			launch.set((targetRPM + 15*(targetRPM - (60 / count.getPeriod()))) / 5800);
		}
		
		else {
			launch.set(0);
		}

		/*pid.setSetpoint(60 / (shooterRPM.getPeriod()));
		pid.*/
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
