package org.metrobots.commands.auto.modes;

import org.metrobots.commands.auto.DriveForward;
import org.metrobots.commands.auto.ShootFuel;
import org.metrobots.commands.auto.Strafe;
import org.metrobots.commands.auto.ZeroTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootFuelFillStorageShootFuel extends CommandGroup {
	public ShootFuelFillStorageShootFuel() {
		this.addSequential(new ShootFuel(3, 1));
		this.addSequential(new Strafe(0.5, 1));
		this.addSequential(new ZeroTurn(-90, 0.5));
		this.addSequential(new DriveForward(-0.75, 1));
		this.addSequential(new Strafe(0.5, 1.5));
		this.addSequential(new DriveForward(0, 3));
		this.addSequential(new ShootFuel(3, 1));
	}

}
