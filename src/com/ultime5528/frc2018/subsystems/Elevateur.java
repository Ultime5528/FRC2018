package com.ultime5528.frc2018.subsystems;

import com.ultime5528.frc2018.K;
import com.ultime5528.frc2018.commands.MaintienElevateur;
import com.ultime5528.frc2018.util.LinearInterpolator;
import com.ultime5528.frc2018.util.Point;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Elevateur extends PIDSubsystem {

	private VictorSP moteur;
	private LinearInterpolator interpolateurMonter, interpolateurDescendre;
	private Encoder encoder;
	private Point[] pointsMonter, pointsDescendre;
	
	
	
	
	// Initialize your subsystem here
	public Elevateur() {
		super("Elevateur", K.Elevateur.P, K.Elevateur.I, K.Elevateur.D);

		//setAbsoluteTolerance(K.Elevateur.TOLERANCE);
		
		moteur = new VictorSP(K.Ports.ELEVATEUR_MOTEUR);
		addChild("moteur", moteur);

		encoder = new Encoder(K.Ports.ELEVATEUR_ENCODER_A, K.Ports.ELEVATEUR_ENCODER_B);
		addChild("Encoder", encoder);
		encoder.setDistancePerPulse(-0.00006006);
		
		pointsMonter = new Point[] { 
				new Point(1.38, -0.7),
				new Point(1.50, -0.3),
				};
		
		pointsDescendre = new Point[]{
				new Point(0.1, 0.15),
				new Point(0.15, 0.35),
				new Point(0.65,0.45),
				new Point(0.8,0.25)
		};
		
		interpolateurMonter = new LinearInterpolator(pointsMonter);
		interpolateurDescendre = new LinearInterpolator(pointsDescendre);
	}

	
	
	public void initDefaultCommand() {
		//setDefaultCommand(new MaintienElevateur());
	}
	
	
	
	@Override
	protected double returnPIDInput() {
		return encoder.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		moteur.set(output);
	}
	
	
	public void startPID() {
		setSetpoint(getHauteur());
		enable();
	}
	
	
	public void monter(LinearInterpolator interpolator) {
		
		/*
		if(encoder.getDistance() >= K.Elevateur.MAX_ENCODER){
			moteur.set(0.0);
			
		}
		else{
			moteur.set(interpolator.interpolate(encoder.getDistance()));
			//moteur.set(K.Elevateur.VITESSE_MOTEUR_ELEVATEUR_MONTER);
		}*/
		
		moteur.set(interpolator.interpolate(encoder.getDistance()));
		
	}
	
	public void monter() {
		//monter(interpolateurMonter);
		moteur.set(-0.65);
	}
	
	public void descendre(){
	
		/*
		if(encoder.getDistance() <= K.Elevateur.MIN_ENCODER){
			moteur.set(0.0);
		}
		else{
			moteur.set(interpolateurDescendre.interpolate(encoder.getDistance()));
			//moteur.set(K.Elevateur.VITESSE_MOTEUR_ELEVATEUR_DESCENDRE);
		}*/
		
		moteur.set(interpolateurDescendre.interpolate(encoder.getDistance()));
		
	}
	
	public void stop(){
		
		moteur.set(0.0);
	}
	
	public void resetEncoder(){
		encoder.reset();
	}
	
	public void tendre(){
		moteur.set(-0.17);
	}
	
	public double getHauteur(){
		return encoder.getDistance();
	}
}


