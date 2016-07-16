package com.course4.week3.assignment3;

import edu.duke.FileResource;
import java.util.*;
/**
 * Created by PIYUSH on 08-03-2016.
 */
public class EfficientMarkovWord implements IMarkovModel {
    private String [] myText;
    private int myOrder;
    private Random myRandom;
    private HashMap<WordGram, ArrayList<String>> followerMap;
    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
        followerMap = new HashMap<WordGram, ArrayList<String>>();
        
    }
    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }


    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);
        WordGram wg = new WordGram(myText, index, myOrder);// random word to start with
        String key = wg.toString();
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(wg);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            wg = wg.shiftAdd(next);
        }

        return sb.toString().trim();
    }
    
    private void buildHashMap() {
    	for(int k = 0; k <= myText.length - myOrder; k++) {
    		
    		WordGram wg = new WordGram(myText, k, myOrder);
    		
    		boolean hasKey = followerMap.containsKey(wg);
            
            if(hasKey == false) {
            	ArrayList<String> follows = new ArrayList<String>();
            	int indexOfWordGram = 0;
            	for(int i = 0; i < myText.length - wg.length(); i = indexOfWordGram + 1) {
                    indexOfWordGram = indexOf(myText, wg, i);
                    if(indexOfWordGram >= myText.length-myOrder || indexOfWordGram == -1) {
                    	break;
                    }
                    //System.out.println(indexOfWordGram);
                    //System.out.println(indexOfWordGram + kGram.length());
                    //System.out.println("added " + myText[indexOfWordGram + kGram.length()]);
                    String follow = myText[indexOfWordGram + wg.length()];
                    follows.add(follow);
                    }
            	followerMap.put(wg, follows);
               }
    		
    			
    		}
    	
    }

    public int indexOf(String [] words, WordGram target, int start) {
        for(int i = start; i <= words.length - target.length(); i++) {
            WordGram wg = new WordGram(words, i, target.length());
            if(target.equals(wg)) return i;
        }
        return -1;
    }

    public void printHashMapInfo() {
        /*
        System.out.println(followerMap.size());
        System.out.println(followerMap);
        */
        //System.out.println(followerMap);
//        System.out.println("total followerMap size: " + followerMap.keySet().size());
        int  maxSize = 0;
        for(Map.Entry<WordGram, ArrayList<String>> entry : followerMap.entrySet()) {
            int currSize = entry.getValue().size();
            if(currSize > maxSize) {
                maxSize = currSize;
            }
        }
        System.out.println("max arraylist size " + maxSize);
//
//        for(Map.Entry<WordGram, ArrayList<String>> entry : followerMap.entrySet()) {
//            if(entry.getValue().size() == maxSize) System.out.println(entry);
//        }

        System.out.println("total keys: " + followerMap.size());
//        for(WordGram curr : followerMap.keySet()) {
//            System.out.println(curr + "  " + followerMap.get(curr));
//        }
    }

    public ArrayList<String> getFollows(WordGram kGram) {
    	if(followerMap.isEmpty()) buildHashMap();
        return followerMap.get(kGram);
    }
    
    public static void main(String [] args) {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //String st = "this is a test yes this is really a test is a test is very wow";
        EfficientMarkovWord word = new EfficientMarkovWord(2);
    	word.setTraining(st);
        word.setRandom(65);
    	word.buildHashMap();
        word.printHashMapInfo();
    	//System.out.println(word.followerMap.size());
    	//System.out.println(word.followerMap);
    }
}
