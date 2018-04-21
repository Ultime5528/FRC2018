package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PrendreCube extends Command {

	public PrendreCube() {
		super("PrendreCube");
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		SmartDashboard.putBoolean("PrendreCube", true);
		Robot.camera.setIntake(true);
		Robot.oi.setRumble(1.0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.intake.prendre();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.intake.hasCube() && !Robot.oi.getDownGamepad();
	}

	// Called once after isFinished returns true
	protected void end() {

		interrupted();
		Robot.ledController.setModeCube();
		
	}

	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.intake.stop();
		SmartDashboard.putBoolean("PrendreCube", false);
		Robot.camera.setIntake(false);
		Robot.oi.setRumble(0.0);
		
	}
}
