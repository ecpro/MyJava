package com.course2and3.allweeks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i = i + totalSlices) {
        	slice.append(message.charAt(i));
        }
        return slice.toString();
    }
    public HashSet<String> readDictionary(FileResource fr) {
    	HashSet<String> dictionary = new HashSet<String>();
    	for(String word : fr.lines()) {
    		dictionary.add(word.toLowerCase());
    	}
    	return dictionary;
    }
    public int countWords(String message, HashSet<String> dictionary) {
    	String [] words = message.split("\\W");
    	int count = 0;
    	for(int i = 0; i<words.length; i++) {
    		if(dictionary.contains(words[i].toLowerCase())) {
    			count++;
    		}
    	}
    	return count;
    }
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
    	int keyLengthLimit = 100 ;
    	int maxMatches = 0;
    	int keyLength = 0;
    	for(int i = 1; i<keyLengthLimit; i++) {
    		
    		int [] currentKey = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
    		VigenereCipher vc = new VigenereCipher(currentKey);
    		String decrypted = vc.decrypt(encrypted);
    		int currentMatches = countWords(decrypted, dictionary); 
    		
    		if(maxMatches == 0 || maxMatches < currentMatches){
    			maxMatches = currentMatches;
    			keyLength = i;
    		}
    	}
    		
    	System.out.println("max matches :" + maxMatches);
    	int [] key = tryKeyLength(encrypted, keyLength, mostCommonCharIn(dictionary));

    	System.out.println("the key length is : "+ key.length);
    	VigenereCipher vc = new VigenereCipher(key);
    	return vc.decrypt(encrypted);
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        String slice = null;
        for(int i = 0; i < klength; i++) {
        	slice = sliceString(encrypted, i, klength);
        	CaesarCracker cc = new CaesarCracker();
        	key[i] = cc.getKey(slice);
        }
        return key;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
    	HashMap<Character, Integer> letterMap = new HashMap<Character, Integer>();
    	for(String word : dictionary) {
    		for(int i = 0; i<word.length(); i++) {
    			char tempChar = word.charAt(i);
    			if (!Character.isAlphabetic(tempChar)) continue;
    			if(letterMap.isEmpty() || !letterMap.containsKey(tempChar)) {
        			letterMap.put(tempChar, 1);
        		}
    			else {
    				int value = letterMap.get(tempChar);
    				letterMap.put(tempChar, value+1);
    			}
    		}
    		
    	}
    	int maxValue = 0;
    	char mostCommonLetter = '\u0000';
    	for(char letter : letterMap.keySet()) {
    		int currValue = letterMap.get(letter);
    		if ( maxValue == 0 || maxValue < currValue) {
    			maxValue = currValue;
    			mostCommonLetter = letter;
    		}
    	}
    	
    	return mostCommonLetter;
    	
    }
    public HashMap<String, HashSet<String>> getLanguage() {
    	DirectoryResource dr = new DirectoryResource();
    	HashMap<String, HashSet<String>> languageDicMap = new HashMap<String, HashSet<String>>();
    	//String [] language = {"English", "French", "Spanish", "Danish", "Dutch", "German", "Italian", "Portuguese"};
    	
    	for(File f : dr.selectedFiles()) {
    		String fileName = f.getName();
    		if(fileName.equals("Danish")) {
    			HashSet<String> lang = new HashSet<String>();
    			lang = readDictionary(new FileResource(f));
    			languageDicMap.put(fileName, lang);
    		}
    		else if(fileName.equals("Dutch")) {
    			HashSet<String> lang = new HashSet<String>();
    			lang = readDictionary(new FileResource(f));
    			languageDicMap.put(fileName, lang);
    		}
    		else if(fileName.equals("English")) {
    			HashSet<String> lang = new HashSet<String>();
    			lang = readDictionary(new FileResource(f));
    			languageDicMap.put(fileName, lang);
    		}
    		else if(fileName.equals("French")) {
    			HashSet<String> lang = new HashSet<String>();
    			lang = readDictionary(new FileResource(f));
    			languageDicMap.put(fileName, lang);
    		}
    		else if(fileName.equals("German")) {
    			HashSet<String> lang = new HashSet<String>();
    			lang = readDictionary(new FileResource(f));
    			languageDicMap.put(fileName, lang);
    		}
    		else if(fileName.equals("Italian")) {
    			HashSet<String> lang = new HashSet<String>();
    			lang = readDictionary(new FileResource(f));
    			languageDicMap.put(fileName, lang);
    		}
    		else if(fileName.equals("Portuguese")) {
    			HashSet<String> lang = new HashSet<String>();
    			lang = readDictionary(new FileResource(f));
    			languageDicMap.put(fileName, lang);
    		}
    		else {
    			HashSet<String> lang = new HashSet<String>();
    			lang = readDictionary(new FileResource(f));
    			languageDicMap.put(fileName, lang);
    		}
    	}
    	return languageDicMap;
    }
    public void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> langDic) {
    		
    	for ( String lang : langDic.keySet()) {
    		System.out.println("language " + lang );
    		breakForLanguage(encrypted, langDic.get(lang));
    		}
    }
    public void testBreakForAllLanguage() {
    	FileResource fr = new FileResource();
    	String encrypted = fr.asString();
    	breakForAllLanguages(encrypted, getLanguage());
    }
    public void testMostCommonCharIn() {
    	FileResource fr = new FileResource();
    	System.out.println(mostCommonCharIn(readDictionary(fr)));
    }
    public void testTryKeyLength() {
    	FileResource fr = new FileResource();
    	String message = fr.asString();
    	int [] key = tryKeyLength(message, 38, 'e');
//    	for(int i = 0; i<key.length; i++) {
//    		System.out.print(key[i] + " ");
//    	}
    }	
   public void tryVigenereWithfixed() {
	   FileResource fr = new FileResource();
	   String encrypted = fr.asString();
	   int [] key = tryKeyLength(encrypted, 38, 'e');
	   
	   VigenereCipher vc = new VigenereCipher(key);
	   String decrypted = vc.decrypt(encrypted);
	   
	   fr = new FileResource();
	   System.out.println(countWords(decrypted, readDictionary(fr)));
    	
    }
    public void breakVigenere () throws FileNotFoundException {
      FileResource fr = new FileResource();
      String encrypted = fr.asString();
      fr = new FileResource();
      //PrintWriter pw = new PrintWriter("output.txt");
      //pw.println(breakForLanguage(encrypted, readDictionary(fr)));
      System.out.println(breakForLanguage(encrypted, readDictionary(fr)));
    }
    public static void main(String args []) throws FileNotFoundException {
    	VigenereBreaker vb = new VigenereBreaker();
    	//System.out.println(vb.sliceString("abcdefghijklm", 4, 5));
    	//vb.testTryKeyLength();
    	vb.breakVigenere();
    	//FileResource fr = new FileResource();
    	//HashSet<String> words = vb.readDictionary(fr);
    	//fr = new FileResource();
    	//System.out.println(vb.countWords(fr.asString(), words));
    	//vb.tryVigenereWithfixed();
    	//vb.testMostCommonCharIn();
    	//vb.testBreakForAllLanguage();
    }
}
