
package org.metrobots;

import org.metrobots.commands.DriveGroup;
import org.metrobots.subsystems.Climber;
import org.metrobots.subsystems.DriveTrain;
import org.metrobots.subsystems.Intake;
import org.metrobots.subsystems.Shooter;
import org.metrobots.util.OpticalEncoder;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	
	public static MetroXboxController cont1;
	public CANTalon flMotor;
	public CANTalon blMotor;
	public CANTalon frMotor;
	public CANTalon brMotor;
	public CANTalon intakeMotor;
	public CANTalon climbMotor;
	public CANTalon launchMotor;
	public AnalogInput shooterRPM;
	public OpticalEncoder clicker;
	public static AHRS navx;
	public CANTalon feederMotor;

	
	public static DriveTrain driveTrain;
	public static Intake intake;
	public static Shooter launch;
	public static Climber climb;
	
	
    public void robotInit() {
    
    	cont1 = new MetroXboxController(0);
    	flMotor = new CANTalon(Constants.flMotorPort);
    	blMotor = new CANTalon(Constants.blMotorPort);
    	frMotor = new CANTalon(Constants.frMotorPort);
    	brMotor = new CANTalon(Constants.brMotorPort);
    	climbMotor = new CANTalon(Constants.climbMotorPort);
    	launchMotor = new CANTalon(Constants.launchMotorPort);
    	feederMotor = new CANTalon (Constants.feederMotorPort);
    	
    	navx = new AHRS(SPI.Port.kMXP);
    	
    	intakeMotor = new CANTalon(Constants.intakeMotorPort);
    	
    	driveTrain = new DriveTrain(flMotor, blMotor, frMotor, brMotor);
    	intake = new Intake(intakeMotor);
    	climb = new Climber(climbMotor);
    	launch = new Shooter(launchMotor, feederMotor);
    	shooterRPM = new AnalogInput(0);
    	clicker = new OpticalEncoder(shooterRPM);
    	
    	
    	
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		System.out.println("encoder: " + clicker.clicks);
		System.out.println("lowLightLevel: " + clicker.lowLightLevel);
		System.out.println("currentLightLevel: " + clicker.opticalEncoder.getValue());
		clicker.setRPM();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	
    	Scheduler.getInstance().add(new DriveGroup());
    	
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        System.out.println(clicker.clicks);
		clicker.setRPM();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
