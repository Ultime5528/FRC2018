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
public class AutoDroitScaleDroitRecule extends CommandGroup {

    public AutoDroitScaleDroitRecule() {
       

    	CommandGroup commandeDebut = new CommandGroup("Debut");
		commandeDebut.addSequential(new DemarrerElevateur());
		commandeDebut.addSequential(new SetElevateur(0.1));
		commandeDebut.addSequential(new WaitCommand(0.8));
		commandeDebut.addSequential(new SetElevateur(1.45));

		addParallel(commandeDebut);
		addSequential(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(6.4, -0.5, Pathfinder.d2r(-17.5))
		}, 0.65, -0.07));

		addSequential(new WaitForChildren());
		
		addSequential(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));

		addParallel(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0,Pathfinder.d2r(-180)),
				new Waypoint(-3, -0.5, Pathfinder.d2r(-162.5)),
				
		}, -0.35, 0.1));
		
		addParallel(new SetElevateur(-0.005));
    }
}
