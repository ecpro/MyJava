package com.course4.week1;
import java.util.*;

public class LargestQuakes {
	
	public void findLargestQuakes() {

		EarthQuakeParser parser = new EarthQuakeParser(); 
		String source = "data/nov20quakedata.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		//int maxIndex = indexOfLargest(list);
	
		ArrayList<QuakeEntry> answer = getLargest(list, 50);
		for(QuakeEntry currEntry : answer) {
		System.out.println(currEntry);
		}
		System.out.println("total quakes : " + answer.size());
		//System.out.println("largest quake: " + list.get(maxIndex));
	}
	
	public int indexOfLargest(ArrayList<QuakeEntry> data) {
		int index = 0;
		for(int i = 0; i< data.size(); i++) {
			double maxMag = data.get(index).getMagnitude();
			double currMag = data.get(i).getMagnitude();
			if(currMag > maxMag) {
				index = i;
			}
		}
		return index;
	}
	
	public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
		
		ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
		ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry> ();
		
		for(int i = 0; i < howMany && !copy.isEmpty(); i++) {
			int maxIndex = 0;
			for(int k = 0; k < copy.size(); k++) {
				if(copy.get(maxIndex).getMagnitude() < copy.get(k).getMagnitude()) {
					maxIndex = k;
				}
			}
			answer.add(copy.get(maxIndex));
			copy.remove(maxIndex);
		}
		return answer;
	}
	
	public static void main (String args []) {
		LargestQuakes lq = new LargestQuakes();
		lq.findLargestQuakes();
	}
}
