package com.course2and3.allweeks; /**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;

import java.io.File;

import org.apache.commons.csv.*;

public class BabyBirths {
	public String dataSourceDirectory = "us_babynames/us_babynames_by_year/yob";
	public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}
	private String getFileOfYear(int year) {
		return dataSourceDirectory + Integer.toString(year) + ".csv";
	}
	
   public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int uniqueGirlCount = 0;
		int uniqueBoyCount = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				uniqueBoyCount++;
			}
			else {
				uniqueGirlCount++;
				totalGirls += numBorn;
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("female girls = " + totalGirls);
		System.out.println("male boys = " + totalBoys);
		System.out.println("Unique boys count: " + uniqueBoyCount);
		System.out.println("Unique girl count: " + uniqueGirlCount);
	}
   public int getRank (int year, String name, String gender) {
	   	FileResource fr = new FileResource(getFileOfYear(year));
	   	CSVParser parser = fr.getCSVParser(false);
	   	int rank = 0;
	   	for(CSVRecord currentRow : parser) {
	   		if(currentRow.get(1).equals(gender)) {
	   			rank++;
	   			if(currentRow.get(0).equals(name)) {
	   				return rank;
	   			}
	   		}
	   	}
	   	return -1;
   }
   public int getRank (String filePath, String name, String gender) {
	   	FileResource fr = new FileResource(filePath);
	   	CSVParser parser = fr.getCSVParser(false);
	   	int rank = 0;
	   	for(CSVRecord currentRow : parser) {
	   		if(currentRow.get(1).equals(gender)) {
	   			rank++;
	   			if(currentRow.get(0).equals(name)) {
	   				return rank;
	   			}
	   		}
	   	}
	   	return -1;
  }
   
   public String getName(int year, int rank, String gender) {
	   FileResource fr = new FileResource(getFileOfYear(year));
	   CSVParser parser = fr.getCSVParser(false);
	   int count = 0;
	   for(CSVRecord currentRow : parser) {
		   if(currentRow.get(1).equals(gender)) {
			   count++;
			   if(count == rank) {
				   return currentRow.get(0);
			   }
		   }
	   }
	   return "NO NAME";
   }
   public void whatIsNameInYear(String name, int year, int newYear, String gender) {
		
		int rankInYearOne = getRank(year, name, gender);
		if(rankInYearOne == -1) {
			System.out.print("Name does not exist");
		}
		else {
			String correspName = getName(newYear, rankInYearOne, gender);
			if(correspName.equals("NO NAME")) {
				System.out.println("no such name in " + newYear);
			}
			else {
				System.out.println(name + " born in " + year + " would be " + correspName + " if she was born in " + newYear);
			}
		}
		
	}
   
	public String yearOfHighestRank (String name, String gender) {
		DirectoryResource dr = new DirectoryResource();
		int highestRank = 0;
		String filename = null;
		for(File f : dr.selectedFiles()) {
			String filePath = f.getAbsolutePath();	
			int currentRank = getRank(filePath, name, gender);
			
			if(highestRank == 0 || highestRank > currentRank) {
				highestRank = currentRank;
				filename = f.getName();
			}
		}
		return filename;
	}
	public double getAverageRank(String name, String gender) {
		DirectoryResource dr = new DirectoryResource();
		double totalRank = 0;
		int count = 0;
		for(File f : dr.selectedFiles()) {
			String filePath = f.getAbsolutePath();
			int currentRank = getRank(filePath, name, gender);
			if(currentRank == -1) return -1.0;
			totalRank = totalRank+currentRank;
			count++;
		}
		return totalRank / count;
	}
	
	public void testTotalBirths () {
		FileResource fr = new FileResource();
		//FileResource fr = new FileResource("data/yob2014.csv");
		totalBirths(fr);
	}
	public int getTotalBirthsRankedHigher(int year, String name, String gender) {
		FileResource fr = new FileResource(getFileOfYear(year));
		int limitRank = getRank(year, name, gender) - 1;
		//System.out.println("ethan's rank " + limitRank);
		if(limitRank == -1) return -1;
		int total = 0;
		while(limitRank > 0) {
			String currName = getName(year, limitRank, gender);
			total = total + getNameCount(fr, currName);
			limitRank--;
		}
		return total;
	}
	public int getNameCount(FileResource fr, String name) {
		CSVParser parser = fr.getCSVParser(false);
		for( CSVRecord currentRow : parser) {
			String currName = currentRow.get(0);
			if(name.equals(currName)) {
				return Integer.parseInt(currentRow.get(2));
			}
		}
		return -1;
	}
	public void testGetAverageRank() {
		System.out.println(getAverageRank("Robert", "M"));
	}
	public void testTotalBirthsRankedHigher() {
		System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
	}
	public void testYearOfHighestRank() {
		System.out.println(yearOfHighestRank("Mich", "M"));
	}
	public void testwhatIsNameInYear() {
		whatIsNameInYear("Owen", 1974, 2014, "M");
	}
	public void testGetName() {
		   System.out.println(getName(1982, 450 , "M"));
	   }
	public void testGetRank() {
		System.out.println(getRank(1971, "Frank", "M"));
	}
	public static void main(String [] args) {
		BabyBirths bb = new BabyBirths();
		//bb.testGetRank();
		//bb.testGetName();
		//System.out.println(bb.getFileOfYear(2012));
		//bb.testwhatIsNameInYear();
		//bb.testYearOfHighestRank();
		//bb.testGetAverageRank();
		bb.testTotalBirthsRankedHigher();
		//bb.testTotalBirths();
	}
}
