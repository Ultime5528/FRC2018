package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrendreCube extends Command {
	
	private boolean tourner = false;
	private long time;
	
    public PrendreCube() {
        super("PrendreCube");
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	tourner = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(tourner) {
    		
    		Robot.intake.tournerCube(); // Tourner le cube sur lui-meme
    		
    		// On le fait tourner pendant 300 millisecondes
    		if(System.currentTimeMillis() - time > 300) {
    			tourner = false;
    			time = System.currentTimeMillis();
    		}
    			
    		
    	}
    	// On ne recommence pas a le faire tourner avant 500 ms, et on verifie le courant dans l'intake
    	else if(System.currentTimeMillis() - time > 500 && (Robot.pdp.getCurrent(6) > 2.2 || Robot.pdp.getCurrent(7) > 4.2)) {
    		
    		tourner = true;
    		Robot.intake.tournerCube();
    		time = System.currentTimeMillis();
    		
    	}
    	else
    		Robot.intake.prendre();
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.intake.hasCube();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.stop();
    	Robot.ledController.setModeCube();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.stop();
    }
}
