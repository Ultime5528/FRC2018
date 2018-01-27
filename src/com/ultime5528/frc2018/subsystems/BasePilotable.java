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
	private Encoder encoderGauche;
	private Encoder encoderDroit;

	public BasePilotable() {
		super("Base pilotable");

		moteurGauche = new VictorSP(K.Ports.BASE_PILOTABLE_MOTEUR_GAUCHE);
		addChild("Moteur Gauche", moteurGauche);

		moteurDroit = new VictorSP(K.Ports.BASE_PILOTABLE_MOTEUR_DROIT);
		addChild("Moteur Droit", moteurDroit);

		drive = new DifferentialDrive(moteurGauche, moteurDroit);
		drive.setMaxOutput(0.7);

		encoderGauche = new Encoder(K.Ports.BASE_PILOTABLE_ENCODER_GAUCHE_A, K.Ports.BASE_PILOTABLE_ENCODER_GAUCHE_B);
		encoderGauche.setDistancePerPulse(-0.00023456);
		addChild("encodeur gauche", encoderGauche);

		encoderDroit = new Encoder(K.Ports.BASE_PILOTABLE_ENCODER_DROIT_A, K.Ports.BASE_PILOTABLE_ENCODER_DROIT_B);
		encoderDroit.setDistancePerPulse(0.00023456);
		addChild("encodeur droit", encoderDroit);

		gyro = new ADXRS450_Gyro();
		gyro.calibrate();
		addChild("Gyro", gyro);

	}

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
