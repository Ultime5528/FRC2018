package com.ultime5528.frc2018.subsystems;

import java.sql.Driver;

import com.ultime5528.frc2018.commands.SignalerLED;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDController extends Subsystem {

	private SerialPort serial;
	private boolean estBranche;
	
	public LEDController(){
		estBranche = false;
		
		try {
			
			serial = new SerialPort(9600, SerialPort.Port.kUSB1);
			estBranche = true;
			
		} catch (Exception e) {
			DriverStation.reportError("Arduino debranche", true);
		}
		
		
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new SignalerLED());
    }
    
    public void setModeAuto(){
    	sendString("auto");
    }
   
    public void setModeTeleop(){
    	sendString("teleop");
    	
    }
  
    public void setModeCube(){
    	sendString("cube");	
    }
    
    public void setModeEndGame(){
    	sendString("endGame");
    }
    
    public void setModeMonter(){
    	sendString("monter");
    }

    private void sendString(String command){
    	if(estBranche){
    		serial.writeString(command);
    	}
    }
    
    public void setModeCurrentPeriod(){
    	if(DriverStation.getInstance().isAutonomous()){
    		setModeAuto();
    	}
    	else if(DriverStation.getInstance().getMatchTime() <= 30){
    		setModeEndGame();
    		}
    	else{
    		setModeTeleop();
    	}
    }
    
    public void setModeSignal1(){
    	sendString("signal1");
    }
    public void setModeSignal2(){
    	sendString("signal2");
    }
}