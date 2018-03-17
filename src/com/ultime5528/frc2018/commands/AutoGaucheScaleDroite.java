package com.ultime5528.frc2018.commands;

import com.ultime5528.frc2018.K;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.command.WaitForChildren;

/**
 *
 */
public class AutoGaucheScaleDroite extends CommandGroup {

    public AutoGaucheScaleDroite() {

    	CommandGroup commandeDebut = new CommandGroup("Debut");
		commandeDebut.addSequential(new DemarrerElevateur());
		commandeDebut.addSequential(new SetElevateur(0.1));
		commandeDebut.addSequential(new WaitCommand(5));
		commandeDebut.addSequential(new SetElevateur(1.45));
    	
        addParallel(commandeDebut);
		
        addSequential(new SuivreTrajectoire(new Waypoint[]{
        	new Waypoint(0, 0, 0),
        	new Waypoint(5.25, 2.5, Pathfinder.d2r(90)),
        	new Waypoint(5.25, 4.3, Pathfinder.d2r(75)),
        	new Waypoint(6.70, 4.85, Pathfinder.d2r(-25))
        }, 0.485, -0.08));
       
		addSequential(new WaitForChildren());
		
        addSequential(new LancerCube(K.Intake.VITESSE_LANCER_LOIN));
        
		addParallel(new SuivreTrajectoire(new Waypoint[] {
				new Waypoint(0, 0,Pathfinder.d2r(-180)),
				new Waypoint(-0.5, 0, Pathfinder.d2r(-180)),
				
		}, -0.35, 0.1));
		
		addSequential(new SetElevateur(-0.005));
		
    }
}
