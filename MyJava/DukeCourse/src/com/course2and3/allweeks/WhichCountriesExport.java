package com.course2and3.allweeks; /**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;

import org.apache.commons.csv.*;
public class WhichCountriesExport {
	public void listExporters(CSVParser parser, String exportOfInterest) {
		//for each row in the CSV File
		for(CSVRecord record : parser) {
			String export = record.get("Exports");
			if ( export.equals(exportOfInterest)) {
				System.out.println(record.get("Country"));
			}
		}
			//Look at the "Exports" column

			//Check if it contains exportOfInterest

				//If so, write down the "Country" from that row

	}

	public void tester() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		//listExporters(parser, "coffee");
		//System.out.println(countryInfo(parser, "Nauru"));
		//listExportersTwoProducts(parser, "cotton", "flowers");
		//System.out.println(numberOFExporter(parser, "cocoa"));
		bigExporters(parser, "$999,999,999,999");
	}
	public String countryInfo(CSVParser parser, String country) {
		for(CSVRecord record : parser) {
			if(record.get("Country").equals(country)){
				return country + ":" + " " + record.get("Exports") + " : " + record.get("Value (dollars)"); 
			}
		}	
		return "NOT FOUND";
	}
	public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
		for(CSVRecord record : parser) {
			String exports = record.get("Exports"); 
			if(exports.contains(exportItem1) && exports.contains(exportItem2)) {
				System.out.println(record.get("Country"));
				
			}
		}
	}
	public int numberOFExporter(CSVParser parser, String exportItem) {
		int count = 0;
		for(CSVRecord record : parser) {
			String export = record.get("Exports"); 
			if(export.contains(exportItem)) {
				count++;
			}
		}
		return count;
	}
	public void bigExporters(CSVParser parser , String amount) {
		long amountInInt = extractInt(amount);
		
		for(CSVRecord record : parser) {
			long value = extractInt(record.get("Value (dollars)"));
			if(value > amountInInt) {
				System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
			}
		}
		
	}
	public long extractInt(String data) {
		data = data.substring(1, data.length());
		String extracted ="";
		for(String ret : data.split(",")) {
			extracted = extracted + ret;
		}
		//System.out.println(extracted);
		return Long.parseLong(extracted);
	}
	public static void main(String args []) {
		WhichCountriesExport data = new WhichCountriesExport();
		data.tester();
	}
}
