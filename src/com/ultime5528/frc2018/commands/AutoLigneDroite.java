package com.ultime5528.frc2018.commands;

import jaci.pathfinder.Waypoint;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLigneDroite extends CommandGroup {

    public AutoLigneDroite() {
        
    	addParallel(new DemarrerElevateur());
        addSequential(new SuivreTrajectoire(new Waypoint[]{
        		new Waypoint(0, 0 ,0),
        		new Waypoint(3.2, 0, 0)
        },0.45 , -0.1));
       
    }
}
