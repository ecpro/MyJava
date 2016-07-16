package com.course4.week1;
public class DistanceFilter implements Filter {
	
	private Location loc;
	private double maxDistance;
	
	
	public DistanceFilter(Location loc, double maxDistance) {
		this.loc = loc;
		this.maxDistance = maxDistance;
	}
	public boolean satisfies(QuakeEntry qe) {
		
		return qe.getLocation().distanceTo(loc) < maxDistance;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return "Distance Filter";
	}

}
