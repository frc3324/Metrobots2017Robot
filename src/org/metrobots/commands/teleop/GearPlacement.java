//this code was causing compiling warnings and preventing it from compiling
package org.metrobots.commands.teleop;

import org.metrobots.subsystems.GearRod;
import org.metrobots.util.MetroGamepad;

import edu.wpi.first.wpilibj.command.Command;

public class GearPlacement extends Command {
	private GearRod gearMech;
	private MetroGamepad gamepad;
	
	public GearPlacement(GearRod gear, MetroGamepad mgp) {
		gearMech = gear;
		gamepad = mgp;
	}
	
	public void execute () {
		if (gamepad.getButton(MetroGamepad.RIGHT_CLICK)) {
			gearMech.ejectGear();
		} else if (gamepad.getButton(MetroGamepad.LEFT_CLICK)) {
			gearMech.unejectGear();
		} else {
			gearMech.disablePusher();
		}
	}
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void initialize() {}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}
}