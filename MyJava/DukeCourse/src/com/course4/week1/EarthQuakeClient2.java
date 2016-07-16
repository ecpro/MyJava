package com.course4.week1;
import java.util.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            if (f.satisfies(qe)) {
                answer.add(qe);
            }
        }
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom.txt";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MinMagFilter(4.0); 
        Filter magFilter = new MagnitudeFilter(4.0, 5.0);
        Filter depthFilter = new DepthFilter(-35000.0, -12000.0);
        
        for(QuakeEntry currEntry : list) {
        	if(magFilter.satisfies(currEntry) && depthFilter.satisfies(currEntry)) {
        		System.out.println(currEntry);
        	}
        }
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    public void testMatchFilter() {
    	EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter filter = new MatchAllFilter();
        filter.addFilter(new MagnitudeFilter(1.0, 4.0));
        filter.addFilter(new DepthFilter(-180000.0, -30000.0));
        filter.addFilter(new PhraseFilter("any", "o"));
       int count = 0;
        for(QuakeEntry curr : list) {
        	if(filter.satisfies(curr)) { count++; System.out.println(curr); }
        }
        System.out.println("Total count " + count);
        System.out.println("Filters used are : " + filter.getName());
        
    }
    
    public void testMatchFilter2() {
    	EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter filter = new MatchAllFilter();
        filter.addFilter(new MagnitudeFilter(0.0, 5.0));
        filter.addFilter(new DistanceFilter(new Location(55.7308, 9.1153), 3000000));
        filter.addFilter(new PhraseFilter("any", "e"));
        //filter.addFilter(new DepthFilter(-10000.0, -8000.0));
        int count = 0;
        for(QuakeEntry curr : list) {
        	if(filter.satisfies(curr)) { count++ ; System.out.println(curr); }
        }
        System.out.println("total count " + count);
        System.out.println("Filters used are : " + filter.getName());
    }
    public static void main(String args []) {
    	EarthQuakeClient2 client2 = new EarthQuakeClient2();
    	//client2.quakesWithFilter();
    	client2.testMatchFilter2();
    }

}
