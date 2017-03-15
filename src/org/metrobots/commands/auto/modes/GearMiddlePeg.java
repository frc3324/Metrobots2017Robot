package org.metrobots.commands.auto.modes;

import org.metrobots.commands.auto.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearMiddlePeg extends CommandGroup {
	public GearMiddlePeg() {
		this.addSequential(new DriveForward(-0.25, 5));
		//this.addSequential(new DriveForward(0, 2));
		//this.addSequential(new Jiggle(8, 30));
		//this.addSequential(new DriveForward (0.5, 0.25));
	}
}
