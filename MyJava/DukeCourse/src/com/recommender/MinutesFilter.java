package com.recommender;

public class MinutesFilter implements Filter {
	private long min;
	private long max;
	
	public MinutesFilter(long min, long max) {
		this.min = min;
		this.max = max;
	}
	public boolean satisfies(String id) {
		
		int minutes = MovieDatabase.getMinutes(id);
		return minutes >= min && minutes <= max;
	}

}
