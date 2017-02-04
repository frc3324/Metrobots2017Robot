package org.metrobots.util;

import edu.wpi.first.wpilibj.AnalogInput;

public class OpticalEncoder {
	
	public AnalogInput opticalEncoder;
	
	public int clicks = 0;
	public int lowLightLevel = 2000;
	public int significantDiff = 30;
	
	public boolean trigger;
	
	public OpticalEncoder(AnalogInput oe){
		opticalEncoder = oe;
	}
	
	public int getRPM() {
		return clicks * 60;
	}
	
	public void setRPM() {
		
		if (opticalEncoder.getValue() <= lowLightLevel && !trigger ) {
			trigger = true;
			clicks++;
		}
		if (trigger && opticalEncoder.getValue() > lowLightLevel ) {
			trigger = false;
		}
	}
}
