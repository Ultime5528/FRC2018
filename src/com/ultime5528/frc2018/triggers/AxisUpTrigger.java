package com.ultime5528.frc2018.triggers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class AxisUpTrigger extends Trigger {
	private GenericHID joystick;
	private int axis;
	
	public AxisUpTrigger(GenericHID joystick, int axis) {
	
		this.joystick = joystick;
		this.axis = axis;
		
	}

    public boolean get() {
        return joystick.getRawAxis(axis) < -0.5;
    }
}
