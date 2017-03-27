package org.metrobots.commands.auto.modes;

import org.metrobots.commands.auto.DriveForward;
import org.metrobots.commands.auto.Pause;
import org.metrobots.commands.auto.PlaceGear;
import org.metrobots.commands.auto.ZeroTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossBaselineGearMiddlePeg extends CommandGroup {
	public CrossBaselineGearMiddlePeg() {
		this.addSequential(new DriveForward(-0.25, 1));
		this.addSequential(new Pause(0.15));
		//this.addSequential(new DriveForward(0, 2));
		this.addSequential(new PlaceGear());
		this.addSequential(new Pause(0.15));
		this.addSequential(new DriveForward(-0.25, 0.5));
	}
}
