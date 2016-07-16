	package com.course4.week1;

public class DepthFilter implements Filter {
	
	private double minDepth;
	private double maxDepth;

	public DepthFilter(double minDepth, double maxDepth) {
		this.minDepth = minDepth;
		this.maxDepth = maxDepth;
	}
	public boolean satisfies(QuakeEntry qe) {
		double depth = qe.getDepth();
		return depth <= maxDepth && depth >= minDepth;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return "Depth Filter";
	}

}