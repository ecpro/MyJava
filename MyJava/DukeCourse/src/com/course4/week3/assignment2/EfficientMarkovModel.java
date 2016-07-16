package com.course4.week3.assignment2;
import edu.duke.FileResource;

import java.util.*;

/**
 * Created by PIYUSH on 06-03-2016.
 */
public class EfficientMarkovModel extends AbstractMarkovModel {

    private int order;
    private HashMap<String, ArrayList<String>> followerMap;

    public EfficientMarkovModel(int order) {
        super();
        this.order = order;
        followerMap = new HashMap<String, ArrayList<String>>();
    }

    private void buildMap(String key) {
       ArrayList<String> list = super.getFollows(key);
        followerMap.put(key,list);
    }

    public void buildMap() {
        System.out.println("Building HashMap....");
        String key = null;
        ArrayList<String> list = new ArrayList<String>();
        //System.out.println("the text length is : " + myText.length());
        //System.out.println("the order is " + order);
        for(int i = 0; i < myText.length() - order; i++) {
            key = myText.substring(i, i + order);
            //System.out.println("the key is : " + key);
            if(followerMap.isEmpty() || !followerMap.containsKey(key)) {
                list = super.getFollows(key);
                followerMap.put(key,list);
                //System.out.println(list);
            }
        }
        System.out.println("Build complete !! ");
    }

    @Override
    public String getRandomText(int numChars) {
        int index = myRandom.nextInt(myText.length() - order);
        String key = myText.substring(index, index + order);
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        for (int i = 0; i < numChars - order; i++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
//    public ArrayList<String> getFollows(String key){
//        return followerMap.get(key);
//    }

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> list = followerMap.get(key);
        if(list == null || list.isEmpty()) {
           list = super.getFollows(key);
            followerMap.put(key,list);
        }
        return followerMap.get(key);
    }
    public void printHashMapInfo() {
        //System.out.println(followerMap);
        //System.out.println(followerMap.keySet().size());
        int  maxSize = 0;
        for(Map.Entry<String, ArrayList<String>> entry : followerMap.entrySet()) {
            int currSize = entry.getValue().size();
            if(currSize > maxSize) {
                maxSize = currSize;
            }
        }
        System.out.println("max arraylist size " + maxSize);
//
//        for(Map.Entry<String, ArrayList<String>> entry : followerMap.entrySet()) {
//            if(entry.getValue().size() == maxSize) System.out.println(entry);
//        }
    }

    public String toString() {
        return "Efficient Markov " + order + " order" ;
    }

    public static void main(String [] args) {
        EfficientMarkovModel emm = new EfficientMarkovModel(5);
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        emm.setTraining(st);
        emm.setRandomSeed(531);
        //emm.setTraining("yes-this-is-a-thin-pretty-pink-thistle");
        emm.buildMap();
        emm.printHashMapInfo();
    }
}
