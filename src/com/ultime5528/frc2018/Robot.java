/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.ultime5528.frc2018;

import com.ultime5528.frc2018.commands.AutoCentreSwitchDroite;
import com.ultime5528.frc2018.commands.AutoCentreSwitchGauche;
import com.ultime5528.frc2018.commands.AutoDroitScaleDroite;
import com.ultime5528.frc2018.commands.AutoDroitScaleGauche;
import com.ultime5528.frc2018.commands.AutoGaucheScaleDroite;
import com.ultime5528.frc2018.commands.AutoGaucheScaleGauche;
import com.ultime5528.frc2018.commands.AutoLigneDroite;
import com.ultime5528.frc2018.commands.DemarrerElevateur;
import com.ultime5528.frc2018.commands.SignalerLED;
import com.ultime5528.frc2018.subsystems.BasePilotable;
import com.ultime5528.frc2018.subsystems.Elevateur;
import com.ultime5528.frc2018.subsystems.Intake;
import com.ultime5528.frc2018.subsystems.Grimpeur;
import com.ultime5528.frc2018.subsystems.LEDController;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GamepadBase;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	public static final BasePilotable basePilotable = new BasePilotable();
	public static final Elevateur elevateur = new Elevateur();
	public static final Intake intake = new Intake();
	public static final Grimpeur grimpeur = new Grimpeur (); 
	public static final LEDController ledController = new LEDController();
	public static final PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static OI oi;

	private static final AutoCentreSwitchDroite autoCentreSwitchDroite = new AutoCentreSwitchDroite();
	private static final AutoCentreSwitchGauche autoCentreSwitchGauche = new AutoCentreSwitchGauche();
	private static final AutoGaucheScaleGauche autoGaucheScaleGauche = new AutoGaucheScaleGauche();
	private static final AutoGaucheScaleDroite autoGaucheScaleDroite = new AutoGaucheScaleDroite();
	private static final AutoDroitScaleDroite autoDroitScaleDroite = new AutoDroitScaleDroite();
	private static final AutoDroitScaleGauche autoDroitScaleGauche = new AutoDroitScaleGauche();
	private static final AutoLigneDroite autoLigneDroite = new AutoLigneDroite();
	private Command autoCommand;

	private SendableChooser<String> chooser;
	private static final String GAUCHE = "Gauche";
	private static final String CENTRE = "Centre";
	private static final String DROITE = "Droite";
	private static final String LIGNE_DROITE = "Ligne droite";

	private String position;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		
		SmartDashboard.putData(pdp);
		SmartDashboard.putData("Scheduler", Scheduler.getInstance());
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(640, 480);
		camera.setFPS(22);

		chooser = new SendableChooser<>();
		chooser.addObject(DROITE, DROITE);
		chooser.addObject(GAUCHE, GAUCHE);
		chooser.addObject(CENTRE, CENTRE);

		SmartDashboard.putData("Position", chooser);

		K.init();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		ledController.setModeDebutMatch();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		System.out.println("Message : " + DriverStation.getInstance().getGameSpecificMessage());
		Robot.ledController.setModeAlliance();
		position = chooser.getSelected();
		startAuto();
	}

	public void startAuto(){

		String message = DriverStation.getInstance().getGameSpecificMessage();

		if(message.length() >= 2){

			if(position.equals(GAUCHE)){

				if(message.charAt(1) == 'L'){

					autoCommand =  autoGaucheScaleGauche;

				}

				else if(message.charAt(1) == 'R'){

					autoCommand = autoGaucheScaleDroite;

				}


			}	 

			else if(position.equals(CENTRE)){

				if(message.charAt(0) == 'L'){

					autoCommand = autoCentreSwitchGauche;

				}

				else if(message.charAt(0) == 'R'){

					autoCommand = autoCentreSwitchDroite;
				}
				
			}
			
			else if(position.equals(DROITE)){
				
				if(message.charAt(1) == 'R'){

					autoCommand = autoDroitScaleDroite;

				}

				else if(message.charAt(1) == 'L'){

					autoCommand = autoDroitScaleGauche;

				}

			}
			
			else if(position.equals(LIGNE_DROITE)) {
				
				autoCommand = autoLigneDroite;
				
			}
			
			autoCommand.start();
			
		}

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		if (autoCommand == null) {
			
			startAuto();
			
			if(DriverStation.getInstance().getMatchTime() < 12){
				
				DriverStation.reportError("Pas de GameData", false);
				
				autoCommand = autoLigneDroite;
			
				autoCommand.start();
				
			}
			
		}
		
		Scheduler.getInstance().run();
		
	}

	@Override
	public void teleopInit() {

		if(autoCommand != null){
			
			autoCommand.cancel();
		
		}
		
		K.update();
		ledController.setModeTeleop();
		new DemarrerElevateur().start();
		new SignalerLED().start();

	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Intake gauche", pdp.getCurrent(6));
		SmartDashboard.putNumber("Intake droit", pdp.getCurrent(7));
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

	}
}
