package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.K;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCentreSwitchDroite extends CommandGroup {

   public AutoCentreSwitchDroite() {
        
		
		   
	   // Add Commands here
        
    	CommandGroup commandeAvancerLever = new CommandGroup("AvancerLever");
		commandeAvancerLever.addParallel(new DebutAutonome(0, 0.7));
		commandeAvancerLever.addParallel(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(2.54, 1.1, 0),
				new Waypoint(3.0, 1.1, 0)
		}, 0.42, -0.2), 4);
		
		addSequential(commandeAvancerLever);
		addSequential(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
		
		//Deuxieme cube
		
		addParallel(new SetElevateur(0));
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, Pathfinder.d2r(-180)),
				new Waypoint(-1.4, -0.4, Pathfinder.d2r(-205))
		}, -0.55, 0.1));
		
		addParallel(new PrendreCube());
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, Pathfinder.d2r(0)),
				new Waypoint(0.75, 0, Pathfinder.d2r(0))
		}, 0.35, 0.1), 3);
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, Pathfinder.d2r(-180)),
				new Waypoint(-0.75, 0, Pathfinder.d2r(-180))
		}, -0.55, 0.1));
		
		addParallel(new SetElevateur(0.7));
		
		addSequential(new Tourner(60,0.6 , -0.1));
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, Pathfinder.d2r(0)),
				new Waypoint(1.6, -0.1, Pathfinder.d2r(-30))
		}, 0.55, 0.1));
		
		addSequential(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
		

    }
}
