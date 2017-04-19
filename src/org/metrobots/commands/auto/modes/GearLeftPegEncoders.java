package org.metrobots.commands.auto.modes;

import org.metrobots.commands.auto.Brake;
import org.metrobots.commands.auto.DriveForward;
import org.metrobots.commands.auto.DriveForwardDistance;
import org.metrobots.commands.auto.DriveForwardUltrasonic;
import org.metrobots.commands.auto.Pause;
import org.metrobots.commands.auto.PlaceGear;
import org.metrobots.commands.auto.PlaceGearTime;
import org.metrobots.commands.auto.ShootGear;
import org.metrobots.commands.auto.ZeroTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearLeftPegEncoders extends CommandGroup {
	public GearLeftPegEncoders() {
		this.addSequential(new DriveForwardDistance(-0.5, 1000));
		this.addSequential(new Pause(0.15));
		this.addSequential(new ZeroTurn(60, 0.5));
		this.addSequential(new Pause(0.75));
		this.addSequential(new PlaceGearTime(7.5));
		this.addSequential(new DriveForwardUltrasonic(-0.25, 6)); //was 1
		this.addSequential(new Brake(true));
		//this.addSequential(new ShootGear());
	}
}
