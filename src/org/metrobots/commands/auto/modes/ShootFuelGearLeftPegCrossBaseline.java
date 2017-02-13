package org.metrobots.commands.auto.modes;

import org.metrobots.commands.auto.DriveForward;
import org.metrobots.commands.auto.ShootFuel;
import org.metrobots.commands.auto.Strafe;
import org.metrobots.commands.auto.ZeroTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootFuelGearLeftPegCrossBaseline extends CommandGroup {
	public ShootFuelGearLeftPegCrossBaseline() {
		this.addSequential(new ShootFuel(3, 1));
		this.addSequential(new Strafe(0.5, 1));
		this.addSequential(new ZeroTurn(-90, 0.75));
		this.addSequential(new DriveForward(-0.75, 1));
		this.addSequential(new ZeroTurn(60, 0.5));
		this.addSequential(new DriveForward(-0.25, 2));
		this.addSequential(new DriveForward(0, 2));
		this.addSequential(new DriveForward(0.5, 1));
		this.addSequential(new ZeroTurn(120, 0.75));
		this.addSequential(new DriveForward(0.75, 2));
	}
}
