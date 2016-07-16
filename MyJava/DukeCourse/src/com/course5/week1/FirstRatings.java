package com.course5.week1;

import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;
import java.util.*;

public class FirstRatings {
			
	public ArrayList<Movie> loadMovies(String filename) {
		FileResource fr = new FileResource("data"+File.separator+filename);
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		
		CSVParser parser = fr.getCSVParser();
		for(CSVRecord record : parser) {
			String id = record.get("id");
			String title = record.get("title");
			String year = record.get("year");
			String genre = record.get("genre");
			String director = record.get("director");
			String country = record.get("country");
			String poster = record.get("poster");
			int minutes = Integer.parseInt(record.get("minutes"));
			
			movieList.add(new Movie(id, title, year, genre, director, country, poster, minutes));
		}
		
		return movieList;
	}
	
	public void testLoadMovies() {
		String filename = "ratedmoviesfull.csv";
		ArrayList<Movie> movieList = loadMovies(filename);
		System.out.println("Total movies: " + movieList.size());
		
		
		int comedyCount= 0;
		int timeCount= 0;
		for(Movie m : movieList) {
			if(m.getGenres().contains("Comedy")) {
				comedyCount++;
			}
			if(m.getMinutes() > 150) {
				timeCount++;
			}
		}
		
		System.out.println("movies with time > 150 : " + timeCount);
		System.out.println("movies with genre comedy : " + comedyCount);
		
		HashMap<String, Integer> director_MovieCount = new HashMap<String, Integer>();
		
		for(Movie curr: movieList) {
			String [] directors = curr.getDirector().split(",");
			
			for(int i = 0 ; i < directors.length; i++) {
				if(director_MovieCount.isEmpty() || !director_MovieCount.containsKey(directors[i])) {
					director_MovieCount.put(directors[i].trim(), 1);
				}
				else {
					int count = director_MovieCount.get(directors[i].trim());
					count++;
					director_MovieCount.put(directors[i].trim(), count);
				}
			}
		}
		
		int max = 0;
		String name ="";
		for(Map.Entry<String, Integer> entry : director_MovieCount.entrySet()) {
			if(entry.getValue() > max) {
				max = entry.getValue();
				name = entry.getKey();
			}
		}
		System.out.println("Max no movies by a director " + max);
		System.out.println("Director with maximum movies " + name);
		for(Map.Entry<String, Integer> entry: director_MovieCount.entrySet()) {
			System.out.println(entry.getKey() + "  " + entry.getValue());
		}
		
		
		
	}
	
	public List<EfficientRater> loadRaters(String filename) {
		FileResource fr = new FileResource("data" + File.separator + filename);
		CSVParser parser = fr.getCSVParser();
		List<EfficientRater> raterList = new ArrayList<EfficientRater> ();
		
		for(CSVRecord rec : parser) {
			String rater_id = rec.get("rater_id");
			String movie_id = rec.get("movie_id");
			Double rating = Double.parseDouble(rec.get("rating"));
			if(raterList.isEmpty()){
				EfficientRater tempRater = new EfficientRater(rater_id);
				tempRater.addRating(movie_id, rating);
				raterList.add(tempRater);
			}
			else {
				boolean hasRater = false;
				EfficientRater tempRater = null;
				for(EfficientRater rater : raterList) {
					if(rater.getID().equals(rater_id)) {
						tempRater = rater;
						hasRater = true;
						break;
					}
					tempRater = new EfficientRater(rater_id);
					tempRater.addRating(movie_id, rating);
					}
				if (hasRater) tempRater.addRating(movie_id, rating);
				else raterList.add(tempRater); 
				}
				
			}
		
		
		
//		for(CSVRecord rec : parser) {
//			
//			String myID = rec.get("rater_id");
//			boolean hasID = false;
//			for(PlainRater r : raterList) {
//					if(r.getID().equals(myID)) {
//						hasID = true;
//						break;
//				}
//			}
//			
//			if(hasID) continue;
//			raterList.add(new EfficientRater(myID));
//			
//			
//		}
//		
//		
//		for(CSVRecord rec : fr.getCSVParser()) {
//			String item = rec.get("movie_id");
//			Double value = Double.parseDouble(rec.get("rating"));
//			String id = rec.get("rater_id");
//			for(PlainRater r : raterList) {
//				if(r.getID().equals(id)){
//					r.addRating(item, value);
//				}
//			}
//		}
		return raterList;
	}
	
	public void testLoadRaters() {
		//List<EfficientRater> raterList = loadRaters("ratings_short.csv");
		List<EfficientRater> raterList = loadRaters("ratings.csv");
		System.out.println("Size of the raterList : " + raterList.size());
		
		for(EfficientRater r : raterList) {
			System.out.println("Rater : " + r.getID() + "  " + "has given "+ r.numRatings() + " ratings");
		}
		
		int maxRating = 0;
		for(EfficientRater r : raterList) {
			if(maxRating < r.numRatings()) {
				maxRating = r.numRatings();
			}
		}
		System.out.println("max no. of ratings are : " + maxRating);
		
		System.out.println("Raters who have given maximum ratings are : ");
		for(EfficientRater r : raterList) {
			if(r.numRatings() == maxRating) {
				System.out.println("Rater ID: " + r.getID());
			}
		}
		
		int ratingCount = 0;
		String movieID = "1798709";
		for(EfficientRater r : raterList) {
			
			if(r.getRating(movieID) != -1) {
				ratingCount++;
			}
		}
		System.out.println("The movie " + movieID + " has got " + ratingCount + " ratings");
		
		Set<String> diffMovies = new HashSet<String>();
		for(EfficientRater r : raterList) {
			List<String> movies = r.getItemsRated();
			for(String movie : movies) {
				if(diffMovies.isEmpty() || !diffMovies.contains(movie)) {
					diffMovies.add(movie);
				}
			}
		}
		System.out.println("Total rated movies are: " + diffMovies.size() + "\n" + diffMovies);
		
	}

	public static void main(String[] args) {
		
		FirstRatings fr = new FirstRatings();
		//fr.testLoadMovies();
		fr.testLoadRaters();
	}

}
