package org.metrobots.commands.auto.modes;

import org.metrobots.commands.auto.DriveForward;
import org.metrobots.commands.auto.Pause;
import org.metrobots.commands.auto.PlaceGear;
import org.metrobots.commands.auto.ZeroTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossBaselineGearLeftPeg extends CommandGroup {
	public CrossBaselineGearLeftPeg() {
		this.addSequential(new DriveForward(-0.25, 2.3));
		this.addSequential(new Pause(0.15));
		this.addSequential(new ZeroTurn(60, 0.5));
		this.addSequential(new Pause(0.5));
		this.addSequential(new PlaceGear());
		this.addSequential(new DriveForward(-0.25, 0.8));
	}
}
