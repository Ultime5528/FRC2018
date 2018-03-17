package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		SmartDashboard.putBoolean("PrendreCube", true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		Robot.intake.prendre();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.intake.hasCube();
	}

	// Called once after isFinished returns true
	protected void end() {
		
		SmartDashboard.putBoolean("PrendreCube", false);
		Robot.intake.stop();
		Robot.ledController.setModeCube();
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.intake.stop();
		SmartDashboard.putBoolean("PrendreCube", false);
	}
}
