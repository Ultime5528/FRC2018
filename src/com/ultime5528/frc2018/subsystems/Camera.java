package com.ultime5528.frc2018.subsystems;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static final int LARGEUR = 320;
	public static final int HAUTEUR = 240;
	
	
	public Camera(){
		
		new Thread(this::visionLoop).start();
	}

	
	private void visionLoop(){
		
		CvSink source = CameraServer.getInstance().getVideo();
		CvSource video = CameraServer.getInstance().putVideo("Video", LARGEUR, HAUTEUR);
		
		Mat image = new Mat();
		
		while(!Thread.interrupted()){
			
			source.grabFrame(image);
			//
			video.putFrame(image);
			
			
		}
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

