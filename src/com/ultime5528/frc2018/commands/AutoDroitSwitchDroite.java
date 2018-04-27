package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.K;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Waypoint;

/**
 *
 */
public class AutoDroitSwitchDroite extends CommandGroup {

    public AutoDroitSwitchDroite() {
       
    	CommandGroup commandeAvancerLever = new CommandGroup("AvancerLever");
		commandeAvancerLever.addParallel(new DebutAutonome(0, 0.7));
		commandeAvancerLever.addParallel(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(2.5, 0.2, 10),
				new Waypoint(3.9, -0.6, -90)
		}, 0.42, -0.2), 6);
		
		addSequential(commandeAvancerLever);
		addSequential(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
    	
    }

}
