package com.course4.week1;

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        
        for(int i = 0; i < howMany && !copy.isEmpty(); i++) {
        	int minIndex = 0;
        	for(int j = 0; !copy.isEmpty() && j < copy.size() ; j++) {
        		if(copy.get(j).getLocation().distanceTo(current) < copy.get(minIndex).getLocation().distanceTo(current)) {
        			minIndex = j;
        		}
        	}
        	ret.add(copy.get(minIndex));
        	copy.remove(minIndex);
        }
        
        
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom.txt";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,30);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
    public static void main(String args [] ) {
    	ClosestQuakes cq = new ClosestQuakes();
    	cq.findClosestQuakes();
    }
}
