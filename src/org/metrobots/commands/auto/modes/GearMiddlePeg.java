package org.metrobots.commands.auto.modes;

import org.metrobots.commands.auto.Brake;
import org.metrobots.commands.auto.DriveForward;
import org.metrobots.commands.auto.Pause;
import org.metrobots.commands.auto.PlaceGearTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearMiddlePeg extends CommandGroup {
	public GearMiddlePeg() {
		this.addSequential(new DriveForward(-0.25, 1.25));
		this.addSequential(new Pause(0.15)); //was commented out
		this.addSequential(new PlaceGearTime(7.5));
		this.addSequential(new Pause(0.15)); //was commented out
		this.addSequential(new DriveForward(-0.25, 1));
		this.addSequential(new Brake(true));
		//this.addSequential(new ShootGear());
	}
}
