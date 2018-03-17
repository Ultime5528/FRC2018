package com.ultime5528.frc2018.commands;

import jaci.pathfinder.Waypoint;

import com.ultime5528.frc2018.K;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCentreSwitchGauche extends CommandGroup {

    public AutoCentreSwitchGauche() {

		CommandGroup commandeDebut = new CommandGroup("Debut");
		commandeDebut.addSequential(new DemarrerElevateur());
		commandeDebut.addSequential(new SetElevateur(0.7));
	   
	   // Add Commands here:
        
    	CommandGroup commandeAvancerLever = new CommandGroup("AvancerLever");
		commandeAvancerLever.addParallel(commandeDebut);
		commandeAvancerLever.addParallel(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(2.54, -1.7, 0),
				new Waypoint(3.0, -1.7, 0)
		}, 0.42, -0.2), 4);
		
		addSequential(commandeAvancerLever);
		addSequential(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));

    }
}
