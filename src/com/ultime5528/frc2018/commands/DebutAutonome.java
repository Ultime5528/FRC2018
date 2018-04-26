package com.ultime5528.frc2018.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DebutAutonome extends CommandGroup {

    public DebutAutonome(double temps, double hauteur) {
    	this(temps, hauteur, true);
    }

	public DebutAutonome(double temps, double hauteur, boolean stopIntake) {
		super("DebutAutonome " + temps + " " + hauteur);
    	
    	addParallel(new MaintienCube());
    	addSequential(new DemarrerElevateur());
    	addParallel(new PrendreCube());
    	addSequential(new SetElevateur(0.07));
		addSequential(new WaitCommand(0.5));
		//addParallel(new MaintienCube()); // A enlever?
		addSequential(new WaitCommand(temps));
		addSequential(new SetElevateur(hauteur));
		
		if(stopIntake)
			addSequential(new StopIntake());
	}
}
