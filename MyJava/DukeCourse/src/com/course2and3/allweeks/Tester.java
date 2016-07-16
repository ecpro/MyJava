package com.course2and3.allweeks;
/**
 * Write a description of class com.course2and3.allweeks.Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        System.out.println(la.countUniqueIPs());
       //la.printAll();
       //la.printAllHigherThanNum(400);
        //testUniqueIPVisitsOnDay(la.uniqueIPVisitsOnDay("Sep 24"));
       //System.out.println(la.countUniqueIPsInRange(400	, 499));
       //System.out.println("max no of website visit is " + la.mostNumberVisitsByIP(la.countVisitsPerIP()));
       //testCountVisitsPerIP(la.countVisitsPerIP()); 
       //testIPsMostVisit(la.iPsMostVisits(la.countVisitsPerIP()));
       //testiPsForDays(la.iPsForDays());
      // System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
        testiPsWithMostVisitsOnDay(la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 30"));
    }		
    public void testCountVisitsPerIP(HashMap<String, Integer> webvisitLog) {
    	for(String key : webvisitLog.keySet()) {
        	System.out.println("IP: " + key + " has " + webvisitLog.get(key) + " visits");
        }
    }
    
    public void testIPsForDays(HashMap<String,ArrayList<String>> webLogDaywise) {
 
    }
    public void testUniqueIPVisitsOnDay(ArrayList<String> ips) {
      System.out.println(ips.size());
      for(String currentRow : ips) {
      	System.out.println(currentRow);
      }
    }
    public void testIPsMostVisit(ArrayList<String> ipList) {
    	for(String ip : ipList) {
    		System.out.println(ip);
    	}
    }
    public void testiPsForDays(HashMap<String, ArrayList<String>> ipPerDay) {
    	for(String currentDay : ipPerDay.keySet()) {
    		System.out.println(currentDay + " has " + ipPerDay.get(currentDay).size() + " IPs");
    	}
    }
    public static void main(String [] args ) {
    	Tester test = new Tester();
    	test.testLogAnalyzer();
    }
    public void testiPsWithMostVisitsOnDay(ArrayList<String> ipList) {
    	for(String ip : ipList) {
    		System.out.println(ip);
    	}
    	
    }
}
