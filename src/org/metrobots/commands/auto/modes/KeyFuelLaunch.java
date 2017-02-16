package org.metrobots.commands.auto.modes;

import org.metrobots.commands.auto.ShootFuel;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class KeyFuelLaunch extends CommandGroup {
	public KeyFuelLaunch() {
		this.addSequential(new ShootFuel(3, 1));
	}
}
