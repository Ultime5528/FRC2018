/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.ultime5528.frc2018;



import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

import com.ultime5528.frc2018.commands.AutoCentreSwitchDroite;
import com.ultime5528.frc2018.commands.AutoCentreSwitchGauche;
import com.ultime5528.frc2018.commands.AutoDroitScaleDroite;
import com.ultime5528.frc2018.commands.AutoDroitScaleGauche;
import com.ultime5528.frc2018.commands.AutoGaucheScaleDroite;
import com.ultime5528.frc2018.commands.AutoGaucheScaleGauche;
import com.ultime5528.frc2018.commands.DemarrerElevateur;
import com.ultime5528.frc2018.commands.DescendreElevateur;
import com.ultime5528.frc2018.commands.LancerCube;
import com.ultime5528.frc2018.commands.MonterElevateur;
import com.ultime5528.frc2018.commands.MonterRobot;
import com.ultime5528.frc2018.commands.PrendreCube;
import com.ultime5528.frc2018.commands.PrendreLeverCube;
import com.ultime5528.frc2018.commands.SetElevateur;
import com.ultime5528.frc2018.commands.SuivreArc;
import com.ultime5528.frc2018.commands.SuivreTrajectoire;
import com.ultime5528.frc2018.commands.TournerCubeDroite;
import com.ultime5528.frc2018.commands.TournerCubeGauche;
import com.ultime5528.frc2018.triggers.AxisDownTrigger;
import com.ultime5528.frc2018.triggers.AxisUpTrigger;
import com.ultime5528.frc2018.triggers.CubeTrigger;
import com.ultime5528.frc2018.triggers.POVTrigger;
import com.ultime5528.frc2018.triggers.POVTrigger.Arrow;
import com.ultime5528.frc2018.util.CubicInterpolator;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
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
	
	
	private JoystickButton buttonG1;
	private JoystickButton buttonG2;
	private JoystickButton buttonG3;
	private JoystickButton buttonG4;
	private JoystickButton buttonG5;
	private JoystickButton buttonG6;
	private JoystickButton buttonG8;
	
	
	private JoystickButton button5;
	private JoystickButton button6;
	private JoystickButton button8;
	private JoystickButton button11;
	private JoystickButton button12;
	
	
	private POVTrigger downG; 
	private AxisUpTrigger leftAxisUp;
	private AxisDownTrigger leftAxisDown;
	private AxisDownTrigger rightTrigger;
	private AxisDownTrigger leftTrigger;
	private CubeTrigger cubeTrigger;

	
	public OI() {
		
		Waypoint[] ligneDroite = {
				new Waypoint(0, 0, Pathfinder.d2r(0)),
				new Waypoint(3.35, 1.05, Pathfinder.d2r(90))
		};
		
		joystick = new Joystick(0);
		gamepad = new XboxController(1);
		interY = new CubicInterpolator(K.OI.INTER_Y_A, K.OI.INTER_Y_B, K.OI.INTER_Y_C);
		
		//SmartDashboard.putData("Scheduler", Scheduler.getInstance());
		//SmartDashboard.putData("Set Elevateur 0", new SetElevateur(K.Elevateur.HAUTEUR_BAS));
		//SmartDashboard.putData("Set Elevateur .1", new SetElevateur(0.1));
		//SmartDashboard.putData("Set Elevateur 0.6", new SetElevateur(0.6));
		//SmartDashboard.putData("Set Elevateur 1.4", new SetElevateur(1.4));
		//SmartDashboard.putData("Autonome Gauche Scale Gauche", new AutoGaucheScaleGauche());
		//SmartDashboard.putData("AutonomeGaucheScaleGaucheSwitchGauche",new AutonomeGaucheScaleGaucheSwitchGauche());
		//SmartDashboard.putData("AutoCentreSwitchDroite", new AutoCentreSwitchDroite());
		//SmartDashboard.putData("AutoCentreSwitchGauche", new AutoCentreSwitchGauche());
		//SmartDashboard.putData("AutoGaucheScaleDroite", new AutoGaucheScaleDroite());
		//SmartDashboard.putData("AutoDroitScaleDroite", new AutoDroitScaleDroite());
		//SmartDashboard.putData("AutoDroitScaleGauche", new AutoDroitScaleGauche());
		
		
		// Button Gamepad
		
		buttonG1 = new JoystickButton(gamepad, 1);
		buttonG1.whenPressed(new SetElevateur(-0.005));
		
		buttonG2 = new JoystickButton(gamepad, 2);
		buttonG2.whenPressed(new SetElevateur(0.7));
		
		buttonG3 = new JoystickButton(gamepad, 3);
		buttonG3.whenPressed(new SetElevateur(0.1));
		
		buttonG4 = new JoystickButton(gamepad, 4);
		buttonG4.whenPressed(new SetElevateur(1.5));
		
		buttonG5 = new JoystickButton(gamepad, 5);
		buttonG5.whenPressed(new LancerCube(K.Intake.VITESSE_LANCER_PROCHE));
		
		buttonG6 = new JoystickButton(gamepad, 6);
		buttonG6.whenPressed(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
		
		buttonG8 = new JoystickButton(gamepad, 8);
		buttonG8.whenPressed(new DemarrerElevateur());
		
		
		//Button Joystick
		
		//button2 = new JoystickButton(joystick, 2);
		//button2.whileHeld(new MonterRobot());

		
		
		button5 = new JoystickButton(joystick, 5);
		button5.whileHeld(new DescendreElevateur());
		
		button6 = new JoystickButton(joystick, 6);
		button6.whileHeld(new MonterElevateur());
		
		button8 = new JoystickButton(joystick, 8);
		button8.whileHeld(new MonterRobot());
		
		button11 = new JoystickButton(joystick, 11);
		button11.toggleWhenPressed(new PrendreCube());
		
		button12 = new JoystickButton(joystick, 12);
		button12.whenPressed(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
		
		// Button Gamepad
		
		downG= new POVTrigger(gamepad, Arrow.DOWN);
		downG.whileActive(new PrendreCube());
		
		leftAxisDown = new AxisDownTrigger(gamepad, 1);
		leftAxisDown.whileActive(new DescendreElevateur());
		
		leftAxisUp = new AxisUpTrigger(gamepad, 1);
		leftAxisUp.whileActive(new MonterElevateur());
		
		rightTrigger = new AxisDownTrigger(gamepad, 3);
		rightTrigger.whileActive(new TournerCubeDroite());
		
		leftTrigger = new AxisDownTrigger(gamepad, 2);
		leftTrigger.whileActive(new TournerCubeGauche());
		
		cubeTrigger = new CubeTrigger();
		cubeTrigger.whenActive(new PrendreCube());
		
		
		
	}
	
	
	public void setRumble(double value) {
		gamepad.setRumble(RumbleType.kLeftRumble, value);
		gamepad.setRumble(RumbleType.kRightRumble, value);
	}
	
	public boolean getDownGamepad(){
		
		return downG.get();
	}
	
	public Joystick getJoystick() {
		return joystick;
	}
	
	public CubicInterpolator getInterY() {
		return interY;
	}
}
