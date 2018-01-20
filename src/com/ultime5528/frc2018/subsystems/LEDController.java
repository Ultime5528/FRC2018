package com.ultime5528.frc2018.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDController extends Subsystem {

	private SerialPort serial;
	
	public LEDController(){
		serial = new SerialPort(9600, SerialPort.Port.kOnboard);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void allumerRouge(){
    	serial.writeString("r\n");
    }
   
}

