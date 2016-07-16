package com.course4.week3.assignment2;
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandomSeed(int seed) {
        myRandom = new Random(seed);
    }

    abstract public String getRandomText(int numChars);

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> keyFollowerList = new ArrayList<String> ();
        for(int i = 0; i < myText.length() - key.length() ; i++) {
            if(myText.substring(i, i + key.length()).equals(key)) {
                keyFollowerList.add(myText.substring(i+key.length(),i+key.length()+1));
            }
        }
        return keyFollowerList;
    }

}
