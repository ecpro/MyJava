package com.recommender;

public class DirectorsFilter implements Filter {
	
	private String directors;
	
	public DirectorsFilter(String directors) {
		this.directors = directors.trim();
	}

	public boolean satisfies(String id) {
		
		return directors.contains(MovieDatabase.getDirector(id));
	}

}
