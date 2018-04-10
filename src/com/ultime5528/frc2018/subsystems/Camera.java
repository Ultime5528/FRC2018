package com.ultime5528.frc2018.subsystems;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Camera extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static final int LARGEUR = 320;
	public static final int HAUTEUR = 240;
	private AtomicBoolean cubeDetecte;
	
	
	
	public Camera(){
		
		new Thread(this::visionLoop).start();
		cubeDetecte = new AtomicBoolean(false);
		
	}

	
	private void visionLoop(){
		
		UsbCamera cam = new UsbCamera("Main Cam", 0);
		cam.setResolution(LARGEUR, HAUTEUR);
		cam.setFPS(20);
		cam.setExposureAuto();
		cam.setWhiteBalanceAuto();
		//cam.setBrightness(10);
		//cam.setExposureManual(22);
		//cam.setWhiteBalanceManual(3000);
		
		
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
    	
    	// Vert + Rouge -> output (jaune)
    	Core.add(channels.get(1), channels.get(2), output);
    	
    	// Output - K * Bleu -> output
    	Core.addWeighted(output, 1, channels.get(0), -2.0, 0, output);
    	
    	// |Vert - Rouge| -> Channel 1 (difference absolue entre rouge et vert 
    	Core.absdiff(channels.get(1), channels.get(2), channels.get(1));
    	
    	// Output + K * AbsDiff -> Output
    	Core.addWeighted(output, 1, channels.get(1), -5, 0, output);
 		
    	for(Mat c : channels)
    		c.release();
    	
    	Rect roi = new Rect( (int)(0.3*LARGEUR), (int)(0.3*HAUTEUR),(int)( 0.55*LARGEUR), (int)(0.6*HAUTEUR));
    	Imgproc.rectangle(output, roi.br(), roi.tl(), new Scalar(255));
    	
    	Mat cropped = new Mat(output, roi);
    	
    	double moyenne = Core.sumElems(cropped).val[0] / (cropped.rows()*cropped.cols());
    	
    	cubeDetecte.set(moyenne > 40);
    	cropped.release();
	
    	SmartDashboard.putNumber("moyenne", moyenne);
    	output.copyTo(image);
    	
    	output.release();
    	
    	
	}
	
	public boolean cubeDetecte() {
		
		return cubeDetecte.get();
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

