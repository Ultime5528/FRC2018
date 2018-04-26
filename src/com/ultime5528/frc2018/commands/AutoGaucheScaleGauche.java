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

		addParallel(new DebutAutonome(0.5, 1.45));
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(6.4, 0.5, Pathfinder.d2r(17.5))
		}, 0.65, -0.07));

		addSequential(new WaitForChildren(), 1.0);
		
		addSequential(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
		

		//Deuxieme cube
		
		CommandGroup prendreCubeGroup = new CommandGroup();
		
		addParallel(new PrendreLeverCube());
		
		// Augmenter la distance de recul
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0,Pathfinder.d2r(-180)),
				new Waypoint(-0.57, -0.0, Pathfinder.d2r(-180))
		}, -0.6, 0.1));
		
		addSequential(new Tourner(142, 0.6, -0.1));
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0,Pathfinder.d2r(0)),
				new Waypoint(1.65, 0, Pathfinder.d2r(0))
		}, 0.35, -0.1), 3.5);
		
		// Separer en deux avancees
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0,Pathfinder.d2r(-180)),
				new Waypoint(-0.5, 0, Pathfinder.d2r(-180)),
				
		}, -0.35, 0.1));
		
		addSequential(new Tourner(-138, -0.6, 0.1));
		
		addParallel(new SetElevateur(1.50));
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0,Pathfinder.d2r(0)),
				new Waypoint(1.60 , 0, Pathfinder.d2r(0))
				
		}, 0.65, -0.1));
		
		addSequential(new WaitForChildren());

		addSequential(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
		
		addParallel(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0,Pathfinder.d2r(-180)),
				new Waypoint(-0.5, 0, Pathfinder.d2r(-180)),
				
		}, -0.35, 0.1));
		
		addSequential(new SetElevateur(-0.005));
		
	}
		
}
