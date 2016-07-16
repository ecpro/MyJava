package com.course2and3.allweeks;
/**
 * Write a description of class com.course2and3.allweeks.LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     private  HashMap<String, Integer> freq;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry> ();
     }
        
     public void readFile() {
    	 
         FileResource fr = new FileResource();
         for(String line : fr.lines()) {
        	 records.add(WebLogParser.parseEntry(line));
        }
     }
     public int countUniqueIPs() {
    	 freq = new HashMap<String,Integer> ();
    	 for(LogEntry entry: records) {
    		 if(freq.isEmpty() || ! freq.containsKey(entry.getIpAddress())) {
    			 freq.put(entry.getIpAddress(), 1);
    		 }
    		 else {
    			 int val = freq.get(entry.getIpAddress());
    			 freq.put(entry.getIpAddress(), val+1);
    		 }
    	 }
    	 return freq.size();
     }
     public void printAllHigherThanNum(int num) {
    	 for(LogEntry entry : records) {
    		 if(entry.getStatusCode() > num) {
    			 System.out.println(entry);
    		 }
    	 }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
     	ArrayList<String> uniqueIPsOnaDay = new ArrayList<String>();
     	
     	for (LogEntry currentRow : records) {
     		String currentDate = currentRow.getAccessTime().toString();
     		String currentIp = currentRow.getIpAddress();
     		if (currentDate.contains(someday)) {
     			if(uniqueIPsOnaDay.isEmpty()) {
     				uniqueIPsOnaDay.add(currentIp);
     			}
     			if(!uniqueIPsOnaDay.contains(currentIp)) {
     				uniqueIPsOnaDay.add(currentRow.getIpAddress());
     			}
     		}
     	}
     	return uniqueIPsOnaDay;
     }
     public int countUniqueIPsInRange(int low, int high) {
    	 ArrayList<String> list = new ArrayList<String>();
    	 for(LogEntry currentRow : records) {
    		 if(currentRow.getStatusCode() >= low && currentRow.getStatusCode() <= high) {
    			if(list.isEmpty() || !list.contains(currentRow.getIpAddress())) {
    				list.add(currentRow.getIpAddress());
    			}
    		 }
    	 }
    	 return list.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP() {
    	 return freq;
     }
     public int mostNumberVisitsByIP(HashMap<String,Integer> weblog) {
    	 weblog = freq;
    	 int maxVisit = 0;
    	 
    	 for(String key : weblog.keySet()) {
    		 if(weblog.get(key) > maxVisit) {
    			 maxVisit = weblog.get(key);
    		 }
    	 }
    	 return maxVisit;
     }
     public ArrayList<String>  iPsMostVisits(HashMap<String,Integer> weblog) {
    	 ArrayList<String> freqUserList = new ArrayList<String> ();
    	 int maxVisit = mostNumberVisitsByIP(weblog);
    	 for(String key : weblog.keySet()) {
    		 if(weblog.get(key) == maxVisit) {
    			 freqUserList.add(key);
    		 }
    	 }
    	 return freqUserList;
     }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
    
     public HashMap<String,ArrayList<String>>  iPsForDays() {
    	 
    	 HashMap<String,ArrayList<String>> iPsPerDayLog = new HashMap<String,ArrayList<String>>();
    	 DateFormat df = new SimpleDateFormat("MMM dd");
    	 for (LogEntry currentLog : records) {
    		 String currentDay = df.format(currentLog.getAccessTime()).toString();
    		 if(iPsPerDayLog.isEmpty()) {
    			 iPsPerDayLog.put(currentDay, new ArrayList<String>());
    			 iPsPerDayLog.get(currentDay).add(currentLog.getIpAddress());
    		 }
    		 else if(!iPsPerDayLog.containsKey(currentDay)) {
    			 iPsPerDayLog.put(currentDay, new ArrayList<String>());
    			 iPsPerDayLog.get(currentDay).add(currentLog.getIpAddress());
    		 }
    		 else {
    			 iPsPerDayLog.get(currentDay).add(currentLog.getIpAddress());
    		 }
    	 }
    	 return iPsPerDayLog;
     }
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> weblog) {
    	 
    	 int max = 0; 
    	 String day = null;
    	 for (String current : weblog.keySet()) {
    		 if(day == null) {
    			 day = current;
    		 }
    		 if(weblog.get(current).size() > weblog.get(day).size()) {
    			 day = current;
    		 }
    	 }
    	 return day;
     }
     
     public ArrayList<String>  iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> weblog, String day) {
    	 ArrayList<String> mostIpOnADayList = new ArrayList<String>(); 
    	 for ( String key : weblog.keySet()) {
    		 if(key.equals(day)) {
    			 HashMap<String, Integer> IPOnADayMap = new HashMap<String, Integer>();
    			 for(String ip : weblog.get(key)) {
    				 if(IPOnADayMap.isEmpty() || !IPOnADayMap.containsKey(ip)) {
    					 IPOnADayMap.put(ip, 1);
    				 }
    				 if(IPOnADayMap.containsKey(ip)) {
    					 int value = IPOnADayMap.get(ip);
    					 IPOnADayMap.put(ip, value+1);
    				 }
    			 }
    			 int max = findMaxValue(IPOnADayMap);
    			for(String ip : IPOnADayMap.keySet()) {
    				if(IPOnADayMap.get(ip) == max) {
    					mostIpOnADayList.add(ip);
    				}
    			}
    		 }
    	 }
    	 return mostIpOnADayList;
     }
     private int findMaxValue(HashMap<String,Integer> ipFreq) {
    	 int max = 0; 
    	 for ( String current : ipFreq.keySet()) {
    		 if(ipFreq.get(current) > max) {
    			 max = ipFreq.get(current);
    		 }
    	 }
    	 return max;
     }
//     public static void main(String args []){
//    	 com.course2and3.allweeks.LogAnalyzer la = new com.course2and3.allweeks.LogAnalyzer();
//    	 la.readFile();
//    	 la.printAll();
//     }
}
