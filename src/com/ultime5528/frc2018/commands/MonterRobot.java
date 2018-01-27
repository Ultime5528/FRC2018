package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MonterRobot extends Command {

    public MonterRobot() {
        super("MonterRobot");
    	requires(Robot.grimpeur);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.grimpeur.grimper();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.grimpeur.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
