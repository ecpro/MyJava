package com.course2and3.allweeks;

import edu.duke.*;
public class TestCaesarCipher {
	private int [] freq = new int [26];
	
	public void findFreq(String text) {
		text = text.toLowerCase();
		for(int i = 0; i<text.length() ;i++){
			if(Character.isLetter(text.charAt(i))) {
				int textIndex = text.charAt(i) - 97 ; 
				freq[textIndex]++; 
			}
		}
	}
	
	public int maxIndex(int [] array) {
		int maxIndex = 0;
		for(int i = 0 ;i<array.length ; i++) {
			if(array[i]> array[maxIndex]) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	public void simpleTest() {
		FileResource fr = new FileResource();
		String message = fr.asString();
		//System.out.println(message);
		//String message = "eee there how are you?";
		OOCaesarCipher cc = new OOCaesarCipher(18);
		String encrypted = cc.encrypt(message);
		System.out.println(encrypted);
		System.out.println("\n\n\n\n Decrypted message  from here : ");
		System.out.println(cc.decrypt(encrypted));
	}
	public static void main(String[] args) {
		TestCaesarCipher tcc = new TestCaesarCipher();
		tcc.simpleTest();
	}
}
