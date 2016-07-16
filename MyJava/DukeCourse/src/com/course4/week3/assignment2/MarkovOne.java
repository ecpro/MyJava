package com.course4.week3.assignment2;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by PIYUSH on 03-03-2016.
 */
public class MarkovOne extends AbstractMarkovModel {

    public MarkovOne() {
        super();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        int index = myRandom.nextInt(myText.length() -1);
        String key = myText.substring(index, index + 1);
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        for(int i = 0; i < numChars; i++) {
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }

        return sb.toString();
    }

    public void printList() {
        ArrayList<String> list = new ArrayList<String>(getFollows("this is a test "));
        System.out.println("total letters : " + list.size());
        for(String curr : list) {
            System.out.println(curr);
        }
    }

    public static void main(String [] args) {
        MarkovOne first = new MarkovOne();
        first.setTraining("this is a test yes this is a test.");
        first.printList();
    }



}
