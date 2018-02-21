package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.K;
import com.ultime5528.frc2018.util.Point;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.command.WaitForChildren;

/**
 *
 */
public class AutoGaucheScaleGauche extends CommandGroup {

	public AutoGaucheScaleGauche() {

		CommandGroup commandeDebut = new CommandGroup("Debut");
		commandeDebut.addSequential(new DemarrerElevateur());
		commandeDebut.addSequential(new SetElevateur(0.1));
		commandeDebut.addSequential(new WaitCommand(1));
		commandeDebut.addSequential(new SetElevateur(1.45));
		


		addParallel(commandeDebut);
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(6.4, 0.5, Pathfinder.d2r(17.5))
		}, 0.6, -0.07));


		addSequential(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
		

		//Deuxieme cubes
		
		addParallel(new PrendreLeverCube());
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0,Pathfinder.d2r(-180)),
				new Waypoint(-0.85, -0.70, Pathfinder.d2r(-110))
		}, -0.5, 0.1));
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0,Pathfinder.d2r(0)),
				new Waypoint(1, 0.7, Pathfinder.d2r(52)),
				new Waypoint(1.60, 1.65, Pathfinder.d2r(52))
		}, 0.35, -0.1),4.0);
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0,Pathfinder.d2r(-180)),
				new Waypoint(-1.20, 0.80, Pathfinder.d2r(-260)),
		}, -0.5, 0.1));
		
		addParallel(new SetElevateur(1.45));
		
		addParallel(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0,Pathfinder.d2r(0)),
				new Waypoint(1.7, -1, Pathfinder.d2r(-48)),
		}, 0.5, -0.1));
		
		addSequential(new WaitForChildren());

		addSequential(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
		
	}
		
}
