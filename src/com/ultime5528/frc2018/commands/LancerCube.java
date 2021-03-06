package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LancerCube extends Command {

	private double speed;
	
    public LancerCube(double speed) {
    	super("LancerCube");
        requires(Robot.intake);
        this.speed = speed;
        setTimeout(0.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ledController.setModeCurrentPeriod();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.lancer(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
