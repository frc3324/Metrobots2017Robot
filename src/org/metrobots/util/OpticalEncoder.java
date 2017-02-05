package org.metrobots.util;

import org.metrobots.Constants;

import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Counter;

/**
 * Class for using a light/color sensor as an encoder<br>
 * <br>
 * 
 * Feb 5: Refactored. Also, added Javadocs - Cameron
 *
 */
public class OpticalEncoder extends Counter {

	public static AnalogTrigger trigger;
	public static int lower = Constants.opticalEncoderLow;
	public static int upper = Constants.opticalEncoderHigh;

	/**
	 * Optical Encoder object
	 * 
	 * @param channel
	 *            analog port of the optical encoder
	 */
	public OpticalEncoder(int channel) {
		super(analogTrig(channel));
	}

	/**
	 * Read current RPM returned from sensor
	 * 
	 * @return RPM
	 */
	public double getRPM() {
		return 60 / this.getPeriod();
	}

	/**
	 * Create an analog trigger from a given channel. This is only used when
	 * creating an instance of Optical Encoder
	 * 
	 * @param channel
	 *            analog port of the optical encoder
	 * @return analog trigger object
	 */
	private static AnalogTrigger analogTrig(int channel) {
		trigger = new AnalogTrigger(channel);
		trigger.setLimitsRaw(lower, upper);
		return trigger;
	}
}
