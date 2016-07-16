package com.course2and3.allweeks;

public class OOCaesarCipher {
	private String alphaLow;
	private String alphaUp;
	private String shiftedLow;
	private String shiftedUp;
	private int mainKey;	
	OOCaesarCipher(int key) {
		alphaLow = "abcdefghijklmnopqrstuvwxyz" ;
		alphaUp  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" ;
		key = key % 26 ;
		mainKey = key;
		if(key != 0) {
			shiftedLow = alphaLow.substring(key, alphaLow.length()) + alphaLow.substring(0, key);
			shiftedUp = alphaUp.substring(key, alphaUp.length()) + alphaUp.substring(0, key);
		}
		else {
			shiftedLow = alphaLow;
			shiftedUp = alphaUp;
		}
		
	}
	
	public String encrypt(String input) {
		StringBuilder encrypted = new StringBuilder("");
		for(int i = 0;i<input.length();i++) {
			char ch = input.charAt(i);
			int index = 0;
			if(Character.isLowerCase(ch)) {
				index = alphaLow.indexOf(ch);
				encrypted = encrypted.append(shiftedLow.charAt(index));
			} else if(Character.isUpperCase(ch)) {
				index = alphaUp.indexOf(ch);
				encrypted = encrypted.append(shiftedUp.charAt(index));
			} else {
				encrypted = encrypted.append(ch);
			}
			
		}
		return encrypted.toString();
	}
	
	
	public String decrypt(String encrypted) {
		OOCaesarCipher cc = new OOCaesarCipher(26 - mainKey);
		String message = cc.encrypt(encrypted);
		return message;
	}
	
	
	
	public static void main(String[] args) {
		OOCaesarCipher cc = new OOCaesarCipher(21);
		System.out.println(cc.encrypt("Just a test string with lots of eeeeeeeeeeeeeeeees"));
		System.out.println(cc.decrypt("Epno v ozno nomdib rdoc gjon ja zzzzzzzzzzzzzzzzzn"));
	}

}
