package org.metrobots.commands.auto;

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PlaceGear extends Command {
	
	public int lastVisionDirection = 1;
	public int lastVisionMagnitude = 1;
	

	public PlaceGear () {
		requires((Subsystem)Robot.driveTrain);
	}
	
	public void mecanumDrive(double x, double y, double turn) {
		
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.driveTrain.mecanumDrive(0, 0, 0);
		
	}

	//@Override
	/*protected void execute() 
		Robot.comms.getFiringStatus();
		Robot.comms.getDirection();
		Robot.comms.getMagnitude();*/
		/*double visionSpeed = 0.0;
		int visionDirection = org.metrobots.Robot.comms.getDirection();
		int visionMagnitude = org.metrobots.Robot.comms.getMagnitude();
		
		if ((visionDirection) != 0) {
			if (visionDirection == 1) { //move robot right
				visionSpeed = 0.5;
				mecanumDrive(1.0, 0.0, 0.0);
			}
		
			else if (visionDirection == -1) { //move robot left
				visionSpeed = 0.5;
				mecanumDrive(-1.0, 0.0, 0.0);
			}
			
			//mecanumDrive(0, visionSpeed, 0);
		
		} else { //don't move left or right; ready to move forward
			//double visionSpeed = 0;
			if (visionMagnitude == 3) { //move robot forward fast speed
				visionSpeed = 0.75;
				
				mecanumDrive(0.0, 1.0, 0.0);
			}
		
			else if (visionMagnitude == 2) { //move robot forward medium speed
				visionSpeed = 0.50;
				mecanumDrive(0.0, 1.0, 0.0);
			}
		
			else if (visionMagnitude == 1) { //move robot forward low speed
				visionSpeed = 0.25;
				mecanumDrive(0.0, 1.0, 0.0);
			}
		
			else if (visionMagnitude == 0) { //don't move robot forward
				visionSpeed = 0.0;
			}
			
			mecanumDrive(visionSpeed, 0, 0);
			
			}
		
		lastVisionDirection = visionDirection;
		lastVisionMagnitude = visionMagnitude;
		
		}
	
	@Override
	protected boolean isFinished() {
		if (lastVisionDirection == 0 && lastVisionMagnitude == 0){
			return true;
		} else {
			return false;
		}
	}*/

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
