/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.ultime5528.frc2018;



import org.opencv.core.Point;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

import com.ultime5528.frc2018.commands.DescendreElevateur;
import com.ultime5528.frc2018.commands.LancerCube;
import com.ultime5528.frc2018.commands.MonterElevateur;
import com.ultime5528.frc2018.commands.PrendreCube;
import com.ultime5528.frc2018.commands.SuivreArc;
import com.ultime5528.frc2018.commands.SuivreTrajectoire;
import com.ultime5528.frc2018.util.CubicInterpolator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private Joystick joystick;
	private JoystickButton button11;
	private CubicInterpolator interY;
	private JoystickButton button12;
	private JoystickButton button9;
	private JoystickButton button10;
	
	public OI() {
		
		Waypoint[] ligneDroite = {
				new Waypoint(0, 0, Pathfinder.d2r(0)),
				new Waypoint(3.35, 1.05, Pathfinder.d2r(90))
		};
		
		joystick = new Joystick(0);
		interY = new CubicInterpolator(K.OI.INTER_Y_A, K.OI.INTER_Y_B, K.OI.INTER_Y_C);
		
		SmartDashboard.putData("Scheduler", Scheduler.getInstance());
		SmartDashboard.putData("Suivre courbe 2 2 ", new SuivreArc(2, 1, 0.4));
		SmartDashboard.putData("Suivre courbe 2 0 ", new SuivreArc(2, 0, 0.4));
		SmartDashboard.putData("Suivre Trajectoire 2 2 0", new SuivreTrajectoire(ligneDroite ,0.4 , K.SuivreTrajectoire.VITESSE_BRAKE));
	
		button9 = new JoystickButton(joystick, 9);
		button9.whileHeld(new MonterElevateur());
		
		button10 = new JoystickButton(joystick, 10);
		button10.whileHeld(new DescendreElevateur());

		button11 = new JoystickButton(joystick, 11);
		button11.toggleWhenPressed(new PrendreCube());
		
		button12 = new JoystickButton(joystick, 12);
		button12.whenPressed(new LancerCube(K.Intake.VITESSE_LANCER_PROCHE));
		
		
	}
	
	public Joystick getJoystick() {
		return joystick;
	}
	
	public CubicInterpolator getInterY() {
		return interY;
	}
}
