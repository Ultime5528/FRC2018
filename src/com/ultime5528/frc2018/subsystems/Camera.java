package com.ultime5528.frc2018.subsystems;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
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
	private AtomicBoolean intake;

	
	// Mettre a TRUE pour envoyer l'image de la vision sur le Dashboard
	private final boolean DEBUG_VISION = false;

	public Camera(){

		new Thread(this::visionLoop).start();
		cubeDetecte = new AtomicBoolean(false);
		intake = new AtomicBoolean(false);

	}

	public void setIntake(boolean intake){
		this.intake.set(intake);
	}

	private void visionLoop(){

		UsbCamera cam = new UsbCamera("Main Cam", 0);
		cam.setResolution(LARGEUR, HAUTEUR);
		cam.setFPS(20);
		//cam.setExposureAuto();
		//cam.setWhiteBalanceAuto();
		cam.setBrightness(50);
		cam.setExposureManual(10);
		cam.setWhiteBalanceManual(5000);


		CvSink source = CameraServer.getInstance().getVideo(cam);
		CvSource videoinitial = CameraServer.getInstance().putVideo("Video initial", LARGEUR, HAUTEUR);

		CvSource video = null;
		
		if(DEBUG_VISION)
			video = CameraServer.getInstance().putVideo("Video vision", LARGEUR, HAUTEUR);
		
		Mat image = new Mat(), output = new Mat();

		while(!Thread.interrupted()){
			try {
				
				source.grabFrame(image);

				detecterCube(image, output);
				
				if(DEBUG_VISION)
					video.putFrame(output);
				
				ecrireInfo(image, (int)DriverStation.getInstance().getMatchTime(), intake.get());
				videoinitial.putFrame(image);


			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}



	private void detecterCube(Mat image, Mat output){

		ArrayList<Mat> channels = new ArrayList<>();
		Core.split(image, channels);

		if(channels.size() < 3)
			return;

		// Vert + Rouge -> output (jaune)
		Core.add(channels.get(1), channels.get(2), output);

		// Output - K * Bleu -> output
		Core.addWeighted(output, 1, channels.get(0), -1.5, 0, output);

		// |Vert - Rouge| -> Channel 1 (difference absolue entre rouge et vert 
		Core.absdiff(channels.get(1), channels.get(2), channels.get(1));

		// Output + K * AbsDiff -> Output
		Core.addWeighted(output, 1, channels.get(1), -3, 0, output);

		for(Mat c : channels)
			c.release();

		Rect roi = new Rect( (int)(0.6*LARGEUR), (int)(0.8*HAUTEUR),(int)( 0.1*LARGEUR), (int)(0.1*HAUTEUR));
		Imgproc.rectangle(output, roi.br(), roi.tl(), new Scalar(255));

		Mat cropped = new Mat(output, roi);

		double moyenne = Core.sumElems(cropped).val[0] / (cropped.rows()*cropped.cols());

		cubeDetecte.set(moyenne > 40);
		cropped.release();

		SmartDashboard.putNumber("moyenne", moyenne);
		
	}


	private void ecrireInfo( Mat img, int tempsRestant, boolean intake ) {

		Scalar couleur;

		if(tempsRestant >= 30)
			couleur = new Scalar(0, 255, 0);

		else
			couleur = new Scalar(0, 255, 255);


		Imgproc.rectangle(img, new Point(0, HAUTEUR * 0.9) , new Point(LARGEUR * tempsRestant / 135.0, HAUTEUR), couleur, -1, 8, 0);

		Size tailleTexte = Imgproc.getTextSize(tempsRestant + "", Core.FONT_HERSHEY_DUPLEX, 1, 3, null);
		Imgproc.putText(img, tempsRestant + "", new Point((LARGEUR / 2 ) - (tailleTexte.width / 2) , (HAUTEUR * 0.97)), Core.FONT_HERSHEY_DUPLEX, 0.8, new Scalar(0, 0, 255), 1);

		Imgproc.putText(img, "Intake:", new Point(0 , (HAUTEUR * 0.08)), Core.FONT_HERSHEY_COMPLEX_SMALL, 1, new Scalar(255, 255, 255), 1);

		if(intake){
			Imgproc.putText(img, "ON", new Point(LARGEUR * 0.28, (HAUTEUR * 0.08)), Core.FONT_HERSHEY_COMPLEX_SMALL, 1, new Scalar(0, 255, 0), 1);
		}
		else{
			Imgproc.putText(img, "OFF", new Point(LARGEUR * 0.28, (HAUTEUR * 0.08)), Core.FONT_HERSHEY_COMPLEX_SMALL, 1, new Scalar(0, 0, 255), 1);
		}

		Imgproc.rectangle(img, new Point(0, HAUTEUR * 0.05),new Point(0, LARGEUR * 0.5) , new Scalar(255, 255, 255), -1, 8, 0);

	}

	public boolean cubeDetecte() {

		return cubeDetecte.get();

	}
	
	
	
	public boolean hasCube(){
		
		return intake.get();
		
	}
	
	
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
}

