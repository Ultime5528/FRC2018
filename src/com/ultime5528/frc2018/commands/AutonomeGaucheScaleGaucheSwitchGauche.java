package com.ultime5528.frc2018.commands;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

import com.ultime5528.frc2018.K;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomeGaucheScaleGaucheSwitchGauche extends CommandGroup {

    public AutonomeGaucheScaleGaucheSwitchGauche() {
        
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
    	// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
