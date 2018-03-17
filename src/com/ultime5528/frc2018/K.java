/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.ultime5528.frc2018;

import com.ultime5528.frc2018.Robot;

import edu.wpi.first.wpilibj.Preferences;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class K {

	public static void init() {

		Preferences prefs = Preferences.getInstance();

		prefs.putDouble("inter_y_a", OI.INTER_Y_A);

		prefs.putDouble("inter_y_b", OI.INTER_Y_B);

		prefs.putDouble("inter_y_c", OI.INTER_Y_C);

		prefs.putDouble("base_pilotable_largeur", BasePilotable.LARGEUR);

		prefs.putDouble("p_follower", BasePilotable.P_FOLLOWER);

		prefs.putDouble("d_follower", BasePilotable.D_FOLLOWER);

		prefs.putDouble("k_angle_follower", BasePilotable.K_ANGLE_FOLLOWER);

		prefs.putDouble("max_velocity", BasePilotable.MAX_VELOCITY);

		prefs.putDouble("correction_gauche", BasePilotable.CORRECTION_GAUCHE);

		prefs.putDouble("max_encoder", Elevateur.MAX_ENCODER);

		prefs.putDouble("min_encoder", Elevateur.MIN_ENCODER);

		prefs.putDouble("vitesse_moteur_elevateur_descendre", Elevateur.VITESSE_MOTEUR_ELEVATEUR_DESCENDRE);

		prefs.putDouble("vitesse_moteur_elevateur_monter", Elevateur.VITESSE_MOTEUR_ELEVATEUR_MONTER);

		prefs.putDouble("VITESSE_PRENDRE", Intake.VITESSE_PRENDRE);

		prefs.putDouble("VITESSE_LANCER_PROCHE", Intake.VITESSE_LANCER_PROCHE);

		prefs.putDouble("VITESSE_LANCER_LOIN", Intake.VITESSE_LANCER_LOIN);
		
		prefs.putDouble("VITESSE_GRIMPER", Grimpeur.VITESSE_GRIMPER);
		
		prefs.putDouble("VITESSE_DESCENDRE", Grimpeur.VITESSE_DESCENDRE);
		
		prefs.putDouble("THRESHOLD_VITESSE", SuivreTrajectoire.THRESHOLD_VITESSE);
		
		prefs.putDouble("VITESSE_BRAKE", SuivreTrajectoire.VITESSE_BRAKE);
		
		prefs.putDouble("P_ANGLE", SuivreTrajectoire.P_ANGLE);
		
		prefs.putDouble("ELEVATEUR_P", Elevateur.P);
	
		prefs.putDouble("ELEVATEUR_I", Elevateur.I);
	
		prefs.putDouble("ELEVATEUR_D", Elevateur.D);
		
		prefs.putDouble("VITESSE_GARDER_CUBE", Intake.VITESSE_GARDER_CUBE);
	
	
	}

	public static void update() {

		Preferences prefs = Preferences.getInstance();

		OI.INTER_Y_A = prefs.getDouble("inter_y_a", OI.INTER_Y_A);
		Robot.oi.getInterY().setA(OI.INTER_Y_A);

		OI.INTER_Y_B = prefs.getDouble("inter_y_b", OI.INTER_Y_B);
		Robot.oi.getInterY().setB(OI.INTER_Y_B);

		OI.INTER_Y_C = prefs.getDouble("inter_y_c", OI.INTER_Y_C);
		Robot.oi.getInterY().setC(OI.INTER_Y_C);

		BasePilotable.LARGEUR = prefs.getDouble("base_pilotable_largeur", BasePilotable.LARGEUR);

		BasePilotable.P_FOLLOWER = prefs.getDouble("p_follower", BasePilotable.P_FOLLOWER);

		BasePilotable.K_ANGLE_FOLLOWER = prefs.getDouble("k_angle_follower", BasePilotable.K_ANGLE_FOLLOWER);

		BasePilotable.MAX_VELOCITY = prefs.getDouble("max_velocity", BasePilotable.MAX_VELOCITY);

		BasePilotable.CORRECTION_GAUCHE = prefs.getDouble("correction_gauche", BasePilotable.CORRECTION_GAUCHE);

		Elevateur.MAX_ENCODER = prefs.getDouble("max_encoder", Elevateur.MAX_ENCODER);

		Elevateur.MIN_ENCODER = prefs.getDouble("min_encoder", Elevateur.MIN_ENCODER);

		Elevateur.VITESSE_MOTEUR_ELEVATEUR_DESCENDRE = prefs.getDouble("vitesse_moteur_elevateur_descendre",
				Elevateur.VITESSE_MOTEUR_ELEVATEUR_DESCENDRE);

		Elevateur.VITESSE_MOTEUR_ELEVATEUR_MONTER = prefs.getDouble("vitesse_moteur_elevateur_monter",
				Elevateur.VITESSE_MOTEUR_ELEVATEUR_MONTER);
		
		Elevateur.P = prefs.getDouble("ELEVATEUR_P", Elevateur.P);
		
		Elevateur.I = prefs.getDouble("ELEVATEUR_I", Elevateur.I);
		
		Elevateur.D = prefs.getDouble("ELEVATEUR_D", Elevateur.D);
		
		Elevateur.TOLERANCE = prefs.getDouble("ELEVATEUR_TOLERANCE", Elevateur.TOLERANCE);
		
		Robot.elevateur.setAbsoluteTolerance(Elevateur.TOLERANCE);
		
		Robot.elevateur.getPIDController().setPID(Elevateur.P, Elevateur.I, Elevateur.D);
		
		Intake.VITESSE_PRENDRE = prefs.getDouble("VITESSE_PRENDRE", Intake.VITESSE_PRENDRE);

		Intake.VITESSE_LANCER_PROCHE = prefs.getDouble("VITESSE_LANCER_PROCHE", Intake.VITESSE_LANCER_PROCHE);

		Intake.VITESSE_LANCER_LOIN = prefs.getDouble("VITESSE_LANCER_LOIN", Intake.VITESSE_LANCER_LOIN);
		
		Intake.VITESSE_GARDER_CUBE = prefs.getDouble("VITESSE_GARDER_CUBE", Intake.VITESSE_GARDER_CUBE);
		
		Grimpeur.VITESSE_GRIMPER = prefs.getDouble("VITESSE_GRIMPER", Grimpeur.VITESSE_GRIMPER);
	
		Grimpeur.VITESSE_DESCENDRE = prefs.getDouble("VITESSE_DESCENDRE", Grimpeur.VITESSE_DESCENDRE);
		
		SuivreTrajectoire.THRESHOLD_VITESSE = prefs.getDouble("THRESHOLD_VITESSE", SuivreTrajectoire.THRESHOLD_VITESSE);
		
		SuivreTrajectoire.VITESSE_BRAKE = prefs.getDouble("VITESSE_BRAKE", SuivreTrajectoire.THRESHOLD_VITESSE);
		
		SuivreTrajectoire.P_ANGLE = prefs.getDouble("P_ANGLE", SuivreTrajectoire.P_ANGLE);
		
		
		
	}

	public static final class Ports {

		public static final int BASE_PILOTABLE_MOTEUR_GAUCHE = 0;
		public static final int BASE_PILOTABLE_MOTEUR_DROIT = 1;

		public static final int ELEVATEUR_MOTEUR = 3;
		
		public static final int INTAKE_MOTEUR_GAUCHE = 4;
		public static final int INTAKE_MOTEUR_DROITE = 5;
		
		public static final int GRIMPEUR_MOTEUR = 2;
		public static final int GRIMPEUR_SERVO = 6;
		
		
		
		public static final int BASE_PILOTABLE_ENCODER_DROIT_A = 2;
		public static final int BASE_PILOTABLE_ENCODER_DROIT_B = 3;
		
		public static final int BASE_PILOTABLE_ENCODER_GAUCHE_A = 0;
		public static final int BASE_PILOTABLE_ENCODER_GAUCHE_B = 1;

		public static final int GRIMPEUR_ENCODER_A = 4;
		public static final int GRIMPEUR_ENCODER_B = 5;

		
		public static final int ELEVATEUR_ENCODER_A = 6;
		public static final int ELEVATEUR_ENCODER_B = 7;

		
		public static final int INTAKE_SWITCH_DROITE = 8;
		public static final int INTAKE_SWITCH_GAUCHE = 9;

	}

	public static final class OI {

		public static double INTER_Y_A = 0.5;
		public static double INTER_Y_B = 0.2;
		public static double INTER_Y_C = 0.1;

	}

	public static final class BasePilotable {
		public static double LARGEUR = 0.61;
		public static double P_FOLLOWER = 0;
		public static double D_FOLLOWER = 0;
		public static double K_ANGLE_FOLLOWER = -0.01;
		public static double MAX_VELOCITY = 2.55;
		public static double CORRECTION_GAUCHE = 0.98;
	}

	public static final class Elevateur {
		public static double MAX_ENCODER = 1.55;
		public static double MIN_ENCODER = 0;
		public static double VITESSE_MOTEUR_ELEVATEUR_MONTER = -0.5;
		public static double VITESSE_MOTEUR_ELEVATEUR_DESCENDRE = 0.15;
		public static double HAUTEUR_BAS = -0.01;
		public static double P = -15;
		public static double I = 0;
		public static double D = 0;
		public static double TOLERANCE = 0.00000000000000000001;
	}

	public static final class Intake {
		public static double VITESSE_PRENDRE = 0.45;
		public static double VITESSE_LANCER_PROCHE = -0.4;
		public static double VITESSE_LANCER_LOIN = -1;
		public static double VITESSE_GARDER_CUBE = 0.20;
		
	}
	public static final class Grimpeur {
		public static double VITESSE_GRIMPER = 1;
		public static double VITESSE_DESCENDRE = -1;
		public static double VITESSE_MAINTIEN = -0.5;
	}
	public static final class SuivreTrajectoire {
		public static double VITESSE_BRAKE = -1;
		public static double THRESHOLD_VITESSE = 0.01;
		public static double P_ANGLE = 0.01;
		
	}
	
}
