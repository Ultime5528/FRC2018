package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SignalerLED extends Command {
	private boolean signal1;
	private boolean signal2;
	private boolean signal3;
	private boolean signal4;

	public SignalerLED() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.ledController);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		signal1 = false;
		signal2 = false;
		signal3 = false;
		signal4 = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		if(DriverStation.getInstance().isOperatorControl()){

			if(!signal1 && DriverStation.getInstance().getMatchTime() <= 100){
				Robot.ledController.setModeSignal1();
				signal1 = true;
			}

			else if (!signal2 && DriverStation.getInstance().getMatchTime() <= 70){
				Robot.ledController.setModeSignal1();
				signal2 = true;
			}

			else if(!signal3 && DriverStation.getInstance().getMatchTime() <= 40){
				Robot.ledController.setModeSignal2();
				signal3 = true;
			}
			else if(!signal4 && DriverStation.getInstance().getMatchTime() <= 30){
				Robot.ledController.setModeEndGame();
				signal4 = true;
			}

		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
