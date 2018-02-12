package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.K;
import com.ultime5528.frc2018.util.Point;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomeGaucheScaleGauche extends CommandGroup {

	public AutonomeGaucheScaleGauche() {

		CommandGroup commandeDebut = new CommandGroup("Debut");
		commandeDebut.addSequential(new DemarrerElevateur());
		commandeDebut.addSequential(new SetElevateur(0.1));
		commandeDebut.addSequential(new MaintienElevateur());


		addParallel(commandeDebut);
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(5, -0.15, Pathfinder.d2r(17.5))
		}, 0.6, -0.2));

		CommandGroup commandeAvancerLever = new CommandGroup("AvancerLever");
		commandeAvancerLever.addParallel(new SetElevateur(1.4));
		commandeAvancerLever.addParallel(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(1.25, 0.0, 0)
		}, 0.4, -0.1));

		addSequential(commandeAvancerLever);
		addSequential(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
		
		CommandGroup commandeTournerDescendre = new CommandGroup("TournerDescendre");
		commandeTournerDescendre.addParallel(new SetElevateur(0));
		commandeTournerDescendre.addParallel(new Tourner(120, 0.5, -0.6));
		
		addSequential(commandeTournerDescendre);
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(1.5, 0, 0)}, 0.5, -0.2));
		
		addParallel(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(1.5, 0, 0)}, 0.35, -0.2));
		
		addSequential(new PrendreLeverCube());
		
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, Pathfinder.d2r(-180)),
				new Waypoint(-1.75, 0, Pathfinder.d2r(-185))}, -0.4, 0.2));
		
		CommandGroup commandeReculerTourner = new CommandGroup("ReculerTourner");
		commandeReculerTourner.addParallel(new SetElevateur(1.4));
		commandeReculerTourner.addParallel(new Tourner(-120, -0.4, 0.6));
		
		addSequential(commandeReculerTourner);
		addParallel(new MaintienElevateur());
		addSequential(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
	}
		
}
