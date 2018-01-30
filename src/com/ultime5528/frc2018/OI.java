/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.ultime5528.frc2018;

import com.ultime5528.frc2018.commands.SuivreArc;
import com.ultime5528.frc2018.util.CubicInterpolator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private Joystick joystick;
	
	private CubicInterpolator interY;
	
	public OI() {
		joystick = new Joystick(0);
		interY = new CubicInterpolator(K.OI.INTER_Y_A, K.OI.INTER_Y_B, K.OI.INTER_Y_C);
		
		SmartDashboard.putData("Scheduler", Scheduler.getInstance());
		SmartDashboard.putData("Suivre courbe 2 2 ", new SuivreArc(2, 1, 0.4));
		SmartDashboard.putData("Suivre courbe 2 0 ", new SuivreArc(2, 0, 0.4));
		
	}
	
	public Joystick getJoystick() {
		return joystick;
	}
	
	public CubicInterpolator getInterY() {
		return interY;
	}
}
