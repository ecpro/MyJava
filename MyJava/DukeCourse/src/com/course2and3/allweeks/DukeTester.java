package com.course2and3.allweeks;

public class DukeTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkFinder lf = new LinkFinder();
		lf.findLink();
		
		TagFinder tf = new TagFinder();
		//tf.printAll("CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA");
		//System.out.println(tf.ctgCounter("ctgcgtctgctgccctgasgsasashdah ctg asdgasdgctgccc"));;
		
		//lf.findURL("asdhref=\"https://amazon.com/adsga/asga/?xag3\".asdfafasdhref=\"ftp://amazon.com/adsga/asga/?xag3\".asdfafasdhref=\"https://amazon.com/adsga/asga/?xag3\".asdfafasdhref=\"http://amazon.com/adsga/asga/?xag3\".asdfafasdhref=\"https://amazon.com/adsga/asga/?xag3\".asdfaf");
		//lf.testURLwithStorage();
//		tf.testStorageFinder();
//		System.out.println(tf.genes.size());
//		tf.printGenes(tf.genes);
//		int count = 0;
//		for(String dna : tf.genes.data()) {
//			count = count + tf.ctgCounter(dna);
//		}
//		System.out.println("CTG count : " + count);
//		System.out.println("longest gene " + tf.longestGene());
		lf.testURLwithStorage();
	}
	

}   