package com.course2and3.allweeks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import edu.duke.FileResource;
import edu.duke.URLResource;


public class GladLibMap {
	private HashMap<String ,ArrayList<String>> myMap;
	private Random myRandom;
	private ArrayList<String> usedWords;
	private ArrayList<String> usedCategories;
	//private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLibMap(){
		myMap = new HashMap<String ,ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLibMap(String source){
		myMap = new HashMap<String ,ArrayList<String>>();
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		String [] wordCategory = {"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
		for(int i = 0; i < wordCategory.length; i++){
			myMap.put(wordCategory[i], readIt(source + "/" + wordCategory[i]+ ".txt"));
		}
		usedWords = new ArrayList<String>();
		usedCategories = new ArrayList<String>();
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("country")) {
			return randomFrom(myMap.get("country"));
		}
		if (label.equals("color")){
			return randomFrom(myMap.get("color"));
		}
		if (label.equals("noun")){
			return randomFrom(myMap.get("noun"));
		}
		if (label.equals("name")){
			return randomFrom(myMap.get("name"));
		}
		if (label.equals("adjective")){
			return randomFrom(myMap.get("adjective"));
		}
		if (label.equals("animal")){
			return randomFrom(myMap.get("animal"));
		}
		if (label.equals("timeframe")){
			return randomFrom(myMap.get("timeframe"));
		}
		if(label.equals("verb")) {
			return randomFrom(myMap.get("verb"));
		}
		if(label.equals("fruit")){
			return randomFrom(myMap.get("fruit"));
		}
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		return "**UNKNOWN**";
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String category = w.substring(first+1,last);
		String sub = getSubstitute(category);
		if(!usedCategories.contains(category) && !category.equals("number")){
			System.out.println("used categories : " + category);
			usedCategories.add(category);
		}
		while(!usedWords.isEmpty() && usedWords.contains(sub)){
			sub = getSubstitute(category);
		}
		usedWords.add(sub);
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
		System.out.println("number of words: " + usedWords.size());
		System.out.println("\ntotal number of words in the file : " + totalWordsInMap());
		System.out.println("\ntotal number of words in the categories used :" + totalWordConsidered());
		
	}
	public int totalWordsInMap() {
		int wordCount = 0;
		for(String word : myMap.keySet()) {
			wordCount = wordCount + myMap.get(word).size();
		}
		
		return wordCount;
	}
	
	public void printMap() {
		int count = 0;
		for(String word : myMap.keySet()) {
			count = count + myMap.get(word).size() ;
			System.out.println(word + " has count " + myMap.get(word).size());
		}
		System.out.println("total word count in hashmap " + count);
	}
	
	public static void main(String [] args) {
		GladLibMap glm = new GladLibMap();
		glm.makeStory();
		glm.printMap();
	}
	public int totalWordConsidered() {
		int wordCount = 0;
		System.out.println("total myMap ArrayList count " + myMap.size());
		System.out.println("total size of usedCategories " + usedCategories.size());
		for(int i = 0;i<usedCategories.size();i++){
			String word = usedCategories.get(i);
			if(myMap.get(word) != null){
				int size = myMap.get(word).size() ;
				wordCount = wordCount + size;
			}
			
		}
		return wordCount;
	}
}
