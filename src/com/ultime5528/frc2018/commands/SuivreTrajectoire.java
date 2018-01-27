package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.Robot;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SuivreTrajectoire extends Command {
	
	private Trajectory trajectory;

    public SuivreTrajectoire() {
    	
    	Waypoint[] points = new Waypoint[] {
    		    new Waypoint(0, 0, 0),      
    		    new Waypoint(2, 2, Pathfinder.d2r(90))
    		};

    		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, 
    				Trajectory.Config.SAMPLES_HIGH, 0.05, 0.5, 2.0, 60.0);
    		trajectory = Pathfinder.generate(points, config);
    		
    	requires(Robot.basePilotable);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.basePilotable.setTrajectoire(trajectory, 0.0);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.basePilotable.suivreTrajectoire();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       
    	return Robot.basePilotable.trajectoireEstFinie();
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