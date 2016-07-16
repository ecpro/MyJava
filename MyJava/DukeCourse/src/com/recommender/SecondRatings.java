package com.recommender;

/**
 * Write a description of SecondRatings here.
 * 
 * @author Piyush Ravi	
 * @version 3435.2
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
        //this( "ratedmovies_short.csv","ratings_short.csv");
    }

	public SecondRatings(String moviefile, String ratingfile) {
		FirstRatings fr = new FirstRatings();
		myMovies = (ArrayList<Movie>) fr.loadMovies(moviefile);
		myRaters = (ArrayList<EfficientRater>) fr.loadRaters(ratingfile);		
	}

    public int getMovieSize() {
    	return myMovies.size();
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
    	for(Movie m : myMovies) {
    		double averageRating = getAverageByID(m.getID(), minimalRaters);
    		if(averageRating != 0.0) {
    			ratingList.add(new Rating(m.getID(), averageRating));
    		}
    	}
    	
    	return ratingList;
    }
    
    public String getTitle(String id) {
    	for(Movie m : myMovies) {
    		if(m.getID().equals(id)) {
    			return m.getTitle();
    		}
    	}
    	return "ID not found";
    }
}
