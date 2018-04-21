package com.ultime5528.frc2018.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DebutAutonome extends CommandGroup {

    public DebutAutonome(double temps, double hauteur) {
    	super("DebutAutonome " + temps + " " + hauteur);
    	
    	addParallel(new MaintienCube());
    	addSequential(new DemarrerElevateur());
		addSequential(new SetElevateur(0.07));
		addParallel(new PrendreCube());
		addSequential(new WaitCommand(0.5));
		addParallel(new MaintienCube());
		addSequential(new WaitCommand(temps));
		addSequential(new SetElevateur(hauteur));
    }
}
