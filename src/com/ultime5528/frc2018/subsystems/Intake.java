package com.ultime5528.frc2018.subsystems;

import com.ultime5528.frc2018.K;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	private VictorSP moteurGauche;
	private VictorSP moteurDroite;
	private DigitalInput switchDroite;
	private DigitalInput switchGauche;

	public Intake() {
		
		super("Intake");
		
		moteurGauche = new VictorSP(K.Ports.INTAKE_MOTEUR_GAUCHE);
		addChild("Moteur Gauche", moteurGauche);

		moteurDroite = new VictorSP(K.Ports.INTAKE_MOTEUR_DROITE);
		addChild("Moteur Droite", moteurDroite);

		switchDroite = new DigitalInput(K.Ports.INTAKE_SWITCH_DROITE);
		addChild("Switch Droite", switchDroite);
		
		switchGauche = new DigitalInput(K.Ports.INTAKE_SWITCH_GAUCHE);
		addChild("Switch Gauche", switchGauche);
		
	}
	
	public boolean hasCube(){
		
		return switchDroite.get() && switchGauche.get();
	}

	public void initDefaultCommand() {

	}
	
	public void prendre(){
		moteurGauche.set(K.Intake.VITESSE_PRENDRE);
		moteurDroite.set(K.Intake.VITESSE_PRENDRE);
	}
	
	public void stop() {
		moteurDroite.set(0);
		moteurGauche.set(0);
	}
	
	public void garder(){
		moteurDroite.set(-0.2);
		moteurGauche.set(-0.2);
	}
	
	public void lancer(double speed) {
		moteurDroite.set(speed);
		moteurGauche.set(speed);
		
	}
}
