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
	private int indexSegment = 0;
	private double vitesse, vitesseBrake;
	private double angleInitial;
	
	public static double VITESSE_BRAKE = -1.0;
	public static double ANGLE_P = 0.09;
	public static double THRESHOLD_VITESSE = 0.01;
	

	
	public SuivreTrajectoire(Waypoint[] points, double vitesse, double vitesseBrake) { 
		
		super("SuivreTrajectoire");
		
		vitesseBrake *= Math.signum(vitesse) * Math.signum(vitesseBrake) * -1;
		
		angleInitial = Pathfinder.r2d(points[0].angle);		
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_FAST, 0.05, Math.abs(vitesse), 100, 10000); 
		trajectory = Pathfinder.generate(points, config); 
		this.vitesseBrake = vitesseBrake;
		this.vitesse = vitesse;
		
		requires(Robot.basePilotable); 
		
	} 

	// Called just before this Command runs the first time 
	protected void initialize() { 
		Robot.basePilotable.resetGyro();
		Robot.basePilotable.resetEncoders();
		indexSegment = 0;    
	} 

	// Called repeatedly when this Command is scheduled to run 
	protected void execute() { 

		double distanceParcourue = Math.abs(Robot.basePilotable.getEncoderGaucheDistance() + Robot.basePilotable.getEncoderDroitDistance()) / 2.0;
		double vitesseGauche = vitesse;
		double vitesseDroite = vitesse;
		
		
		Robot.basePilotable.getAverageSpeedFilter().pidGet();

		while(indexSegment < trajectory.segments.length && trajectory.segments[indexSegment].position < distanceParcourue)
			indexSegment = indexSegment + 1;
		
		
		if(indexSegment >= trajectory.segments.length){
			Robot.basePilotable.tankDrive(vitesseBrake, vitesseBrake);
			return; 
		}
		
    	double error = Pathfinder.r2d(trajectory.segments[indexSegment].heading) - Robot.basePilotable.getHeading() - angleInitial;
    	error =  Pathfinder.boundHalfDegrees(error);
    	
    	double correction = ANGLE_P * error;
    	
    	Robot.basePilotable.tankDrive(vitesseGauche + correction, vitesseDroite - correction);

	} 

	// Make this return true when this Command no longer needs to run execute() 
	protected boolean isFinished() { 

		return (indexSegment >= trajectory.segments.length) && -1 * Math.signum(vitesseBrake)* Robot.basePilotable.getAverageSpeedFilter().get() < THRESHOLD_VITESSE; //Robot.basePilotable.trajectoireEstFinie(); 

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
