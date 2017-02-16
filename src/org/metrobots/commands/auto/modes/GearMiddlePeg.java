package org.metrobots.commands.auto.modes;

import org.metrobots.commands.auto.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearMiddlePeg extends CommandGroup {
	public GearMiddlePeg() {
		this.addSequential(new DriveForward(-0.5, 0.75));
		this.addSequential(new DriveForward(0, 2));
		this.addSequential(new DriveForward (0.5, 0.25));
	}
}
