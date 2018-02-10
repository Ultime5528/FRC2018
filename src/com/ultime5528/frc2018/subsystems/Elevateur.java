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

	private VictorSP moteurElevateur;
	private LinearInterpolator interpolateurMonter, interpolateurDescendre;
	private Encoder encoder;
	private Point[] pointsMonter, pointsDescendre;
	
	
	
	
	// Initialize your subsystem here
	public Elevateur() {
		super("Elevateur", K.Elevateur.P, K.Elevateur.I, K.Elevateur.D);

		setAbsoluteTolerance(K.Elevateur.TOLERANCE);
		
		moteurElevateur = new VictorSP(K.Ports.ELEVATEUR_MOTEUR);
		addChild("MoteurElevateur", moteurElevateur);

		encoder = new Encoder(K.Ports.ELEVATEUR_ENCODER_A, K.Ports.ELEVATEUR_ENCODER_B);
		addChild("Encoder", encoder);
		encoder.setDistancePerPulse(-0.00006006);
		
		pointsMonter = new Point[] { 
				new Point(1.4, -0.8),
				new Point(1.45, -1),
				};
		
		pointsDescendre = new Point[]{
				new Point(0.1, 0.12),
				new Point(0.18, 0.3),
				new Point(0.65,0.3),
				new Point(0.8,0.2)
		};
		
		interpolateurMonter = new LinearInterpolator(pointsMonter);
		interpolateurDescendre = new LinearInterpolator(pointsDescendre);
	}

	
	
	public void initDefaultCommand() {
		setDefaultCommand(new MaintienElevateur());
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
		
		if(encoder.getDistance() >= K.Elevateur.MAX_ENCODER){
			moteurElevateur.set(0.0);
			
		}
		else{
			moteurElevateur.set(interpolateurMonter.interpolate(encoder.getDistance()));
			//moteurElevateur.set(K.Elevateur.VITESSE_MOTEUR_ELEVATEUR_MONTER);
		}
		
	}
	public void descendre(){
	
		if(encoder.getDistance() <= K.Elevateur.MIN_ENCODER){
			moteurElevateur.set(0.0);
		}
		else{
			moteurElevateur.set(interpolateurDescendre.interpolate(encoder.getDistance()));
			//moteurElevateur.set(K.Elevateur.VITESSE_MOTEUR_ELEVATEUR_DESCENDRE);
		}
	}
	
	public void stop(){
		
		moteurElevateur.set(0.0);
	}
	
	public void resetEncoder(){
		encoder.reset();
	}
	
	public void tendre(){
		moteurElevateur.set(-0.17);
	}
	
	public double getHauteur(){
		return encoder.getDistance();
	}
}


