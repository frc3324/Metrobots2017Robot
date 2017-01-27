package org.metrobots.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
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
	
	public void mecanumDrive(double leftX, double leftY, double rightX){
		robotDrive.mecanumDrive_Cartesian(leftX, leftY, rightX, 0.0);
		
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
    
	


   
    
}

