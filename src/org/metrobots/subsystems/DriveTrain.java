package org.metrobots.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	public static CANTalon flMotor, blMotor, frMotor, brMotor;
	
	public RobotDrive robotDrive;
	
	public DriveTrain(CANTalon flMotor2, CANTalon blMotor2, CANTalon frMotor2, CANTalon brMotor2) {
		
		flMotor = flMotor2;
		blMotor = blMotor2;
		frMotor = frMotor2;
		brMotor = brMotor2;
		
		robotDrive = new RobotDrive(flMotor, blMotor, frMotor, brMotor);
		
	}
	
	public void tankDrive(double left, double right){
		flMotor.set(left);
		blMotor.set(left);
		frMotor.set(right);
		brMotor.set(right);
		
	}
	
	public void mecanumDrive(double x, double y, double turn){
		flMotor.set(x + y + turn);
		blMotor.set(-x + y + turn);
		frMotor.set(x - y + turn);
		brMotor.set(-x - y + turn);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
    
	


   
    
}

