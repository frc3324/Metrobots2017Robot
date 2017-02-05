package org.metrobots.commands;

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Group of commands that need to be run during teleop<br><br>
 * 
 * Feb 5: Refactored. Also, added Javadocs - Cameron
 *
 */
public class DriveGroup extends CommandGroup {
	/**
	 * Adds 4 commands to be run in parallel:<br>
	 * &emsp;MecanumDrive<br>
	 * &emsp;IntakeFuel<br>
	 * &emsp;Climb<br>
	 * &emsp;LaunchFuel<br>
	 */
	public DriveGroup() {
		
		this.addParallel(new MecanumDrive(Robot.driveTrain, Robot.motionGamepad));
		this.addParallel(new IntakeFuel(Robot.intake, Robot.mechanismGamepad));
		this.addParallel(new Climb(Robot.climber, Robot.mechanismGamepad));
		this.addParallel(new LaunchFuel(Robot.shooter, Robot.mechanismGamepad));
		
	}
	
}
