package org.metrobots.commands.auto.modes;

import org.metrobots.commands.auto.ShootFuel;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootFuelRightCrossBaseline extends CommandGroup {
	public ShootFuelRightCrossBaseline() {
		this.addSequential(new ShootFuel(3, 0.675));
		/*this.addSequential(new Strafe(-0.5, 1));
		this.addSequential(new ZeroTurn(90, 0.5));
		this.addSequential(new DriveForward(0.5, 2));*/
	}
}