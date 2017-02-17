package org.metrobots;

import org.metrobots.commands.DriveGroup;
import org.metrobots.commands.auto.modes.CrossBaseline;
import org.metrobots.subsystems.Climber;
import org.metrobots.subsystems.DriveTrain;
import org.metrobots.subsystems.Scrounger;
import org.metrobots.subsystems.Shooter;
import org.metrobots.util.MetroGamepad;
import org.metrobots.util.OpticalEncoder;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;




import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;

import java.io.IOException;

import org.metrobots.botcv.communication.CommInterface;

/**
 * Main robot code<br>
 * <br>
 * 
 * Feb 5: Refactored. Also, added Javadocs - Cameron
 * 
 * Feb 8: Added agitator Talon and stuff - Xander
 * 
 */
public class Robot extends IterativeRobot {

	/*
	 * Declare gamepad objects
	 */
	public static MetroGamepad motionGamepad;
	public static MetroGamepad mechanismGamepad;
	public static CommInterface comms;

	/*
	 * Declare CANTalon (TalonSRX) objects
	 */
	public CANTalon flMotor;
	public CANTalon blMotor;
	public CANTalon frMotor;
	public CANTalon brMotor;
	public CANTalon intakeMotor;
	public CANTalon climbMotor;
	public CANTalon launchMotor;
	public CANTalon feederMotor;
	public CANTalon agitatorMotor;

	/*
	 * Declare sensor objects
	 */
	public OpticalEncoder shooterEncoder;
	public static AHRS navx;

	/*
	 * Declare subsystems for the robot
	 */
	public static DriveTrain driveTrain;
	public static Scrounger intake;
	public static Shooter shooter;
	public static Climber climber;

	/**
	 * When the robot first boots up, initialize all of the gamepads, motor
	 * controllers, sensors, and subsystems
	 */
	public void robotInit() {
		/*
		 * Initialize gamepads
		 */
		motionGamepad = new MetroGamepad(Constants.motionGamepadPort);
		mechanismGamepad = new MetroGamepad(Constants.mechanismGamepadPort);

		/*
		 * Initialize CANTalons (TalonSRX's)
		 */
		flMotor = new CANTalon(Constants.flMotorPort);
		blMotor = new CANTalon(Constants.blMotorPort);
		frMotor = new CANTalon(Constants.frMotorPort);
		brMotor = new CANTalon(Constants.brMotorPort);
		intakeMotor = new CANTalon(Constants.intakeMotorPort);
		climbMotor = new CANTalon(Constants.climbMotorPort);
		launchMotor = new CANTalon(Constants.launchMotorPort);
		feederMotor = new CANTalon(Constants.feederMotorPort);
		agitatorMotor = new CANTalon(Constants.agitatorMotorPort);
		

		/*
		 * Initialize sensors
		 */
		shooterEncoder = new OpticalEncoder(Constants.opticalEncoderPort);
		navx = new AHRS(Constants.navxPort);

		/*
		 * Initialize subsystems
		 */
		driveTrain = new DriveTrain(flMotor, blMotor, frMotor, brMotor, navx);
		intake = new Scrounger(intakeMotor);
		climber = new Climber(climbMotor);
		shooter = new Shooter(launchMotor, feederMotor, agitatorMotor, shooterEncoder);
		
		CallHandler callHandler = new CallHandler();
		try {
			System.out.println(callHandler);
			Client client = new Client("127.0.0.1", 5800, callHandler);
			comms = (CommInterface) client.getGlobal(CommInterface.class);
		} catch (IOException e) {
			System.err.println("Could not establish communications with tablet!");
			e.printStackTrace();
		}

	}

	/**
	 * Initialize whatever you need to when the robot becomes disables
	 */
	public void disabledInit() {
	}

	/**
	 * Runs constantly when the robot is disabled. Good for displaying stuff to
	 * the driver station
	 */
	public void disabledPeriodic() {
		Scheduler.getInstance().run(); // Run scheduler
		//System.out.println("RPM:" + shooter.getRPM()); // Print shooter RPM
	}

	/**
	 * Initialize whatever you need to when the robot starts autonomous
	 */
	public void autonomousInit() {
		driveTrain.resetGyro();
		driveTrain.setFieldOriented(true);
		Scheduler.getInstance().add(new CrossBaseline());
		
		
	}

	/**
	 * Runs constantly when autonomous is enabled
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run(); // Run scheduler
	}

	/**
	 * Initialize whatever you need to when the robot starts teleop
	 */
	public void teleopInit() {
		driveTrain.setIsHoldingAngle(true);
		Scheduler.getInstance().add(new DriveGroup()); // Add DriveGroup to
														// scheduler
	}

	/**
	 * Runs constantly when teleop is enabled
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run(); // Run Scheduler
		System.out.println("holding angle: " + driveTrain.isHoldingAngle);
		
		//System.out.println("RPM:" + shooter.getRPM()); // Print shooter RPM
	}

	/**
	 * Runs constantly when test mode is enabled
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
