package com.course5.week1;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
	
	public void printAverageRatings() {
		SecondRatings sr = new SecondRatings();
		//ThirdRatings sr  = new ThirdRatings();
		System.out.println("Total size of movie: " + sr.getMovieSize());
		System.out.println("Total size of rater: " + sr.getRaterSize());
		
		ArrayList<Rating> ratings = sr.getAverageRatings(12);
		
		System.out.println(ratings.size());
		Collections.sort(ratings);
		
		for(Rating r : ratings) {
			System.out.format(" %2.2f %s \n", r.getValue(), sr.getTitle(r.getItem()));
		}
		
	}
	
	public void getAverageRatingOneMovie() {
		SecondRatings sr = new SecondRatings();
		ArrayList<Rating> ratings = sr.getAverageRatings(1);
		String movieName = "Vacation";
		for(Rating r : ratings) {
			if(sr.getTitle(r.getItem()).equals(movieName)){
				System.out.format("%f", r.getValue());
				return;
			}
		}
	}
	
	
	public static void main(String[] args) {
		MovieRunnerAverage mra = new MovieRunnerAverage();
		//mra.printAverageRatings();
		mra.getAverageRatingOneMovie();
	}

}
