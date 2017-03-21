package org.metrobots;

import java.io.IOException;

import org.metrobots.botcv.communication.CommInterface;
import org.metrobots.commands.DriveGroup;
import org.metrobots.commands.auto.modes.CrossBaseline;
import org.metrobots.commands.auto.modes.CrossBaselineGearLeftPeg;
import org.metrobots.commands.auto.modes.GearMiddlePeg;
import org.metrobots.commands.auto.modes.ShootFuelRightCrossBaseline;
import org.metrobots.subsystems.Climber;
import org.metrobots.subsystems.DriveTrain;
import org.metrobots.subsystems.GearRod;
import org.metrobots.subsystems.Scrounger;
import org.metrobots.subsystems.Shooter;
import org.metrobots.util.MetroGamepad;
import org.metrobots.util.OpticalEncoder;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;

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
	 * Declare Pnuematic jazz
	 */
	public DoubleSolenoid gearPusher;
	public Compressor compressor;

	/*
	 * Declare subsystems for the robot
	 */
	public static DriveTrain driveTrain;
	public static Scrounger intake;
	public static Shooter shooter;
	public static Climber climber;
	public static GearRod gearMech;
	
	public String autoType = "LEFTGEAR";

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
		 * Initialize Pneumatic stuff
		 */
		compressor = new Compressor(0);
		compressor.setClosedLoopControl(true);
		gearPusher = new DoubleSolenoid(Constants.gearPushPort, Constants.gearPullPort);

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
		gearMech = new GearRod(gearPusher);
		
		
		try {
			CallHandler callHandler = new CallHandler();
			System.out.println(callHandler);
			Client client = new Client("10.33.24.50", 5800, callHandler);
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
		
		if (motionGamepad.getButton(MetroGamepad.BUTTON_A)) {
			autoType = "CROSSBASELINE";
		} else if (motionGamepad.getButton(MetroGamepad.BUTTON_B)) {
			autoType = "MIDDLEGEAR";
		} else if (motionGamepad.getButton(MetroGamepad.BUTTON_X)) {
			autoType = "SHOOTRIGHT";
		} else if (motionGamepad.getButton(MetroGamepad.BUTTON_Y)) {
			autoType = "LEFTGEAR";
		}
		
		//System.out.println("Autotype: " + autoType);
		
	}

	/**
	 * Initialize whatever you need to when the robot starts autonomous
	 */
	public void autonomousInit() {
		driveTrain.setFieldOriented(true);
		driveTrain.setIsHoldingAngle(true);
		driveTrain.resetHoldAngle();
		if (autoType.equals("CROSSBASELINE")) {
			Scheduler.getInstance().add(new CrossBaseline());
		} else if (autoType.equals("MIDDLEGEAR")) {
			Scheduler.getInstance().add(new GearMiddlePeg());
		} else if (autoType.equals("SHOOTRIGHT")) {
			Scheduler.getInstance().add(new ShootFuelRightCrossBaseline());
		} else if (autoType.equals("LEFTGEAR")) {
			Scheduler.getInstance().add(new CrossBaselineGearLeftPeg());
		} else {
			Scheduler.getInstance().add(new CrossBaseline());
		}
		
	}

	/**
	 * Runs constantly when autonomous is enabled
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run(); // Run scheduler
		System.out.println("dir: " + comms.getDirection() + " mag:" + comms.getMagnitude());
	}

	/**
	 * Initialize whatever you need to when the robot starts teleop
	 */
	public void teleopInit() {
		driveTrain.setFieldOriented(true);
		driveTrain.setIsHoldingAngle(true);
		driveTrain.resetHoldAngle();
		Scheduler.getInstance().add(new DriveGroup()); // Add DriveGroup to
		shooter.setTargetSpeed(0);
		//Constants.kFlywheelSpeed = SmartDashboard.getNumber("flywheelspeed");
														// scheduler
	}

	/**
	 * Runs constantly when teleop is enabled
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run(); // Run Scheduler
		//System.out.println("flywheelspeed:" + Constants.kFlywheelSpeed);
		//System.out.println("RPM:" + shooter.getRPM() + " with speed " + Constants.kFlywheelSpeed); // Print shooter RPM
		//Constants.kFlywheelSpeed = SmartDashboard.getNumber("flywheelspeed");
		//System.out.println(comms.getStatusMagnitude());
		//System.out.println(comms.getStatusDirection();)
		
		
//		int commsOutput = comms.getDirection(); //was comms.getDirection()
//		if (commsOutput == 1) {
//			System.out.println("Vision Output: Move Right " + comms.getMagnitude());
//		} else if (commsOutput == 0) {
//			System.out.println("Vision Output: Don't Move");
//		} else if (commsOutput == -1) {
//			System.out.println("Vision Output: Move Left " + comms.getMagnitude());
//		} else {
//			System.out.println("No Contours");
//		}
		
		/*
		//System.out.println("holding angle: " + driveTrain.isHoldingAngle);
		 */
		//System.out.println("RPM:" + shooter.getRPM());
		
		//SmartDashboard.putNumber("RPM", shooter.getRPM());
		SmartDashboard.putNumber("targetAngle", driveTrain.getTargetAngle());
		SmartDashboard.putNumber("idk", driveTrain.getAngle());
		
		//System.out.println("RPM:" + shooter.getRPM()); // Print shooter RPM
		 
	}

	/**
	 * Runs constantly when test mode is enabled
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
