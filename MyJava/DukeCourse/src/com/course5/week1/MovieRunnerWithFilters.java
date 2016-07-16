package com.course5.week1;
import java.util.*;

public class MovieRunnerWithFilters {

	
	public void getAverageRatingOneMovie() {
		ThirdRatings sr = new ThirdRatings();
		ArrayList<Rating> ratings = sr.getAverageRatings(1);
		String movieName = "Vacation";
		for(Rating r : ratings) {
			if(MovieDatabase.getTitle(r.getItem()).equals(movieName)){
				System.out.format("%f", r.getValue());
				return;
			}
		}
	}
	
	public void printAverageRatings() {
		ThirdRatings sr = new ThirdRatings("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		//MovieDatabase.initialize("ratedmovies_short.csv");
		System.out.println("Read data for " + MovieDatabase.size() + " movies");
		System.out.println("Read data for " + sr.getRaterSize() + " raters");
		
		ArrayList<Rating> ratings = sr.getAverageRatings(1);
		System.out.println("Found " + ratings.size()+ " movies");
		Collections.sort(ratings);
	
		for(Rating r : ratings) {
			System.out.format(" %2.2f %s \n", r.getValue(), MovieDatabase.getTitle(r.getItem()));
		}
		
	}
	
	public ArrayList<Rating> getAverageRatingByFilter(int minimalRaters, Filter filterCriteria) {
		
		ArrayList<String> selected = MovieDatabase.filterBy(filterCriteria);
		System.out.println("Total movies selected by the filter: " + selected.size());
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		
		ArrayList<Rating> ratings = tr.getAverageRatings(minimalRaters);
		ArrayList<Rating> selectedRatings = new ArrayList<Rating>();
		
		for(Rating rating : ratings) {
			String movieId = rating.getItem();
			if(selected.contains(movieId)) {
				selectedRatings.add(rating);
			}
		}
		
		return selectedRatings;
	}
	
	public void printAverageRatingsByYear() {
		MovieDatabase.initialize("ratedmoviesfull.csv");
		//MovieDatabase.initialize("ratedmovies_short.csv");
		ThirdRatings sr = new ThirdRatings("ratings.csv");
		System.out.println("Read data for " + MovieDatabase.size() + " movies");
		System.out.println("Read data for " + sr.getRaterSize() + " raters");
		
		ArrayList<Rating> ratings = getAverageRatingByFilter(20, new YearAfterFilter(2000));
		System.out.println("Found " + ratings.size()+ " movies");
		
		Collections.sort(ratings);
		
		for(Rating r : ratings) {
			System.out.format(" %2.2f %d %s \n", r.getValue(), MovieDatabase.getYear(r.getItem()), MovieDatabase.getTitle(r.getItem()));
		}
	}
	
	public void printAverageRatingsByGenre() {
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ThirdRatings sr = new ThirdRatings("ratings.csv");
		System.out.println("Read data for " + MovieDatabase.size() + " movies");
		System.out.println("Read data for " + sr.getRaterSize() + " raters");
		
		ArrayList<Rating> ratings = getAverageRatingByFilter(8, new GenreFilter("Comedy"));
		System.out.println("Found " + ratings.size()+ " movies");
		
		Collections.sort(ratings);
		
		for(Rating r : ratings) {
			System.out.format(" %2.2f %s \n", r.getValue(), MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
		}
	}
		
	public void printAverageRatingsByMinutes() {
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ThirdRatings sr = new ThirdRatings("ratings.csv");
		System.out.println("Read data for " + MovieDatabase.size() + " movies");
		System.out.println("Read data for " + sr.getRaterSize() + " raters");
		
		ArrayList<Rating> ratings = getAverageRatingByFilter(5, new MinutesFilter(105, 135));
		System.out.println("Found " + ratings.size()+ " movies");
		
		Collections.sort(ratings);
		
		for(Rating r : ratings) {
			System.out.format(" %2.2f  Time: %d %s \n", r.getValue(), MovieDatabase.getMinutes(r.getItem()), MovieDatabase.getTitle(r.getItem()));
		}
		
	}
	
	public void printAverageRatingByDirectors() {
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ThirdRatings sr = new ThirdRatings("ratings.csv");
		System.out.println("Read data for " + MovieDatabase.size() + " movies");
		System.out.println("Read data for " + sr.getRaterSize() + " raters");
		
		ArrayList<Rating> ratings = getAverageRatingByFilter(4, new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
		System.out.println("Found " + ratings.size()+ " movies");
		
		Collections.sort(ratings);
		
		for(Rating r : ratings) {
			System.out.format(" %2.2f %s \n", r.getValue(), MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
		}
		
	}
	
	public void printAverageRatingByYearAfterAndGenre() {
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ThirdRatings sr = new ThirdRatings("ratings.csv");
		System.out.println("Read data for " + MovieDatabase.size() + " movies");
		System.out.println("Read data for " + sr.getRaterSize() + " raters");
		 
		Filter allFilter = new AllFilters();
		((AllFilters) allFilter).addFilter(new YearAfterFilter(1990));
		((AllFilters) allFilter).addFilter(new GenreFilter("Drama"));
		
		ArrayList<Rating> ratings = getAverageRatingByFilter(8, allFilter);
		System.out.println("Found " + ratings.size()+ " movies");
		
		Collections.sort(ratings);
		
		for(Rating r : ratings) {
			System.out.format(" %2.2f %d %s \n", r.getValue(),MovieDatabase.getYear(r.getItem()), MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
		}
		
	}
	
	public void printAverageRatingsByDirectorsAndMinutes() {
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ThirdRatings sr = new ThirdRatings("ratings.csv");
		System.out.println("Read data for " + MovieDatabase.size() + " movies");
		System.out.println("Read data for " + sr.getRaterSize() + " raters");
		 
		Filter allFilter = new AllFilters();
		((AllFilters) allFilter).addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
		((AllFilters) allFilter).addFilter(new MinutesFilter(90, 180));
		
		ArrayList<Rating> ratings = getAverageRatingByFilter(3, allFilter);
		System.out.println("Found " + ratings.size()+ " movies");
		
		Collections.sort(ratings);
		
		for(Rating r : ratings) {
			System.out.format(" %2.2f Time: %d %s \n", r.getValue(),MovieDatabase.getMinutes(r.getItem()), MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
		}
	}
		
	
	public static void main(String[] args) {
		
		MovieRunnerWithFilters runner = new MovieRunnerWithFilters();
		//runner.printAverageRatings();
		//runner.getAverageRatingOneMovie();
		//runner.printAverageRatingsByYear();
		//runner.printAverageRatingsByGenre();
		//runner.printAverageRatingsByMinutes();
		//runner.printAverageRatingByDirectors();
		//runner.printAverageRatingByYearAfterAndGenre();
		runner.printAverageRatingsByDirectorsAndMinutes();
	}

}
