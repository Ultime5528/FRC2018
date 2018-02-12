package com.ultime5528.frc2018.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PrendreLeverCube extends CommandGroup {

    public PrendreLeverCube() {
    	
    	addSequential(new SetElevateur(0.0));
    	addParallel(new MaintienElevateur());
    	addSequential(new PrendreCube());
    	addSequential(new SetElevateur(0.2));

    }
}
