package org.metrobots.util;

import org.metrobots.Constants;

import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.AnalogTriggerOutput;
import edu.wpi.first.wpilibj.Counter;

/**
 * Class for using a light/color sensor as an encoder<br>
 * <br>
 * 
 * Feb 5: Refactored. Also, added Javadocs - Cameron
 * Feb 7: Fixed OpticalEncoder object
 *
 */
public class OpticalEncoder {

	public static AnalogTrigger trigger;
	public static Counter counter;
	public static int lower = Constants.opticalEncoderLow;
	public static int upper = Constants.opticalEncoderHigh;

	/**
	 * Optical Encoder object
	 * 
	 * @param channel
	 *            analog port of the optical encoder
	 */
	public OpticalEncoder(int channel) {
		trigger = new AnalogTrigger(channel);
		trigger.setLimitsRaw(lower, upper);
		counter = new Counter(new AnalogTriggerOutput(trigger, AnalogTriggerOutput.AnalogTriggerType.kInWindow));
		
	}

	/**
	 * Read current RPM returned from sensor
	 * 
	 * @return RPM
	 */
	public double getRPM() {
		return 60 / counter.getPeriod();
	}
}
