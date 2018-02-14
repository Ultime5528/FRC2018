package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.Robot;
import com.ultime5528.frc2018.util.LinearInterpolator;
import com.ultime5528.frc2018.util.Point;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class SetElevateur extends Command {
	
	private double hauteur;
	private LinearInterpolator interpolator;
	
	public SetElevateur(double hauteur) {
        super("SetElevateur");
		Point[] points = new Point[] {
				new Point(0,0),
				new Point(0,0)
		};
		
		interpolator = new LinearInterpolator(points);
		this.hauteur = hauteur;
        requires(Robot.elevateur);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevateur.disable();
    	//Robot.elevateur.setSetpoint(hauteur);
    	//Robot.elevateur.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.elevateur.getHauteur() < hauteur){
    		Robot.elevateur.monter();
    	}
    	else{ 
    		Robot.elevateur.descendre();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {	
        return Math.abs(hauteur - Robot.elevateur.getHauteur()) <= 0.02;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevateur.setSetpoint(Robot.elevateur.getHauteur());
    	Robot.elevateur.enable();
    	//Robot.elevateur.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
