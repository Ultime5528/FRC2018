package com.ultime5528.frc2018.subsystems;


import com.ultime5528.frc2018.K;
import com.ultime5528.frc2018.Robot;
import com.ultime5528.frc2018.commands.Pilotage;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class BasePilotable extends Subsystem {

	private VictorSP moteurGauche;
    private VictorSP moteurDroit; 
    private DifferentialDrive drive;

    private ADXRS450_Gyro gyro;
    private Encoder enc;
	
    
    public BasePilotable(){
    	super("Base pilotable");
    	
    	moteurGauche = new VictorSP(K.Ports.BASE_PILOTABLE_MOTEUR_GAUCHE);
    	addChild("MoteurGauche", moteurGauche);
    	
    	moteurDroit = new VictorSP(K.Ports.BASE_PILOTABLE_MOTEUR_DROIT);
    	addChild("MoteurDroit", moteurDroit);
    	
    	drive = new DifferentialDrive(moteurGauche, moteurDroit);
    	drive.setMaxOutput(0.7);
    	
    	enc = new Encoder(K.Ports.BASE_PILOTABLE_ENCODER_A, K.Ports.BASE_PILOTABLE_ENCODER_B);
    	enc.setDistancePerPulse(0.00023456);
    	addChild("enc", enc);
    	
    	gyro = new ADXRS450_Gyro();
    	addChild("Gyro", gyro);
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Pilotage());
    	

    }

	public void drive() {
		Joystick joystick = Robot.oi.getJoystick();
		drive.arcadeDrive(Robot.oi.getInterY().interpolate(-joystick.getY()), joystick.getX());
	}
	
	public void stop() {
		drive.arcadeDrive(0, 0);
	}
}

