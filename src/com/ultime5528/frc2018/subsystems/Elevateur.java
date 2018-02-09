package com.ultime5528.frc2018.subsystems;

import org.opencv.core.Point;

import com.ultime5528.frc2018.K;
import com.ultime5528.frc2018.util.LinearInterpolator;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Elevateur extends PIDSubsystem {

	private VictorSP moteurElevateur;
	private LinearInterpolator interpolateurMonter, interpolateurDescendre;
	private Encoder encoder;
	private Point[] pointsMonter, pointsDescendre;
	
	
	
	
	// Initialize your subsystem here
	public Elevateur() {
		super("Elevateur", K.Elevateur.P, K.Elevateur.I, K.Elevateur.D);

		moteurElevateur = new VictorSP(K.Ports.ELEVATEUR_MOTEUR);
		addChild("MoteurElevateur", moteurElevateur);

		encoder = new Encoder(K.Ports.ELEVATEUR_ENCODER_A, K.Ports.ELEVATEUR_ENCODER_B);
		addChild("Encoder", encoder);
		
		pointsMonter = new Point[] { 
				new Point(0, 0.4),
				new Point(10, 0.4),
				};
		
		pointsDescendre = new Point[]{
				new Point(0, 0),
				new Point(10, -0.15)
		};
		
		interpolateurMonter = new LinearInterpolator(pointsMonter);
		interpolateurDescendre = new LinearInterpolator(pointsDescendre);
		

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
			moteurElevateur.set(interpolateurMonter.interpolate(encoder.getDistance()));
		}
		
	}
	public void descendre(){
	
		if(encoder.get() <= K.Elevateur.MIN_ENCODER){
			moteurElevateur.set(0.0);
		}
		else{
			moteurElevateur.set(interpolateurMonter.interpolate(getPosition()));
		}
	}
	
	public void stop(){
		
		moteurElevateur.set(0.0);
	}
	
	public void resetEncoder(){
		encoder.reset();
	}
	
	public void tendre(){
		moteurElevateur.set(0.042);
	}
	
}


