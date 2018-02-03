package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class SetElevateur extends Command {
	
	private double hauteur;
    
	public SetElevateur(double hauteur) {
        this.hauteur = hauteur;
        requires(Robot.elevateur);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevateur.setSetpoint(hauteur);
    	Robot.elevateur.enable();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {	
        return Robot.elevateur.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevateur.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
