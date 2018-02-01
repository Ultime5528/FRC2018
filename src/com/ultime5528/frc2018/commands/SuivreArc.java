package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.K;
import com.ultime5528.frc2018.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.filters.LinearDigitalFilter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SuivreArc extends Command {
	
	public static double MAX_RADIUS = 600.0;
	public static double VITESSE_BRAKE = -1.0;
	public static double ANGLE_P = 0.1;
	public static double THRESHOLD_VITESSE = 0.01;
	
	double xForward, ySide, radius, angle, distance;
	double vGauche, vDroite, vBrake;
	
	boolean enLigneDroite = false;
	boolean shouldBrake = false;
	
	

    public SuivreArc(double xForward, double ySide, double vitesse) {
        
    	super("SuivreArc");
    	requires(Robot.basePilotable);
        
    	this.xForward = xForward;
    	this.ySide = ySide;
        
    	radius = (xForward * xForward + ySide * ySide) / (2 * ySide);
    	
        if(Math.abs(radius) <= MAX_RADIUS) {
        	
        	angle = Math.asin(xForward / radius);
        	distance = radius * angle;
            System.out.println("Distance : " + distance);
        	
            //Si on tourne vers la droite, la roue gauche va plus vite.
            vGauche = vitesse;
        	vDroite = vitesse * (radius - K.BasePilotable.LARGEUR / 2) / (radius + K.BasePilotable.LARGEUR / 2);
            
        	// Si on tourne vers la gauche, les vitesses sont inversees. 
            if(radius < 0.0) {
            	
            	vGauche = vDroite;
            	vDroite = vitesse;
            	
            } 
            
        	
        } else {
        	
        	distance = xForward;
        	
        	vitesse *= Math.signum(xForward);
        	
        	vGauche = vitesse;
        	vDroite = vitesse;
        	enLigneDroite = true;
        	
        }
        
        vBrake = VITESSE_BRAKE * Math.signum(xForward);
        /*
        Preferences.getInstance().putDouble("MAX_RADIUS", MAX_RADIUS);
        Preferences.getInstance().putDouble("VITESSE_BRAKE", VITESSE_BRAKE);
        Preferences.getInstance().putDouble("ANGLE_P", ANGLE_P);
        Preferences.getInstance().putDouble("THRESHOLD_VITESSE", THRESHOLD_VITESSE);
        */
    }

    
    protected void initialize() {
    	Robot.basePilotable.resetGyro();
    	Robot.basePilotable.resetEncoders();
    	Robot.basePilotable.getAverageSpeedFilter().reset();
    	
    	shouldBrake = false;
    	
    	MAX_RADIUS = Preferences.getInstance().getDouble("MAX_RADIUS", MAX_RADIUS);
    	VITESSE_BRAKE = Preferences.getInstance().getDouble("VITESSE_BRAKE", VITESSE_BRAKE);
    	ANGLE_P = Preferences.getInstance().getDouble("ANGLE_P", ANGLE_P);
    	THRESHOLD_VITESSE = Preferences.getInstance().getDouble("THRESHOLD_VITESSE", THRESHOLD_VITESSE);
    	
    }

    
    protected void execute() {
    	
    	SmartDashboard.putNumber("Average speed filter", Robot.basePilotable.getAverageSpeedFilter().pidGet());
    	
    	if(shouldBrake) {
    		
    		Robot.basePilotable.tankDrive(vBrake, vBrake);
    		return;
    		
    	}
    	
    	double error, correction;
    	double angleTheo = 0.0;
    	
    	double distanceParcourue = (Robot.basePilotable.getEncoderGaucheDistance() + Robot.basePilotable.getEncoderDroitDistance()) / 2.0;
    	
    	
    	
    	if(distanceParcourue >= distance)
    		shouldBrake = true;
    	
    	if(!enLigneDroite)
    		angleTheo = Math.toDegrees(distanceParcourue / radius);
    		
    	
    	error = angleTheo - Robot.basePilotable.getHeading();
    	correction = ANGLE_P * error;
    	
    	Robot.basePilotable.tankDrive(vGauche + correction, vDroite - correction);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return shouldBrake && Robot.basePilotable.getAverageSpeedFilter().get() < THRESHOLD_VITESSE;
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
