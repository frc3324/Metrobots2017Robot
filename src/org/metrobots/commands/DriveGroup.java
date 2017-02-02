package org.metrobots.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveGroup extends CommandGroup {
	public DriveGroup() {
		
		this.addParallel(new MecanumDrive());
		this.addParallel(new IntakeFuel());
		this.addParallel(new Climb());
		this.addParallel(new FuelLaunch());
		
	}
	
}
