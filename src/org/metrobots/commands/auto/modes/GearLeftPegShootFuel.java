package org.metrobots.commands.auto.modes;

import org.metrobots.Constants;
import org.metrobots.commands.auto.Brake;
import org.metrobots.commands.auto.DriveForward;
import org.metrobots.commands.auto.DriveForwardDistance;
import org.metrobots.commands.auto.DriveForwardUltrasonic;
import org.metrobots.commands.auto.FeedAgitateFuel;
import org.metrobots.commands.auto.Pause;
import org.metrobots.commands.auto.PlaceGear;
import org.metrobots.commands.auto.PlaceGearTime;
import org.metrobots.commands.auto.ShootGear;
import org.metrobots.commands.auto.SpinFlywheel;
import org.metrobots.commands.auto.ZeroTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearLeftPegShootFuel extends CommandGroup {
	public GearLeftPegShootFuel() {
		this.addSequential(new DriveForwardDistance(-0.5, 800));
		this.addParallel(new Brake(true));
		this.addSequential(new Pause(0.15));
		this.addSequential(new ZeroTurn(50, 0.5));
		this.addSequential(new Pause(0.75));
		this.addSequential(new PlaceGearTime(7.5));
		this.addSequential(new DriveForwardDistance(-0.25, 300)); //was 1
		this.addSequential(new Brake(true));
		this.addSequential(new Pause(0.25));
		this.addParallel(new ShootGear());
		this.addSequential(new Pause(0.5));
		this.addSequential(new DriveForward(0.5, 2));
		this.addSequential(new Pause(0.5));
		this.addSequential(new SpinFlywheel(Constants.airshipShootingRPM));
		this.addSequential(new Pause(1.0));
		this.addSequential(new FeedAgitateFuel(true));
		//this.addSequential(new ShootGear());
	}
}
