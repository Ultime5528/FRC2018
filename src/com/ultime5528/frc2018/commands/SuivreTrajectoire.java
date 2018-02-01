package com.ultime5528.frc2018.commands; 

import com.ultime5528.frc2018.Robot; 

import jaci.pathfinder.Pathfinder; 
import jaci.pathfinder.Trajectory; 
import jaci.pathfinder.Waypoint; 
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command; 
import edu.wpi.first.wpilibj.interfaces.Gyro;

/** 
 * 
 */ 
public class SuivreTrajectoire extends Command { 

	private Trajectory trajectory; 
	private double maxVelocity = 1.7; 
	private int indexSegment = 0;
	private double vitesse;
	
	public static double VITESSE_BRAKE = -1.0;
	public static double ANGLE_P = 0.1;
	public static double THRESHOLD_VITESSE = 0.01;

	public SuivreTrajectoire() { 

		Waypoint[] points = new Waypoint[] { 
			new Waypoint(0, 0, 0),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees 
			new Waypoint(2, -0.5, Pathfinder.d2r(-45))                           // Waypoint @ x=0, y=0,   exit angle=0 radians 
		}; 

		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05,maxVelocity, 2.0, 60.0); 
		trajectory = Pathfinder.generate(points, config); 

		this.vitesse = 0.4;
		
		requires(Robot.basePilotable); 
		
	} 

	// Called just before this Command runs the first time 
	protected void initialize() { 
		Robot.basePilotable.resetGyro();
		Robot.basePilotable.resetEncoders();
		indexSegment = 0;//Robot.basePilotable.setTrajectoire(trajectory, maxVelocity); 
		VITESSE_BRAKE = Preferences.getInstance().getDouble("VITESSE_BRAKE", VITESSE_BRAKE);
    	ANGLE_P = Preferences.getInstance().getDouble("ANGLE_P", ANGLE_P);
    	THRESHOLD_VITESSE = Preferences.getInstance().getDouble("THRESHOLD_VITESSE", THRESHOLD_VITESSE);
    
	} 

	// Called repeatedly when this Command is scheduled to run 
	protected void execute() { 

		//Robot.basePilotable.suivreTrajectoire(); 

		double distanceParcourue = (Robot.basePilotable.getEncoderGaucheDistance() + Robot.basePilotable.getEncoderDroitDistance()) / 2.0;
		double vitesseGauche = vitesse;
		double vitesseDroite = vitesse;
		
		
		Robot.basePilotable.getAverageSpeedFilter().pidGet();

		while(indexSegment < trajectory.segments.length && trajectory.segments[indexSegment].position < distanceParcourue)
			indexSegment = indexSegment + 1;
		
		
		if(indexSegment >= trajectory.segments.length){
			Robot.basePilotable.tankDrive(VITESSE_BRAKE, VITESSE_BRAKE);
			return; 
		}
		System.out.println(indexSegment);
		
    	double error = Pathfinder.boundHalfDegrees(Pathfinder.r2d(trajectory.segments[indexSegment].heading)) - Robot.basePilotable.getHeading();
    	
    	double correction = ANGLE_P * error;
    	
    	Robot.basePilotable.tankDrive(vitesseGauche + correction, vitesseDroite - correction);

	} 

	// Make this return true when this Command no longer needs to run execute() 
	protected boolean isFinished() { 

		return (indexSegment >= trajectory.segments.length) && Robot.basePilotable.getAverageSpeedFilter().get() < THRESHOLD_VITESSE; //Robot.basePilotable.trajectoireEstFinie(); 

	} 

	// Called once after isFinished returns true 
	protected void end() { 

		Robot.basePilotable.stop(); 

	} 

	// Called when another command which requires one or more of the same 
	// subsystems is scheduled to run 
	protected void interrupted() { 

		end(); 

	} 
} 
