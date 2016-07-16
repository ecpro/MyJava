package com.course5.week1;

import java.util.ArrayList;

public interface Rater {
	
	public void addRating(String item, double rating);
	public boolean hasRating(String item);
	public double getRating(String item);
	public int numRatings();
	public ArrayList<String> getItemsRated();
	
}
