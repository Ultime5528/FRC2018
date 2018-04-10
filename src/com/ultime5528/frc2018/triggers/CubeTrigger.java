package com.ultime5528.frc2018.triggers;

import com.ultime5528.frc2018.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class CubeTrigger extends Trigger {

    public boolean get() {
        return Robot.camera.cubeDetecte();
    }
}
