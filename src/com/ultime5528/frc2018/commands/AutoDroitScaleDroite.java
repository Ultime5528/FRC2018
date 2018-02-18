package com.ultime5528.frc2018.commands;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

import com.ultime5528.frc2018.K;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.command.WaitForChildren;

/**
 *
 */
public class AutoDroitScaleDroite extends CommandGroup {

    public AutoDroitScaleDroite() {
     
    	CommandGroup commandeDebut = new CommandGroup("Debut");
		commandeDebut.addSequential(new DemarrerElevateur());
		commandeDebut.addSequential(new SetElevateur(0.1));
		commandeDebut.addSequential(new WaitCommand(1));
		commandeDebut.addSequential(new SetElevateur(1.45));
		
		addParallel(commandeDebut);
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(6.3, -0.55, Pathfinder.d2r(-17.5))
		}, 0.6, -0.1));


		addSequential(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
		
		
		//Deuxieme cubes
		
		addParallel(new PrendreLeverCube());
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0,Pathfinder.d2r(-180)),
				new Waypoint(-0.9, 0.8, Pathfinder.d2r(-250))
		}, -0.3, 0.1));
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0,Pathfinder.d2r(0)),
				new Waypoint(1, -0.7, Pathfinder.d2r(-60)),
				new Waypoint(1.5, -1.566, Pathfinder.d2r(-60))
		}, 0.3, -0.1));
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0,Pathfinder.d2r(-180)),
				new Waypoint(-1.25, -1.0, Pathfinder.d2r(-100)),
		}, -0.3, 0.1));
		
		addParallel(new SetElevateur(1.45));
		
		addParallel(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0,Pathfinder.d2r(0)),
				new Waypoint(1.7, 1, Pathfinder.d2r(45)),
		}, 0.3, -0.1));
		
		addSequential(new WaitForChildren());

		addSequential(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
		
		
		
    }
}
