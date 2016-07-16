package com.course4.week3.assignment3;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Piyush Ravi
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){
            //markov.setRandom(844);
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord markovWord = new EfficientMarkovWord(5);
        markovWord.setRandom(844);
        runModel(markovWord, st, 60);
    }



    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    }

    public void testHashMap() {
        EfficientMarkovWord emw = new EfficientMarkovWord(2);
        String test = "this is a test yes this is really a test yes a test this is wow";
        emw.setTraining(test);
        emw.setRandom(42);
        System.out.println(emw.getRandomText(45));
        emw.printHashMapInfo();
    }

    public static void main(String [] args) {
        MarkovRunner mr = new MarkovRunner();
        mr.runMarkov();
        //mr.testHashMap();
    }
}
