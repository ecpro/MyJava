package com.course2and3.allweeks;

import edu.duke.FileResource;
import edu.duke.StorageResource;

/**
* Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
* A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
* that is a multiple of 3 letters long.
*
* @author Duke Software Team 
 */
//import edu.duke.*;
//import java.io.*;

public class TagFinder {
      public StorageResource genes = new StorageResource();
       public void printAll(String dna) {
              dna = dna.toLowerCase();
              int start = 0;
              while(true) {
                     start = dna.indexOf("atg", start); 
                     if(start == -1 || start + 5 >= dna.length()) break;
                     
                     int end = findStopIndex(dna,start+3);
                     if(end >= dna.length()-3) start = start + 3;
                     else {
                           System.out.println(dna.substring(start, end + 3));
                           start = end +3;
                     }
              }
              
       }
       
       public int findStopIndex(String dna,int index) {
              int stop1 = dna.indexOf("tag", index);
              if(stop1 ==-1 || (stop1 - index) % 3 != 0) stop1=dna.length();
              
              int stop2 = dna.indexOf("tga", index);
              if(stop2 ==-1 || (stop2 - index) % 3 != 0) stop2=dna.length();
              
              int stop3 = dna.indexOf("taa", index);
              if(stop3 ==-1 || (stop3 - index) % 3 != 0) stop3=dna.length();
              
              return Math.min((Math.min(stop1, stop2)), stop3);
              
       }
       
       public StorageResource storeAll(String dna) {
           dna = dna.toLowerCase();
           int start = 0;
           StorageResource geneStore = new StorageResource();
           while(true) {
                  start = dna.indexOf("atg", start); 
                  if(start == -1 || start + 5 >= dna.length()) break;
                  
                  int end = findStopIndex(dna,start+3);
                  if(end >= dna.length()-3) start = start + 3;
                  else {
                        geneStore.add(dna.substring(start, end + 3));
                        start = end +3;
                  }
           }
           return geneStore;
           
    }
       
    public void testStorageFinder() {
      FileResource fr = new FileResource();
      String sequence = fr.asString();
      genes = storeAll(sequence);
      
//          for(String value : genes.data()) {
//                System.out.println(value);
//          }
     }
    
    public float cgRatio(String dna) {
      float cgCount = 0;
      for(int i = 0 ; i < dna.length(); i++) {
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'c' || dna.charAt(i) == 'g' || dna.charAt(i) == 'G') {
                  cgCount++;
            }
      }
      return cgCount / (float) dna.length();
    }
    
    public void printGenes(StorageResource sr) {
      int count60 = 0;
      int countCg = 0;
      for(String gene : sr.data()) {
            if(gene.length() > 60) {
                  count60++;
                  System.out.println(gene);
            }
            //System.out.println("gene : " + gene);
            //System.out.println("cgRatio " + gene );
            if(cgRatio(gene) > 0.35) {
                  countCg++;
                  System.out.println(gene + "  " + cgRatio(gene));
            }
            
      }
      
      System.out.println("the number of string greater than 60 characters : " + count60);
      System.out.println("the number of string with C-G ratio > 0.35 : " + countCg);
    }
    public int ctgCounter(String dna) {
    	dna = dna.toLowerCase();
    	int start = 0;
    	int count = 0;
    	int pos = 0;
    	while (true) {
    		if( start > dna.length() - 3) break;
    		pos = dna.indexOf("ctg", start);
    		System.out.println("pos = " + pos);
    		if(pos == -1) {
    			break;
    		}
    		start = pos + 3 ;
    		System.out.println("start = " + start);
    		count++;
    	}
    	return count;
    }
    
    public int longestGene() {
    	int max = 0 ;
    	for(String gene : genes.data()) {
    		if(gene.length() > max ) {
    			max = gene.length();
    		}
    	}
    	return max;
    }
       
}

