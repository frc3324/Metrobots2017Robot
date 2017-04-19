package org.metrobots.commands.auto.modes;

import org.metrobots.commands.auto.Brake;
import org.metrobots.commands.auto.DriveForward;
import org.metrobots.commands.auto.DriveForwardDistance;
import org.metrobots.commands.auto.Pause;
import org.metrobots.commands.auto.PlaceGearTime;
import org.metrobots.commands.auto.ShootGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearMiddlePegEncodersNoV extends CommandGroup {
	public GearMiddlePegEncodersNoV() {
		this.addSequential(new DriveForwardDistance(-0.5, 800));
		this.addSequential(new Brake(true));
		this.addSequential(new Pause(0.5)); //was commented out
		this.addSequential(new ShootGear());
		this.addSequential(new Pause(0.75));
		this.addSequential(new DriveForward(0.5, 0.5));
		this.addSequential(new Brake(true));
		//this.addSequential(new ShootGear());
	}
}
