package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DemarrerElevateur extends Command {

    public DemarrerElevateur() {
        super("DemarrerElevateur");
    	// Use requires() here to declare subsystem dependencies
        requires(Robot.elevateur);
        setTimeout(1.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevateur.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevateur.tendre();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevateur.resetEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
