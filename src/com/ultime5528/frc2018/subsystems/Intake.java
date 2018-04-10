package com.ultime5528.frc2018.subsystems;

import com.ultime5528.frc2018.K;
import com.ultime5528.frc2018.commands.MaintienCube;

import edu.wpi.first.wpilibj.AnalogInput;
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
	private AnalogInput sonar;
	private Boolean aCube;

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
		
		sonar = new AnalogInput(0);
		addChild("Sonar", sonar);
		
	}
	
	public boolean hasCube(){
		
		return (sonar.getVoltage() < 0.08); //switchDroite.get() && switchGauche.get();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new MaintienCube());
	}
	
	public void prendre(){
		moteurGauche.set(-0.65 * K.Intake.VITESSE_PRENDRE);
		moteurDroite.set(K.Intake.VITESSE_PRENDRE);
	}
	
	public void stop() {
		moteurDroite.set(0);
		moteurGauche.set(0);
	}
	
	public void garder(){
		setMoteurs(K.Intake.VITESSE_GARDER_CUBE);
	}
	
	public void lancer(double speed) {
		setMoteurs(speed);
	}
	
	
	private void setMoteurs(double speed){
		moteurDroite.set(speed);
		moteurGauche.set(-speed);
	}

	public void tournerCube() {
		moteurGauche.set(0.5); // Cote gauche inverse. moins rapide que le droit
		moteurDroite.set(0.8);
	}

	public void prendreFort() {
		setMoteurs(0.6);
	}
}
