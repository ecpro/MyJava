package com.course4.week1;
import java.util.*;

public class EarthQuakeClient {
	private ArrayList<QuakeEntry> quakeList;
    public EarthQuakeClient() {
        quakeList = createCSV();
    }
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry currEntry : quakeData) {
        	double mag = currEntry.getMagnitude();
        	if(mag > magMin) {
        		answer.add(currEntry);
        	}
        }

        return answer;
    }
    
    public void testFilterByMag() {
    	ArrayList<QuakeEntry> list = new ArrayList<QuakeEntry> ();
    	list = filterByMagnitude(quakeList, 5);
    	for(QuakeEntry curr : list) {
    		System.out.println(curr);
    	}
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
      
        for( QuakeEntry currentEntry : quakeData) {
        	Location loc = currentEntry.getLocation();
        	if(loc.distanceTo(from) < distMax) {
        		answer.add(currentEntry);
        	}
        }
        
        return answer;
    }
    
    public void testFilterByDistanceFrom() {
    	ArrayList<QuakeEntry> list = new ArrayList<QuakeEntry> ();
    	Location india = new Location(35.988, -78.907);
    	list = filterByDistanceFrom(quakeList, 3400, india);
    	for (QuakeEntry curr : list) {
    		System.out.println(curr);
    	}
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
//        com.course4.week2.part1.EarthQuakeParser parser = new com.course4.week2.part1.EarthQuakeParser();
//        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
//        String source = "data/nov20quakedata.atom";
//        ArrayList<com.course4.week2.part1.QuakeEntry> list  = parser.read(source);
//        System.out.println("read data for "+list.size()+" quakes");
    	
    	ArrayList<QuakeEntry> bigQuakeList = new ArrayList<QuakeEntry> ();
    	bigQuakeList = filterByMagnitude(quakeList, 5);
    	
    	for(QuakeEntry curr : bigQuakeList) {
    		System.out.println(curr);
    	}
    	

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom.txt";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
       // com.course4.week2.part1.Location city = new com.course4.week2.part1.Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> answer = filterByDistanceFrom(list, 1000000, city);
        
        dumpCSV(answer);
        System.out.println("Found " + answer.size() + " earthquake that match that criteria");
    }

    public ArrayList<QuakeEntry> createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        //dumpCSV(list);
       // System.out.println("# quakes read: " + list.size());
//        for (com.course4.week2.part1.QuakeEntry qe : list) {
//            System.out.println(qe);
//        }
//        
        return list;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
    	ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    	for(QuakeEntry currQuake : quakeData) {
    		double depth = currQuake.getDepth();
    		if(depth < maxDepth && depth > minDepth) {
    			answer.add(currQuake);
    		}
    	}
    	dumpCSV(answer);
    	return answer;
    }
    public void quakesOfDepth() {
    	EarthQuakeParser parser = new EarthQuakeParser();
    	String source = "data/nov20quakedatasmall.atom.txt";
    	ArrayList<QuakeEntry> list = parser.read(source);
    	ArrayList<QuakeEntry> answer = filterByDepth(list, -10000.0, -5000.0);
    	
    	System.out.println("Found " + answer.size() + " that match that criteria");
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
    	ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry> ();
    	for(QuakeEntry currQuake : quakeData) {
    		String info = currQuake.getInfo();
    		if(where.equals("start") && info.startsWith(phrase)) {
    			answer.add(currQuake);
    		}
    		if(where.equals("end") && info.endsWith(phrase)) {
    			answer.add(currQuake);
    		}
    		if(where.equals("any") && info.contains(phrase)) {
    			answer.add(currQuake);
    		}
    	}
    	return answer;
    }
    public void quakesByPhrase ( ) {
    	EarthQuakeParser parser = new EarthQuakeParser();
    	String source = "data/nov20quakedatasmall.atom.txt";
    	ArrayList<QuakeEntry> list = parser.read(source);
    	ArrayList<QuakeEntry> answer = filterByPhrase(list, "start", "Explosion");
    	dumpCSV(answer);
    	System.out.println("Found " + answer.size() + " that match California at end");
    }
    public static void main(String [] args) {
    	EarthQuakeClient client = new EarthQuakeClient();
    	//client.createCSV();
    	//client.testFilterByMag();
    	//client.bigQuakes();
    	//client.testFilterByDistanceFrom();
    	//client.closeToMe();
    	//client.quakesOfDepth();
    	client.quakesByPhrase();
    }
    
}
