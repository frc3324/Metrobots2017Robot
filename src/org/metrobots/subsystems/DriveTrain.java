package org.metrobots.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	public static Talon flMotor, blMotor, frMotor, brMotor;
	
	public RobotDrive robotDrive;
	
	public DriveTrain(Talon flMotor_, Talon blMotor_, Talon frMotor_, Talon brMotor_) {
		
		flMotor = flMotor_;
		blMotor = blMotor_;
		frMotor = frMotor_;
		brMotor = brMotor_;
		
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

