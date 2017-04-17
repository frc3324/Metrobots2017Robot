package org.metrobots.commands.auto.modes;

import org.metrobots.commands.auto.Brake;
import org.metrobots.commands.auto.DriveForward;
import org.metrobots.commands.auto.Pause;
import org.metrobots.commands.auto.PlaceGearTime;
import org.metrobots.commands.auto.ZeroTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearRightPeg extends CommandGroup {
	public GearRightPeg() {
		this.addSequential(new DriveForward(-0.25, 2.3));
		this.addSequential(new Pause(0.15));
		this.addSequential(new ZeroTurn(-60, 0.5));
		this.addSequential(new Pause(0.75));
		this.addSequential(new PlaceGearTime(7.5));
		this.addSequential(new DriveForward(-0.25, 1));
		this.addSequential(new Brake(true));
		//this.addSequential(new ShootGear());
		//this.addSequential(new DriveForward(0, 2));
		//this.addSequential(new DriveForward(0.5, 0.75));
		//this.addSequential(new ZeroTurn(60, 0.5));
		//this.addSequential(new DriveForward(1, 3));
	}
}
