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

		prefs.putDouble("max_velocity", K.BasePilotable.MAX_VELOCITY);

		prefs.putDouble("correction_gauche", K.BasePilotable.CORRECTION_GAUCHE);

		prefs.putDouble("max_encoder", K.Elevateur.MAX_ENCODER);

		prefs.putDouble("min_encoder", K.Elevateur.MIN_ENCODER);

		prefs.putDouble("vitesse_moteur_elevateur_descendre", K.Elevateur.VITESSE_MOTEUR_ELEVATEUR_DESCENDRE);

		prefs.putDouble("vitesse_moteur_elevateur_monter", K.Elevateur.VITESSE_MOTEUR_ELEVATEUR_MONTER);

		prefs.putDouble("VITESSE_PRENDRE", K.Intake.VITESSE_PRENDRE);

		prefs.putDouble("VITESSE_LANCER_PROCHE", K.Intake.VITESSE_LANCER_PROCHE);

		prefs.putDouble("VITESSE_LANCER_LOIN", K.Intake.VITESSE_LANCER_LOIN);
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

		BasePilotable.MAX_VELOCITY = prefs.getDouble("max_velocity", K.BasePilotable.MAX_VELOCITY);

		BasePilotable.CORRECTION_GAUCHE = prefs.getDouble("correction_gauche", K.BasePilotable.CORRECTION_GAUCHE);

		Elevateur.MAX_ENCODER = prefs.getDouble("max_encoder", K.Elevateur.MAX_ENCODER);

		Elevateur.MIN_ENCODER = prefs.getDouble("min_encoder", K.Elevateur.MIN_ENCODER);

		Elevateur.VITESSE_MOTEUR_ELEVATEUR_DESCENDRE = prefs.getDouble("vitesse_moteur_elevateur_descendre",
				K.Elevateur.VITESSE_MOTEUR_ELEVATEUR_DESCENDRE);

		Elevateur.VITESSE_MOTEUR_ELEVATEUR_MONTER = prefs.getDouble("vitesse_moteur_elevateur_monter",
				K.Elevateur.VITESSE_MOTEUR_ELEVATEUR_MONTER);

		K.Intake.VITESSE_PRENDRE = prefs.getDouble("VITESSE_PRENDRE", K.Intake.VITESSE_PRENDRE);

		K.Intake.VITESSE_LANCER_PROCHE = prefs.getDouble("VITESSE_LANCER_PROCHE", K.Intake.VITESSE_LANCER_PROCHE);

		K.Intake.VITESSE_LANCER_LOIN = prefs.getDouble("VITESSE_LANCER_LOIN", K.Intake.VITESSE_LANCER_LOIN);
	}

	public static final class Ports {

		public static final int BASE_PILOTABLE_MOTEUR_GAUCHE = 0;
		public static final int BASE_PILOTABLE_MOTEUR_DROIT = 1;

		public static final int BASE_PILOTABLE_ENCODER_GAUCHE_A = 2;
		public static final int BASE_PILOTABLE_ENCODER_GAUCHE_B = 3;

		public static final int BASE_PILOTABLE_ENCODER_DROIT_A = 0;
		public static final int BASE_PILOTABLE_ENCODER_DROIT_B = 1;

		public static final int ELEVATEUR_MOTEUR = 2;
		public static final int ELEVATEUR_ENCODER_A = 6;
		public static final int ELEVATEUR_ENCODER_B = 7;

		public static final int INTAKE_MOTEUR_GAUCHE = 3;
		public static final int INTAKE_MOTEUR_DROITE = 4;
		public static final int INTAKE_LIMIT_SWITCH = 6;

		public static final int GRIMPEUR_MOTEUR = 5;
		public static final int GRIMPEUR_ENCODER_A = 4;
		public static final int GRIMPEUR_ENCODER_B = 5;
		public static final int GRIMPEUR_SERVO = 6;

	}

	public static final class OI {

		public static double INTER_Y_A = 0.5;
		public static double INTER_Y_B = 0.2;
		public static double INTER_Y_C = 0.1;

	}

	public static final class BasePilotable {
		public static double LARGEUR = 0.5;
		public static double P_FOLLOWER = 0;
		public static double D_FOLLOWER = 0;
		public static double K_ANGLE_FOLLOWER = -0.01;
		public static double MAX_VELOCITY = 2.55;
		public static double CORRECTION_GAUCHE = 0.98;
	}

	public static final class Elevateur {
		public static double MAX_ENCODER = 1000;
		public static double MIN_ENCODER = 0;
		public static double VITESSE_MOTEUR_ELEVATEUR_MONTER = 0.5;
		public static double VITESSE_MOTEUR_ELEVATEUR_DESCENDRE = -0.5;
	}

	public static final class Intake {
		public static double VITESSE_PRENDRE = 0.7;
		public static double VITESSE_LANCER_PROCHE = -0.2;
		public static double VITESSE_LANCER_LOIN = -1;
	}
	public static final class Grimpeur {
		public static double VITESSE_GRIMPER = 1;
	}
	
}
