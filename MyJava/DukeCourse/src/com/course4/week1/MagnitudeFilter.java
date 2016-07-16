package com.course4.week1;
public class MagnitudeFilter implements Filter {
	
	private double minMag;
	private double maxMag;

	public MagnitudeFilter(double minMag, double maxMag) {
		this.minMag = minMag;
		this.maxMag = maxMag;
	}
	public boolean satisfies(QuakeEntry qe) {
		double mag = qe.getMagnitude();
		return mag <= maxMag && mag >= minMag;
	}
	public String getName() {
		return "Magnitude Filter";
	}

}
