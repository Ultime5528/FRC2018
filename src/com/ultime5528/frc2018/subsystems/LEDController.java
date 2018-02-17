package com.ultime5528.frc2018.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDController extends Subsystem {

	private SerialPort serial;
	private boolean estBranche;
	private String mode;
	
	
	public LEDController(){
		estBranche = false;
		
		try {
			
			serial = new SerialPort(9600, SerialPort.Port.kUSB1);
			estBranche = true;
			
		} catch (Exception e) {
			DriverStation.reportError("Arduino debranche", true);
		}
		
		Notifier n = new Notifier(() -> renvoyerMode());
		n.startPeriodic(0.5);
		
		
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
    
    public void setModeAuto(){
    	sendString("autonome");
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
    public void renvoyerMode(){
    	sendString(mode);
    }
    
    private void sendString(String command){
    	if(estBranche && command != null){
    		serial.writeString(command + "\n");
    		mode = command;
    		System.out.println(command);
    	}
    }
    
    public void setModeCurrentPeriod(){
    	if(DriverStation.getInstance().isAutonomous()){
    		setModeAuto();
    	}
    	else if(DriverStation.getInstance().getMatchTime() <= 30){
    		setModeEndGame();
    		}
    	else if(DriverStation.getInstance().isOperatorControl()){
    		setModeTeleop();
    	} else {
    		setModeDebutMatch();
    	}
    }
    
    public void setModeSignal1(){
    	sendString("signal1");
    	
    	Notifier notif = new Notifier(this::setModeCurrentPeriod);
    	notif.startSingle(3);
    	
    }
    public void setModeSignal2(){
    	sendString("signal2");
    	
    	Notifier notif = new Notifier(this::setModeCurrentPeriod);
    	notif.startSingle(3);
    }

    public void setModeDebutMatch() {
    	sendString("debutMatch");
    }
    
	public void setModeAlliance() {
		
		if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue) {
			sendString("bleu");
			
		}
		else {
			sendString("rouge");
		}
	}
}