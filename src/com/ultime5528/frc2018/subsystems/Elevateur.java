package com.ultime5528.frc2018.subsystems;

import java.sql.Driver;

import com.ultime5528.frc2018.K;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Elevateur extends PIDSubsystem {

	private VictorSP moteurElevateur;

	private AnalogPotentiometer potentiometre;
	
	public static final double MAX_POTENTIOMETRE = 1000;
	public static final double MINIMUM_POTENTIOMETRE = 25;
	
	// Initialize your subsystem here
	public Elevateur() {
		super("Elevateur", 0, 0, 0);

		moteurElevateur = new VictorSP(K.Ports.ELEVATEUR_MOTEUR);
		addChild("MoteurElevateur", moteurElevateur);

		potentiometre = new AnalogPotentiometer(K.Ports.ELEVATEUR_POTENTIOMETRE);
		addChild("Potentiometre", potentiometre);
		
		

		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return 0.0;
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
	}
	public void monter() {
		
		if(potentiometre.get() >= MAX_POTENTIOMETRE){
			moteurElevateur.set(0.0);
			
		}
		else{
			moteurElevateur.set(0.5);
		}
		
	}
	public void descendre(){
	
		if(potentiometre.get() <= MINIMUM_POTENTIOMETRE){
			moteurElevateur.set(0.0);
		}
		else{
			moteurElevateur.set(-0.5);
		}
	}
	
}


