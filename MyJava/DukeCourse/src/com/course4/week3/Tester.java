package com.course4.week3;
import edu.duke.FileResource;

import java.util.*;
/**
 * Created by PIYUSH on 04-03-2016.
 */
public class Tester {

        public void testGetFollows() {
            MarkovOne mo = new MarkovOne();
            mo.setTraining("this is a test yes this is a test.");
            ArrayList<String> list = mo.getFollows("es");

            for(String curr : list) {
                System.out.println(curr);
            }
        }

        public void testGetFollowsWithFile() {
            FileResource fr = new FileResource();
            String text = fr.asString();
            MarkovOne mo = new MarkovOne();
            mo.setTraining(text);
            List<String> followList = mo.getFollows("he");

            System.out.println(followList.size());
        }

    public static void main(String [] args) {
        Tester t = new Tester();
        //t.testGetFollows();
        t.testGetFollowsWithFile();
    }
}
