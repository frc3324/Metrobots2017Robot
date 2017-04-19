package org.metrobots;

import java.io.IOException;

import org.metrobots.botcv.communication.CommInterface;
import org.metrobots.commands.DriveGroup;
import org.metrobots.commands.auto.modes.CrossBaseline;
import org.metrobots.commands.auto.modes.CrossBaselineGearLeftPeg;
import org.metrobots.commands.auto.modes.GearLeftPeg;
import org.metrobots.commands.auto.modes.GearLeftPegEncoders;
import org.metrobots.commands.auto.modes.GearMiddlePeg;
import org.metrobots.commands.auto.modes.GearMiddlePegEncodersNoV;
import org.metrobots.commands.auto.modes.GearRightPeg;
import org.metrobots.commands.auto.modes.GearRightPegEncoders;
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
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
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
	
	public static boolean noVComms = true;
	
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
	public Encoder flEncoder;
	public Encoder blEncoder;
	public Encoder frEncoder;
	public Encoder brEncoder;
	public Encoder newShooterEncoder;
	public static Ultrasonic ultrasonic;
	
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
		
		flEncoder = new Encoder(Constants.flEncoder1, Constants.flEncoder2);
		blEncoder = new Encoder(Constants.blEncoder1, Constants.blEncoder2);
		frEncoder = new Encoder(Constants.frEncoder1, Constants.frEncoder2);
		brEncoder = new Encoder(Constants.brEncoder1, Constants.brEncoder2);
		newShooterEncoder = new Encoder(Constants.shooterEncoder1, Constants.shooterEncoder2);
		ultrasonic = new Ultrasonic(Constants.ultrasonicOut, Constants.ultrasonicIn);
		ultrasonic.setEnabled(true);
		ultrasonic.setAutomaticMode(true);
		
		/*
		 * Initialize subsystems
		 */
		driveTrain = new DriveTrain(flMotor, blMotor, frMotor, brMotor, navx, flEncoder, blEncoder, frEncoder, brEncoder);
		intake = new Scrounger(intakeMotor);
		climber = new Climber(climbMotor);
		shooter = new Shooter(launchMotor, feederMotor, agitatorMotor, newShooterEncoder);
		gearMech = new GearRod(gearPusher);
		
		
		try {
			CallHandler callHandler = new CallHandler();
			System.out.println(callHandler);
			//Client client = new Client("127.0.0.1", 5800, callHandler);
			Client client = new Client("192.168.42.129", 5800, callHandler);
			
			comms = (CommInterface) client.getGlobal(CommInterface.class);
		} catch (IOException e) {
			System.err.println("Could not establish communications with tablet!");
			e.printStackTrace();
			noVComms = true;
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
			System.out.println("Autotype: " + autoType);
		} else if (motionGamepad.getButton(MetroGamepad.BUTTON_B)) {
			autoType = "MIDDLEGEARNOV";
			System.out.println("Autotype: " + autoType);
		} else if (motionGamepad.getButton(MetroGamepad.BUTTON_X)) {
			autoType = "SHOOTRIGHT";
			System.out.println("Autotype: " + autoType);
		} else if (motionGamepad.getButton(MetroGamepad.BUTTON_Y)) {
			autoType = "LEFTGEAR";
			System.out.println("Autotype: " + autoType);
		} else if (motionGamepad.getButton(MetroGamepad.LB)) {
			autoType = "MIDDLEGEARVISION";
			System.out.println("Autotype: " + autoType);
		} else if (motionGamepad.getButton(MetroGamepad.RB)) {
			autoType = "RIGHTGEAR";
			System.out.println("Autotype: " + autoType);
		}
		
		//System.out.println("Ultrasonic" + ultrasonic.getRangeInches());
		
		System.out.println("flE:" + flEncoder.getDistance());
		System.out.println("blE:" + blEncoder.getDistance());
		System.out.println("frE:" + frEncoder.getDistance());
		System.out.println("brE:" + brEncoder.getDistance());
		
		//System.out.println("dir: " + comms.getDirection() + " mag:" + comms.getMagnitude());
		//System.out.println("x: " + comms.getXOffset() + " y:" + comms.getYOffset());
		
		//System.out.println("Autotype: " + autoType);
		
	}

	/**
	 * Initialize whatever you need to when the robot starts autonomous
	 */
	public void autonomousInit() {
		driveTrain.setFieldOriented(true);
		driveTrain.setIsHoldingAngle(true);
		driveTrain.resetHoldAngle();
		driveTrain.setBrakeMode(true);
		gearMech.unejectGear();
		if (autoType.equals("CROSSBASELINE")) {
			Scheduler.getInstance().add(new CrossBaseline());
		} else if (autoType.equals("MIDDLEGEAR")) {
			Scheduler.getInstance().add(new GearMiddlePeg());
		} else if (autoType.equals("SHOOTRIGHT")) {
			Scheduler.getInstance().add(new ShootFuelRightCrossBaseline());
		} else if (autoType.equals("LEFTGEAR")) {
			Scheduler.getInstance().add(new GearLeftPegEncoders());
		} else if (autoType.equals("MIDDLEGEARVISION")) {
			Scheduler.getInstance().add(new GearMiddlePeg());
		} else if (autoType.equals("MIDDLEGEARNOV")) {
			Scheduler.getInstance().add(new GearMiddlePegEncodersNoV());
		} else if (autoType.equals("RIGHTGEAR")) {
			Scheduler.getInstance().add(new GearRightPegEncoders());
		} else {
			Scheduler.getInstance().add(new CrossBaseline());
		}
		
	}

	/**
	 * Runs constantly when autonomous is enabled
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run(); // Run scheduler
		//System.out.println("dir: " + comms.getDirection() + " mag:" + comms.getMagnitude());
		//System.out.println("x: " + comms.getXOffset() + " y:" + comms.getYOffset());
	}
	
	/**
	 * Initialize whatever you need to when the robot starts teleop
	 */
	public void teleopInit() {
		driveTrain.setFieldOriented(true);
		driveTrain.setIsHoldingAngle(false);
		driveTrain.resetHoldAngle();
		driveTrain.setBrakeMode(false);
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
		
		SmartDashboard.putNumber("RPM", shooter.getRPM());
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
