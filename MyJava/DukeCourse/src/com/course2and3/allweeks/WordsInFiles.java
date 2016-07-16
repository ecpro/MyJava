package com.course2and3.allweeks;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import edu.duke.DirectoryResource;
import edu.duke.FileResource;


public class WordsInFiles {
	private HashMap<String, ArrayList<String>> wordFileMap;
	public WordsInFiles() {
		wordFileMap = new HashMap<String, ArrayList<String>>();		
	}
	private void addWordsFromFile(File f){
		String filename = f.getName();
		FileResource fr = new FileResource(f);
		for(String word : fr.words()){
			if(wordFileMap.get(word) == null) {
				wordFileMap.put(word, new ArrayList<String>());
				wordFileMap.get(word).add(filename);
			}
			else {
				if(!wordFileMap.get(word).contains(filename)){
					wordFileMap.get(word).add(filename);
				}
				else
					continue;
			}
		}
	}
	public void buildWordFileMap() {
		DirectoryResource dr = new DirectoryResource();
		
		for(File f : dr.selectedFiles()) {
			addWordsFromFile(f);
		}
	}
	public int maxNumber() {
		Map.Entry<String, ArrayList<String>> maxEntry = null;
		for(Map.Entry<String, ArrayList<String>> entry : wordFileMap.entrySet()){
			if( maxEntry == null || entry.getValue().size() > maxEntry.getValue().size()) {
				maxEntry = entry;
			}
		}
		return maxEntry.getValue().size();
	}
	public ArrayList<String> wordsInNumFiles(int number) {
		ArrayList<String> wordList = new ArrayList<String>();
		
		for(String word : wordFileMap.keySet()){
			if(wordFileMap.get(word).size() == number) {
				wordList.add(word);
			}
		}
		return wordList;
	}
	public void printFilesIn(String word) {
		ArrayList<String> fileList = new ArrayList<String>();
		if(wordFileMap.containsKey(word)) {
			fileList = wordFileMap.get(word);
		}
		if(fileList != null) {
			String line = word + " appears in";
			for(int i = 0 ;i<fileList.size();i++) {
				if(i == fileList.size() -1) line = line + " and";
				line = line + " " + fileList.get(i) ;
			}
			System.out.println(line);
		}
		else {
			System.out.println("the word does not exist in the selected files");
		}
	}
	public void tester() {
		buildWordFileMap();
		System.out.println("one words appear in maximum of " + maxNumber() + " files");
		ArrayList<String> wordList = wordsInNumFiles(4);
		//System.out.println("\n\n");
		System.out.println("Number of words in 4 files " + wordList.size());
		for(int i = 0 ; i < wordList.size() ; i++) {
			System.out.println(wordList.get(i));
		}
		printFilesIn("tree");
		
	}
	public static void main(String [] args) {
		WordsInFiles wif = new WordsInFiles();
		wif.tester();
	}
}
