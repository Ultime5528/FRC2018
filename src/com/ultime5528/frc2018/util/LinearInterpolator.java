package com.ultime5528.frc2018.util;

import org.opencv.core.Point;

public class LinearInterpolator {

	private Point[] points;

	public LinearInterpolator(Point[] points){

		this.points = points;	
	}

	public double interpolate(double x){

		int i = 0;
		
		while(x <= points[i].x){
			i++;
		}

		return (points[i].y - points[i-1].y)/(points[i].x - points[i-1].x)*(x - points[i-1].x) + points[i-1].y; 
	}

}

