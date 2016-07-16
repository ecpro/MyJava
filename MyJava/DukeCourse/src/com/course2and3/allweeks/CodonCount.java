package com.course2and3.allweeks;

import java.util.HashMap;
import java.util.Map;

import edu.duke.FileResource;


public class CodonCount {
	private HashMap<String, Integer> codonMap;
	public CodonCount() {
		codonMap = new HashMap<String, Integer>();
	}
	public void buildCodonMap(int start , String dna) {
		codonMap.clear();
		dna = dna.toUpperCase();
		for(int i = start;i<=dna.length()-3;i+=3) {
			String codon = dna.substring(i, i+3);
			if(codonMap.containsKey(codon)) {
				int value = codonMap.get(codon);
				codonMap.put(codon, value+1);
			}
			else {
				codonMap.put(codon, 1);
			}
		}
	}
	public String getMostCommonCodon() {
		Map.Entry<String, Integer> maxEntry = null;
		for (Map.Entry<String, Integer> entry : codonMap.entrySet())
		{
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
		    {
		        maxEntry = entry;
		    }
		}
		return maxEntry.getKey();
	}
	public void printCodonCount(int start , int end) {
		for(String key : codonMap.keySet()) {
			if(codonMap.get(key) >= start && codonMap.get(key) <=end)
			{
				System.out.println(key + " " + codonMap.get(key));
			}
		}
	}
	public void tester() {
		FileResource resource = new FileResource();
		for(String dna : resource.words()) {
			for(int i = 0; i < 3 ; i++) {
				buildCodonMap(i, dna);
				System.out.println("Reading frame starting with " + i + "results in "+ codonMap.size() +" unique codons\n");
				System.out.println("\nand the most common codon is "+ getMostCommonCodon() + " with count " + codonMap.get(getMostCommonCodon()));
				System.out.println("\ncodon of count between 1 and 5 inclusive are: \n");
				printCodonCount(6 , 1000);
				System.out.println("\n");
			}
			
		}
	}
	public static void main(String[] args) {
		CodonCount cc = new CodonCount();
		//cc.buildCodonMap(0, "CGTTCAAGTTCAA");
		//System.out.println(cc.getMostCommonCodon());
		//cc.printCodonCount(2, 5);
		cc.tester();
	}

}
