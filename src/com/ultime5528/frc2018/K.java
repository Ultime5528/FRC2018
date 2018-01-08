/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.ultime5528.frc2018;

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
		
	}
	
	
	
	public static void update() {
		
		Preferences prefs = Preferences.getInstance();
		
		OI.INTER_Y_A = prefs.getDouble("inter_y_a", OI.INTER_Y_A);
		Robot.oi.getInterY().setA(OI.INTER_Y_A);

		OI.INTER_Y_B = prefs.getDouble("inter_y_b", OI.INTER_Y_B);
		Robot.oi.getInterY().setB(OI.INTER_Y_B);

		OI.INTER_Y_C = prefs.getDouble("inter_y_c", OI.INTER_Y_C);
		Robot.oi.getInterY().setC(OI.INTER_Y_C);
		
	}
	
	
	
	public static final class Ports {
		
		public static final int BASE_PILOTABLE_MOTEUR_GAUCHE	 = 0;
		public static final int BASE_PILOTABLE_MOTEUR_DROIT		 = 1;
		
		public static final int BASE_PILOTABLE_ENCODER_A		 = 0;
		public static final int BASE_PILOTABLE_ENCODER_B		 = 1;
		
	}
	
	
	
	public static final class OI {
		
		public static double INTER_Y_A = 0.5;
		public static double INTER_Y_B = 0.2;
		public static double INTER_Y_C = 0.1;
		
		
	}
	
}
