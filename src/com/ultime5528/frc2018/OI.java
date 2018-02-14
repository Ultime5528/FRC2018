/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.ultime5528.frc2018;



import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

import com.ultime5528.frc2018.commands.AutonomeGaucheScaleGauche;
import com.ultime5528.frc2018.commands.AutonomeGaucheScaleGaucheSwitchGauche;
import com.ultime5528.frc2018.commands.DemarrerElevateur;
import com.ultime5528.frc2018.commands.DescendreElevateur;
import com.ultime5528.frc2018.commands.LancerCube;
import com.ultime5528.frc2018.commands.MonterElevateur;
import com.ultime5528.frc2018.commands.MonterRobot;
import com.ultime5528.frc2018.commands.PrendreLeverCube;
import com.ultime5528.frc2018.commands.SetElevateur;
import com.ultime5528.frc2018.commands.SuivreArc;
import com.ultime5528.frc2018.commands.SuivreTrajectoire;
import com.ultime5528.frc2018.util.CubicInterpolator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private Joystick joystick;
	private XboxController gamepad;
	private CubicInterpolator interY;
	private JoystickButton button1;
	private JoystickButton button2;
	private JoystickButton button3;
	private JoystickButton button4;
	private JoystickButton button5;
	private JoystickButton button6;
	private JoystickButton button8;
	private JoystickButton button11;
	private JoystickButton button12;

	public OI() {
		
		Waypoint[] ligneDroite = {
				new Waypoint(0, 0, Pathfinder.d2r(0)),
				new Waypoint(3.35, 1.05, Pathfinder.d2r(90))
		};
		
		joystick = new Joystick(0);
		gamepad = new XboxController(1);
		interY = new CubicInterpolator(K.OI.INTER_Y_A, K.OI.INTER_Y_B, K.OI.INTER_Y_C);
		
		SmartDashboard.putData("Scheduler", Scheduler.getInstance());
		SmartDashboard.putData("Suivre courbe 2 2 ", new SuivreArc(2, 1, 0.4));
		SmartDashboard.putData("Suivre courbe 2 0 ", new SuivreArc(2, 0, 0.4));
		SmartDashboard.putData("Suivre Trajectoire 2 2 0", new SuivreTrajectoire(ligneDroite ,0.4 , K.SuivreTrajectoire.VITESSE_BRAKE));
		SmartDashboard.putData("Set Elevateur 0", new SetElevateur(K.Elevateur.HAUTEUR_BAS));
		SmartDashboard.putData("Set Elevateur .1", new SetElevateur(0.1));
		SmartDashboard.putData("Set Elevateur 0.6", new SetElevateur(0.6));
		SmartDashboard.putData("Set Elevateur 1.4", new SetElevateur(1.4));
		SmartDashboard.putData("Autonome Gauche Scale Gauche", new AutonomeGaucheScaleGauche());
		SmartDashboard.putData("AutonomeGaucheScaleGaucheSwitchGauche",new AutonomeGaucheScaleGaucheSwitchGauche());
		
		SmartDashboard.putData("Scheduler", Scheduler.getInstance());
		
		button5 = new JoystickButton(joystick, 5);
		button5.whileHeld(new MonterElevateur());
		
		button3 = new JoystickButton(joystick, 3);
		button3.whileHeld(new DescendreElevateur());
		
		button1 = new JoystickButton(gamepad, 1);
		button1.whenPressed(new SetElevateur(0));

		button2 = new JoystickButton(joystick, 2);
		button2.whileHeld(new MonterRobot());
		
		button2 = new JoystickButton(gamepad, 2);
		button2.whenPressed(new SetElevateur(0.6));
		
		button3 = new JoystickButton(gamepad, 3);
		button3.whenPressed(new SetElevateur(0.1));
		
		button4 = new JoystickButton(gamepad, 4);
		button4.whenPressed(new SetElevateur(1.4));
		
		button5 = new JoystickButton(gamepad, 5);
		button5.whenPressed(new LancerCube(K.Intake.VITESSE_LANCER_PROCHE));
		
		button6 = new JoystickButton(gamepad, 6);
		button6.whenPressed(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
		
		button8 = new JoystickButton(gamepad, 8);
		button8.whenPressed(new DemarrerElevateur());
		
		button11 = new JoystickButton(joystick, 11);
		button11.toggleWhenPressed(new PrendreLeverCube());
		
		button12 = new JoystickButton(joystick, 12);
		button12.whenPressed(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
		
		
	}
	
	public Joystick getJoystick() {
		return joystick;
	}
	
	public CubicInterpolator getInterY() {
		return interY;
	}
}
