package com.ultime5528.frc2018.commands;


import com.ultime5528.frc2018.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Pilotage extends Command {

    public Pilotage() {
        super("Pilotage");
    	requires(Robot.basePilotable);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.basePilotable.drive();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
