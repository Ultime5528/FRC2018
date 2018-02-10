package com.ultime5528.frc2018.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.analog.adis16448.frc.ADIS16448_IMU.Axis;
import com.ultime5528.frc2018.K;
import com.ultime5528.frc2018.Robot;
import com.ultime5528.frc2018.commands.Pilotage;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.filters.LinearDigitalFilter;

/**
 *
 */
public class BasePilotable extends Subsystem {

	private VictorSP moteurGauche;
	private VictorSP moteurDroit;
	private DifferentialDrive drive;

	private ADIS16448_IMU gyro;
	private Encoder encoderGauche;
	private Encoder encoderDroit;
	
	private LinearDigitalFilter averageSpeedFilter, angleSpeedFilter;
	private PIDSource averageSpeed, angleSpeed;
	
	public BasePilotable() {
		super("Base pilotable");

		moteurGauche = new VictorSP(K.Ports.BASE_PILOTABLE_MOTEUR_GAUCHE);
		addChild("Moteur Gauche", moteurGauche);

		moteurDroit = new VictorSP(K.Ports.BASE_PILOTABLE_MOTEUR_DROIT);
		addChild("Moteur Droit", moteurDroit);

		drive = new DifferentialDrive(moteurGauche, moteurDroit);
		drive.setMaxOutput(1.0);
		encoderGauche = new Encoder(K.Ports.BASE_PILOTABLE_ENCODER_GAUCHE_A, K.Ports.BASE_PILOTABLE_ENCODER_GAUCHE_B);
		encoderGauche.setDistancePerPulse(-0.00023456);
		addChild("encodeur gauche", encoderGauche);

		encoderDroit = new Encoder(K.Ports.BASE_PILOTABLE_ENCODER_DROIT_A, K.Ports.BASE_PILOTABLE_ENCODER_DROIT_B);
		encoderDroit.setDistancePerPulse(0.00023456);
		addChild("encodeur droit", encoderDroit);

		gyro = new ADIS16448_IMU(Axis.kX);
		gyro.calibrate();
		addChild("Gyro", gyro);
		
		averageSpeed = new PIDSource() {
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
			}
			@Override
			public double pidGet() {
				return (encoderDroit.getRate() + encoderGauche.getRate()) / 2;
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kRate;
			}
		};
		
		angleSpeed = new PIDSource() {
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
			}
			@Override
			public double pidGet() {
				return gyro.getRate();
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kRate;
			}
		};
		
		averageSpeedFilter = LinearDigitalFilter.movingAverage(averageSpeed, 10);
		angleSpeedFilter = LinearDigitalFilter.movingAverage(angleSpeed, 10);
		
	}
	
	public double getAverageSpeed() {
		return averageSpeed.pidGet();
	}
	public LinearDigitalFilter getAngleSpeedFilter() {
		return angleSpeedFilter;
	}
	public LinearDigitalFilter getAverageSpeedFilter() {
		return averageSpeedFilter;
	}


	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Pilotage());

	}
	
	
	public void resetGyro() {
		gyro.reset();
	}
	
	public void resetEncoders() {
		encoderGauche.reset();
		encoderDroit.reset();
	}

	public double getEncoderGaucheDistance() {
		return encoderGauche.getDistance();
	}
	
	public double getEncoderDroitDistance() {
		return encoderDroit.getDistance();
	}
	
	public double getEncoderGaucheVitesse() {
		return encoderGauche.getRate();
	}
	
	public double getHeading() {
		return gyro.getYaw();
	}
	
	public void tankDrive(double left, double right) {
		System.out.println("Gauche : " + left + "\tDroit : " + right);
		drive.tankDrive(left, right, false);
	}
	
	public void drive() {
		Joystick joystick = Robot.oi.getJoystick();
		drive.arcadeDrive(Robot.oi.getInterY().interpolate(-joystick.getY()), joystick.getX());
	}

	public void stop() {
		drive.arcadeDrive(0, 0);
	}
}
