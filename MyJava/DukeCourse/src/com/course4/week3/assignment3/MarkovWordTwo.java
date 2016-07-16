package com.course4.week3.assignment3;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by PIYUSH on 07-03-2016.
 */
public class MarkovWordTwo implements IMarkovModel{
    private String [] myText;
    private Random myRandom;

    public MarkovWordTwo() {
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
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        for(int i = 0; i < myText.length -1; i = pos + 1) {
            pos = indexOf(myText, key, i);
            if(pos == -1 || pos == myText.length - 1) break;
            follows.add(myText[pos+1]);
        }
        return follows;
    }

    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        for(int i = 0; i < myText.length - 2; i = pos + 2) {
            pos = indexOf(myText, key1, key2, i);
            if(pos == -1 || pos == myText.length - 2) break;
            follows.add(myText[pos+2]);
        }
        return follows;
    }

    public void testGetFollows() {
        setTraining("this is just a test yes this is a simple test");
        ArrayList<String> list = getFollows("this", "");
        System.out.println(list);
    }
    private int indexOf(String [] words, String target1, String target2, int start) {
        for(int i = start; i < words.length - 1; i++) {
            if(words[i].equals(target1) && words[i+1].equals(target2)) return i;
        }
        return -1;
    }

    private int indexOf(String [] words, String target, int start) {
        for(int i = start; i < words.length; i++) {
            if(words[i].equals(target)) return i;
        }
        return -1;
    }

    public void testIndexOf() {
        String [] words = "this is just a test yes this is a simple test".split("\\s+");
        System.out.println(indexOf(words, "this", "is",  0));
    }



    public static void main(String args []) {
        MarkovWordTwo markov = new MarkovWordTwo();
        markov.testIndexOf();
        markov.testGetFollows();
    }

}
