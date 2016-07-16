package com.course2and3.allweeks;

import java.util.ArrayList;

import edu.duke.*;

public class CharactersInPlay {
	private ArrayList<String> characterName;
	private ArrayList<Integer> freq;
	
	public CharactersInPlay() {
		characterName = new ArrayList<String>();
		freq = new ArrayList<Integer>();
	}
	
	// method to update person and its count
	public void update(String person) {
		int index = characterName.indexOf(person);
		if(index == -1) {
			characterName.add(person);
			freq.add(1);
		} else
		{
			freq.set(index, freq.get(index)+1);
		}
	}	
	
	// method to read files for Characters and calling update()
	public void findAllCharacters() {
		characterName.clear();
		freq.clear();
		FileResource fr = new FileResource();
		for(String line : fr.lines()) {
			//line = line.toLowerCase();	
			int endIndex = line.indexOf('.');
			if(endIndex != -1 && endIndex != 0) {
				//System.out.println("word : " + line.substring(0,endIndex));
				update(line.substring(0,endIndex));
			}
			
			
		}
	}
	public void tester() {
		findAllCharacters();
		for(int i = 0;i<characterName.size();i++) {
			System.out.println("Character: " + characterName.get(i) + " " + freq.get(i));
		}
	}
	public void charactersWithNumParts(int num1 ,int num2) {
		for(int i = 0;i<freq.size();i++){
			if(freq.get(i) >= num1 && freq.get(i) <= num2) {
				System.out.println(characterName.get(i) + "  " + freq.get(i));
			}
		}
	}
	public static void main(String[] args) {
		CharactersInPlay cn = new CharactersInPlay();
		//cn.tester();
		cn.findAllCharacters();
		cn.charactersWithNumParts(10, 15);
	}

}
