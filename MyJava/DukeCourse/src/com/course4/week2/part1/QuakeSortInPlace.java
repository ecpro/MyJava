package com.course4.week2.part1;
/**
 * Write a description of class com.course4.week2.part1.QuakeSortInPlace here.
 * 
 * @author Piyush Ravi
 * @version 1.0
 */

import java.util.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakes , int from) {
        int maxIdx = from;
        for(int i = from+1; i < quakes.size(); i++) {
            if(quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth() ) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {

        for(int i = 0, passes = 0; i < in.size() && passes < 50; i++ ,passes++) {
            int maxIndex = getLargestDepth(in, i);
            QuakeEntry quakeWithMaxDepth = in.get(maxIndex);
            QuakeEntry qi = in.get(i);
            in.set(i,quakeWithMaxDepth);
            in.set(maxIndex, qi);

        }
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       dumpCSV(list);
        System.out.println("\n\n");
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataSampleSix2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s, %8.2f \n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo(),
                                qe.getDepth());
	    }
		
	}
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int  numSorted) {

        for(int i = 1; i < quakeData.size() - numSorted; i++) {
            if(quakeData.get(i).getMagnitude() < quakeData.get(i-1).getMagnitude()) {

                swap(quakeData, i, i-1);
            }
        }
//        if(numSorted !=0) System.out.println("Printing quakes after pass " + numSorted);
//        for(int i = 0; i < numSorted; i++) {
//            System.out.println(quakeData.get(i));
//        }

    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> quakeData) {

        for(int i = 0; i < quakeData.size(); i++) {
            onePassBubbleSort(quakeData, i);
            System.out.println("Printing quakes after pass " + i);
            for(int j = 0 ; j<quakeData.size(); j++) {
                System.out.println(quakeData.get(j));
            }
        }
    }

    private void swap(ArrayList<QuakeEntry> list, int i, int j) {
        QuakeEntry qi = list.get(i);
        QuakeEntry qj = list.get(j);
        list.set(i, qj);
        list.set(j, qi);
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for(int i = 1; i < quakes.size(); i++) {
            if(quakes.get(i).getMagnitude() < quakes.get(i-1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int numberOfPasses = 0;
        for(int i = 0; i < in.size(); i++) {
            if(checkInSortedOrder(in)) break;
            onePassBubbleSort(in, i);
            numberOfPasses++;
        }
        System.out.println("number of passes required " + numberOfPasses);
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int numOfPasses = 0;
        for (int i=0; i< in.size() && !checkInSortedOrder(in); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            numOfPasses++;
        }
        System.out.println("Sorted in " + numOfPasses + " passes");
    }

    public void testSort1() {
            EarthQuakeParser parser = new EarthQuakeParser();
            //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
            String source = "data/earthQuakeDataWeekDec6sample2.atom";
            //String source = "data/nov20quakedata.atom";
            ArrayList<QuakeEntry> list  = parser.read(source);

            sortByMagnitudeWithBubbleSortWithCheck(list);
    }

    public void testSort2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

        System.out.println("read data for "+list.size()+" quakes");
        //sortByMagnitude(list);
        sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);

       // sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }
    public static void main(String [] args) {
        QuakeSortInPlace qsip = new QuakeSortInPlace();
        qsip.testSort1();
    }
}
