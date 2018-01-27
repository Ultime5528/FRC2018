package com.ultime5528.frc2018.subsystems;

import java.sql.Driver;

import com.ultime5528.frc2018.K;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Elevateur extends PIDSubsystem {

	private VictorSP moteurElevateur;

	private Encoder encoder;
	
	
	
	// Initialize your subsystem here
	public Elevateur() {
		super("Elevateur", 0, 0, 0);

		moteurElevateur = new VictorSP(K.Ports.ELEVATEUR_MOTEUR);
		addChild("MoteurElevateur", moteurElevateur);

		encoder = new Encoder(K.Ports.ELEVATEUR_ENCODER_A, K.Ports.ELEVATEUR_ENCODER_B);
		addChild("Encoder", encoder);
		
		

		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	@Override
	protected double returnPIDInput() {

		return encoder.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		moteurElevateur.set(output);
		
	}
	public void monter() {
		
		if(encoder.get() >= K.Elevateur.MAX_ENCODER){
			moteurElevateur.set(0.0);
			
		}
		else{
			moteurElevateur.set(K.Elevateur.VITESSE_MOTEUR_ELEVATEUR_MONTER);
		}
		
	}
	public void descendre(){
	
		if(encoder.get() <= K.Elevateur.MIN_ENCODER){
			moteurElevateur.set(0.0);
		}
		else{
			moteurElevateur.set(K.Elevateur.VITESSE_MOTEUR_ELEVATEUR_DESCENDRE);
		}
	}
	
	public void stop(){
		
		moteurElevateur.set(0.0);
	}
	
}


