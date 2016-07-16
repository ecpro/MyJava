package com.course2and3.allweeks; /**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
	public CSVRecord hottestHourInFile(CSVParser parser) {
		//start with largestSoFar as nothing
		CSVRecord largestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			//If largestSoFar is nothing
			if (largestSoFar == null) {
				largestSoFar = currentRow;
			}
			//Otherwise
			else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
				//Check if currentRow’s temperature > largestSoFar’s
				if (currentTemp > largestTemp) {
					//If so update largestSoFar to currentRow
					largestSoFar = currentRow;
				}
			}
		}		
		//The largestSoFar is the answer@
		return largestSoFar;
		
	}
	public CSVRecord coldestHourInFile(CSVParser parser) {
		CSVRecord coldestSoFar = null;
		
		for(CSVRecord currentRow : parser) {
			if(currentRow.get("TemperatureF").contains("-9999")) continue;
			if(coldestSoFar == null) {
				coldestSoFar = currentRow;
			}
			else {
				coldestSoFar = smallestOfTwo(coldestSoFar, currentRow);
			}
		}
		return coldestSoFar;
	}
	
	public CSVRecord smallestOfTwo(CSVRecord first, CSVRecord second) {
		
		double firstRec = Double.parseDouble(first.get("TemperatureF"));
		double secRec = Double.parseDouble(second.get("TemperatureF"));
		if(firstRec <= secRec) {
			return first;
		}
		else return second;
	}
	public File smallestOfTwo(File file1, File file2) {
		FileResource fr = new FileResource(file1);
		
		CSVParser parser = fr.getCSVParser();
		CSVRecord rec1 = coldestHourInFile(parser);
		
		fr = new FileResource(file2);
		
		parser = fr.getCSVParser();
		CSVRecord rec2 = coldestHourInFile(parser);
		
		if(smallestOfTwo(rec1, rec2) == rec1) {
			return file1;
		}
		else
		{
			return file2;
		}
	}
	
	public CSVRecord getLeastHumid(CSVRecord first , CSVRecord second) {
		
		double firstRec = Double.parseDouble(first.get("Humidity"));
		double secRec = Double.parseDouble(second.get("Humidity"));
		
		if(firstRec <= secRec) {
			return first;
		}
		else return second;
		
	}
	
	public void testHottestInDay () {
		FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
		CSVRecord largest = hottestHourInFile(fr.getCSVParser());
		System.out.println("hottest temperature was " + largest.get("TemperatureF") +
				   " at " + largest.get("TimeEST"));
	}
	
	public void testColdestHourInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord rec = coldestHourInFile(parser);
		
		System.out.println("coldest temperature was " + rec.get("TemperatureF") +
				   " at " + rec.get("DateUTC"));
		
	}
	
	public CSVRecord lowestHumidityInManyFiles() {
		DirectoryResource dr = new DirectoryResource();
		CSVRecord lowest = null;
		for ( File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser();
			CSVRecord current = lowestHumidityInFile(parser);
			if(lowest == null ) {
				lowest = current;
			}
			else {
				lowest = getLeastHumid(lowest, current);
			}
		}
		return lowest;
	}
	public CSVRecord lowestTempInManyFiles() {
		DirectoryResource dr = new DirectoryResource();
		CSVRecord lowest = null;
		for ( File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser();
			CSVRecord current = coldestHourInFile(parser);
			if(lowest == null ) {
				lowest = current;
			}
			else {
				lowest = smallestOfTwo(lowest, current);
			}
		}
		return lowest;
	}
	
	public File fileWithColdestTemp() {
		DirectoryResource dr = new DirectoryResource();
		File fileWithColdestTemp = null;
		for (File f : dr.selectedFiles()) {
			if(fileWithColdestTemp == null) {
				fileWithColdestTemp = f;
			}
			else {
				fileWithColdestTemp = smallestOfTwo(fileWithColdestTemp, f);
			}
		}
		return fileWithColdestTemp;
	}
	
	public double averageTemperatureWithHighHumidityInFile(CSVParser parser , int value) {
		double temp = 0;
		double count = 0;
		for ( CSVRecord currentRow : parser) {
			int humidity = Integer.parseInt(currentRow.get("Humidity"));
			double currentemp = Double.parseDouble(currentRow.get("TemperatureF"));
			if (humidity >= value) {
				count++;
				if(currentemp == -9999) continue;
				temp = temp + currentemp;
			}
		}
		if (count == 0) return 0;
		else return temp/count;
	}

	public void testAverageTemperatureWithHighHumidityInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double temp = averageTemperatureWithHighHumidityInFile(parser, 80);
		if (temp == 0) {
			System.out.println("No temperatures with that humidity");
		}
		else {
			System.out.println("Average temperature with high humidity is " + temp);
		}
	}
	public CSVRecord lowestHumidityInFile(CSVParser parser) {
		CSVRecord lowestH = null;
		for (CSVRecord currentRow : parser) {
			if(currentRow.get("Humidity").equals("N/A")) continue;
			
			if(lowestH == null ) {
				lowestH = currentRow;
			} else {
				lowestH = getLeastHumid(lowestH, currentRow);
			}
		}
		
		return lowestH;
	}

	public void testFileColdestTemp() {
		File f = fileWithColdestTemp();
		System.out.println(f.getName());
		
		FileResource fr = new FileResource(f.getAbsolutePath());
		CSVParser parser = fr.getCSVParser();
		CSVRecord rec = coldestHourInFile(parser);
		System.out.println("coldest temperature was " + rec.get("TemperatureF") +
				   " at " + rec.get("TimeEST"));
	}
	public void testLowestHumidityInManyFiles() {
		CSVRecord rec = lowestHumidityInManyFiles();
		System.out.println("lowest Humidity in many files " + rec.get("Humidity") +
				   " at " + rec.get("DateUTC"));
	}
	public void testLowestTempInManyFiles() {
		CSVRecord rec = lowestHumidityInManyFiles();
		System.out.println("lowest Temp in many files " + rec.get("TemperatureF") +
				   " at " + rec.get("DateUTC"));
	}
	public void testLowestHumidityInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord rec = lowestHumidityInFile(parser);
		System.out.println("lowest Humidity was " + rec.get("Humidity") +
				   " at " + rec.get("DateUTC"));
		
	}
	public double averageTemperatureInFile(CSVParser parser) {
		double average = 0;
		double count = 0;
		for (CSVRecord currentRow : parser) {
			count++;
			if(currentRow.get("TemperatureF").contains("-9999")) continue;
			double temp = Double.parseDouble(currentRow.get("TemperatureF"));
			average = average + temp;
		}
		
		return average / count;
	}
	
	public void testAverageTemp() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		Double temp = averageTemperatureInFile(parser);
		System.out.println("Average temperature in file is " + temp);
	}
	
	public static void main(String args []) {
		CSVMax weatherData = new CSVMax();
		//weatherData.testColdestHourInFile();
		weatherData.testColdestHourInFile();
		//weatherData.testFileColdestTemp();
		//weatherData.testLowestHumidityInFile();
		//weatherData.testLowestHumidityInManyFiles();
		//weatherData.testAverageTemp();
		//weatherData.testAverageTemperatureWithHighHumidityInFile();
		//weatherData.testColdestHourInFile();
		//weatherData.testLowestTempInManyFiles();
		//weatherData.testLowestHumidityInFile();
		
	
	}
	
}
