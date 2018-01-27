package com.ultime5528.frc2018.subsystems;

import com.ultime5528.frc2018.K;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grimpeur extends Subsystem {

    private VictorSP moteur;  
    private Encoder encoder;
    private Servo servo;
    
    
    public Grimpeur () {
    	
    	super("Grimpeur");
    	
    	moteur = new VictorSP(K.Ports.GRIMPEUR_MOTEUR); 
    	addChild("moteur", moteur);
    	
    	encoder = new Encoder(K.Ports.GRIMPEUR_ENCODER_A, K.Ports.GRIMPEUR_ENCODER_B);
    	addChild("Encoder", encoder);
    	
    	servo = new Servo(K.Ports.GRIMPEUR_SERVO);
    	addChild("Servo", servo);
    	
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void grimper(){
    	moteur.set(K.Grimpeur.VITESSE_GRIMPER);
    }
    
    public void stop() {
    	moteur.set(0);
    }
    
}

