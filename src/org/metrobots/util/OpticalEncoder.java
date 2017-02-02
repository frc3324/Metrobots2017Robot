package org.metrobots.util;

import edu.wpi.first.wpilibj.AnalogInput;

public class OpticalEncoder {
	
	public AnalogInput opticalEncoder;
	
	public int clicks = 0;
	public int lowLightLevel = 0;
	public int significantDiff = 300;
	
	public boolean trigger;
	
	public OpticalEncoder(AnalogInput oe){
		opticalEncoder = oe;
		lowLightLevel = opticalEncoder.getValue();
	}
	
	public int getRPM() {
		return clicks * 60;
	}
	
	public void setRPM() {
		
		if (opticalEncoder.getValue() >= lowLightLevel - significantDiff && trigger == false) {
			trigger = true;
			clicks++;
		}
		if (trigger == true && opticalEncoder.getValue() < lowLightLevel - significantDiff) {
			trigger = false;
		}
	}
}
