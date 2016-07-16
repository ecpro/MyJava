package com.course4.week1;
import java.util.ArrayList;


public class MatchAllFilter implements Filter {
	private ArrayList<Filter> filters;
	
	public MatchAllFilter() {
		filters = new ArrayList<Filter> ();
	}
	
	public void addFilter(Filter filter) {
		filters.add(filter);
	}
	public boolean satisfies(QuakeEntry qe) {
		
		for(Filter currFilter : filters) {
			if(! currFilter.satisfies(qe)) { return false ; }
		}
		return true;
	}

	public String getName() {
		StringBuilder allFilter = new StringBuilder("");
		for(Filter f : filters) {
			allFilter.append(f.getName() + " ");
		}
		return allFilter.toString();
	}

}
