package org.metrobots.commands.auto.modes;

import org.metrobots.commands.auto.DriveForward;
import org.metrobots.commands.auto.ZeroTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossBaselineGearMiddlePeg extends CommandGroup {
	public CrossBaselineGearMiddlePeg() {
		this.addSequential(new DriveForward(-0.5, 0.75));
		this.addSequential(new DriveForward(0, 2));
		this.addSequential(new DriveForward (0.5, 0.25));
		this.addSequential(new ZeroTurn(45, 0.5));
		this.addSequential(new DriveForward(0.75, 3));
	}
}
