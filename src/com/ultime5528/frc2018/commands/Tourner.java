package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.Robot;
import com.ultime5528.frc2018.subsystems.BasePilotable;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Tourner extends Command {
	private double vitesse, vitesseBrake;
	private double angle;
	private boolean brake;
		
	public Tourner(double angle, double vitesse, double vitesseBrake){
		
		super("Tourner");
		
	    requires(Robot.basePilotable);
	    
	    this.angle = angle;
	    this.vitesse = Math.signum(angle) * Math.abs(vitesse);
	    this.vitesseBrake = Math.signum(angle) * -1 * Math.abs(vitesseBrake);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.basePilotable.resetGyro();
    	brake = false;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.basePilotable.getAngleSpeedFilter().pidGet();
    	
    	if (Math.abs(Robot.basePilotable.getHeading()) > Math.abs(angle) ){
    		brake = true;
    	}

    	
    	if(brake){
    		Robot.basePilotable.tankDrive(vitesseBrake, vitesseBrake * -1);
    	}
    	else{
    		Robot.basePilotable.tankDrive(vitesse, vitesse * -1);
    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return  brake && Math.signum(vitesseBrake);
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.basePilotable.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
