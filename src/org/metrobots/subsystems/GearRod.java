package org.metrobots.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearRod extends Subsystem {
	
	public DoubleSolenoid pusher;
	
	public GearRod(DoubleSolenoid pusherCylinder) {
		pusher = pusherCylinder;
	}
	
	@Override
	protected void initDefaultCommand() {}
	
	public void ejectGear() {
		pusher.set(DoubleSolenoid.Value.kForward);
	}
	
	public void disablePusher() {
		pusher.set(DoubleSolenoid.Value.kOff);
	}
	
	public void unejectGear() {
		pusher.set(DoubleSolenoid.Value.kReverse);
	}

	public void enable() {}

}
