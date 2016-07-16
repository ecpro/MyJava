package com.course4.week3;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by PIYUSH on 04-03-2016.
 */
public class MarkovFour {
    private String myText;
    private Random myRandom;

    public MarkovFour() {
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        int index = myRandom.nextInt(myText.length() -4);
        String key = myText.substring(index, index + 4);
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        for(int i = 0; i < numChars - 4; i++) {
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> keyFollowerList = new ArrayList<String> ();
        for(int i = 0; i < myText.length() - key.length() ; i++) {
            if(myText.substring(i,i + key.length()).equals(key)) {
                keyFollowerList.add(myText.substring(i+key.length(),i+key.length()+1));
            }
        }
        return keyFollowerList;
    }

    public void printList() {
        ArrayList<String> list = new ArrayList<String>(getFollows("this is a test "));
        System.out.println("total letters : " + list.size());
        for(String curr : list) {
            System.out.println(curr);
        }
    }

    public static void main(String [] args) {
        MarkovFour first = new MarkovFour();
        first.setTraining("this is a test yes this is a test.");
        //first.printList();

    }

}
