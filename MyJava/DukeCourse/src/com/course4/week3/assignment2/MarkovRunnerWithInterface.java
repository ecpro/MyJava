package com.course4.week3.assignment2;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */
import com.course4.week3.MarkovRunner;
import edu.duke.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
		markov.setRandomSeed(615);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 1000;

		MarkovZero mz = new MarkovZero();
        runModel(mz, st, size);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size);

    }

	public void runEfficientMarKov() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 1000;

		EfficientMarkovModel emm = new EfficientMarkovModel(5);
		runModel(emm, st, st.length());

        emm.printHashMapInfo();

	}

	public void compareMethods() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;

		Long start = System.nanoTime();
		MarkovModel mm = new MarkovModel(2);
		runModel(mm, st, size);
		Long stop = System.nanoTime();

		System.out.println("Total time elapsed : " + ((stop - start) / 1000000000.0));

		start = System.nanoTime();
		EfficientMarkovModel emm = new EfficientMarkovModel(2);
		runModel(emm, st, size);
		stop = System.nanoTime();

		System.out.println("Total time elapsed : " + ((stop - start) / 1000000000.0));
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

    public static void main(String [] args) {
        MarkovRunnerWithInterface mr = new MarkovRunnerWithInterface();
        //mr.runMarkov();
		//mr.compareMethods();
        mr.runEfficientMarKov();
    }
	
}
