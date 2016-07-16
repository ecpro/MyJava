package com.course4.week3.assignment3;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by PIYUSH on 07-03-2016.
 */
public class MarkovWord implements IMarkovModel {
    private String [] myText;
    private int myOrder;
    private Random myRandom;

    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();

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

    public int indexOf(String [] words, WordGram target, int start) {
        for(int i = start; i < words.length - target.length(); i++) {
            WordGram wg = new WordGram(words, i, target.length());
            if(target.equals(wg)) return i;
        }
        return -1;
    }

    public ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int indexOfWordGram = 0;
        for(int i = 0; i < myText.length - kGram.length() -1; i++) {
            indexOfWordGram = indexOf(myText, kGram, i);
            if(indexOfWordGram == -1 ) break;
            follows.add(myText[indexOfWordGram + kGram.length()]);
        }
        return follows;
    }
}
