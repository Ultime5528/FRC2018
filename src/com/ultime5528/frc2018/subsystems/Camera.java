package com.ultime5528.frc2018.subsystems;

import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
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
		
		UsbCamera cam = new UsbCamera("Main Cam", 0);
		cam.setResolution(LARGEUR, HAUTEUR);
		cam.setFPS(20);
		cam.setBrightness(80);
		cam.setExposureManual(1);
		cam.setWhiteBalanceManual(4000);
		
		
		CvSink source = CameraServer.getInstance().getVideo(cam);
		CvSource video = CameraServer.getInstance().putVideo("Video", LARGEUR, HAUTEUR);
		CvSource videoinitial = CameraServer.getInstance().putVideo("Videointial", LARGEUR, HAUTEUR);
		
		Mat image = new Mat();
		
		while(!Thread.interrupted()){
			try {
				source.grabFrame(image);
				videoinitial.putFrame(image);
				detecterCube(image);
				
				video.putFrame(image);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
		}
		
	}
	
	private void detecterCube(Mat image){
		
		Mat output = new Mat();
    	ArrayList<Mat> channels = new ArrayList<>();
    	Core.split(image, channels);
    	
    	if(channels.size() < 3)
    		return;
    	
    	Core.add(channels.get(1), channels.get(2), output);
    	Core.addWeighted(output, 1, channels.get(0), -2.3, 0, output);
    	Core.absdiff(channels.get(1), channels.get(2), channels.get(1));
    	Core.addWeighted(output, 1, channels.get(1), -5, 0, output);
    	
 		
    	for(Mat c : channels)
    		c.release();
	
    	output.copyTo(image);
    	
    	output.release();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

