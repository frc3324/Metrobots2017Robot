package org.metrobots.commands.auto.modes;

import org.metrobots.commands.auto.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossBaseline extends CommandGroup {
	public CrossBaseline() {
		this.addSequential(new DriveForward(0.5, 2));
	}
}
