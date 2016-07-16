package com.recommender;
import java.util.*;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
        //this( "ratedmovies_short.csv","ratings_short.csv");
    }

	public ThirdRatings(String ratingfile) {
		FirstRatings fr = new FirstRatings();
		myRaters = (ArrayList<EfficientRater>) fr.loadRaters(ratingfile);
		//MovieDatabase.initialize("ratedmoviesfull.csv");
	}

    public int getRaterSize() {
    	return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
		int raterCount = 0;
		double averageRating = 0.0;
		for(EfficientRater r : myRaters) {
			double rating = r.getRating(id);
			if(rating != -1) {
				raterCount++;
				averageRating += rating;
			}
		}
		if(raterCount < minimalRaters) return 0.0;
		
		return averageRating / raterCount;
	}
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
    	
    	ArrayList<Rating> ratingList = new ArrayList<Rating> ();
    	for(String m : MovieDatabase.filterBy(new TrueFilter())) {
    		double averageRating = getAverageByID(m, minimalRaters);
    		if(averageRating != 0.0) {
    			ratingList.add(new Rating(m, averageRating));
    		}
    	}
    	
    	return ratingList;
    }
    
  
}
