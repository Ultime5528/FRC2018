package com.ultime5528.frc2018.commands;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

import com.ultime5528.frc2018.K;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class AutonomeGaucheScaleGaucheSwitchGauche extends CommandGroup {

    public AutonomeGaucheScaleGaucheSwitchGauche() {
        
    	CommandGroup commandeDebut = new CommandGroup("Debut");
		commandeDebut.addSequential(new DemarrerElevateur());
		commandeDebut.addSequential(new SetElevateur(0.1));


		addParallel(commandeDebut);
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(5, -0.15, Pathfinder.d2r(18.5))
		}, 0.6, -0.2));

		CommandGroup commandeAvancerLever = new CommandGroup("AvancerLever");
		commandeAvancerLever.addParallel(new SetElevateur(1.45));
		commandeAvancerLever.addParallel(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(1.42, 0.0, 0)
		}, 0.4, -0.1));
		commandeAvancerLever.addSequential(new PrintCommand("fin avancerLever"));

		addSequential(commandeAvancerLever);
		addSequential(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
		
		addSequential(new Tourner(122, 0.5, -0.6));
	
		CommandGroup commandeTournerAvancer = new CommandGroup("TournerDescendre");
		commandeTournerAvancer.addParallel(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(1.5, 0, 0)}, 0.5, -0.2));
		
		commandeTournerAvancer.addParallel(new SetElevateur(-0.005));
		
		addSequential(commandeTournerAvancer);
		
		addParallel(new PrendreLeverCube());
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(0.6, 0, 0)}, 0.30, -0.2), 2);
		
		
		
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, Pathfinder.d2r(-180)),
				new Waypoint(-1, 0, Pathfinder.d2r(-180))
				
		}, -0.35, 0.2));
		
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
