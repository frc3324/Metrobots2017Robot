package org.metrobots.commands.teleop;

import org.metrobots.subsystems.GearRod;
import org.metrobots.util.MetroGamepad;

import edu.wpi.first.wpilibj.command.Command;

public class GearPlacement extends Command {
	private GearRod gearRod;
	private MetroGamepad gamepad;
	
	public GearPlacement(GearRod gr, MetroGamepad mgp) {
		gearRod = gr;
		gearRod.enable();
		gamepad = mgp;
	}
	
	public void execute () {
		if (gamepad.getButton(gamepad.LT)) {
			//GearRod.
		}
	}
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
}
