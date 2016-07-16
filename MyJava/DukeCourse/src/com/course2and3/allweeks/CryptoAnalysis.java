package com.course2and3.allweeks;

public class CryptoAnalysis {
	
	private CaesarCipher decrypt;
	private int [] freq = new int [26];
	private String ref = "abcdefghijklmnopqrstuvwxyz";
	public String decryptCaesar(String encrypted) {
		decrypt = new CaesarCipher(0);
		findFreq(encrypted);
		int maxIndex = maxIndex(freq);
		char letter = ref.charAt(maxIndex);
		int shift = 4 - ref.indexOf(letter);
		if(4 - ref.indexOf(letter) < 0) {
			shift = -shift;
		}
		else {
			shift = 26 - shift;
		}
		String message = decrypt.encrypt(encrypted);
		//System.out.println("shift :" + shift);
		return message;
	}
	
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
	
	public String decryptTwoKeys(String encrypted) {
		
		// segregating odd even characters into separate Strings
		
		StringBuilder oddString = new StringBuilder("");
		StringBuilder evenString = new StringBuilder("");
		
		for(int i = 0 ;i<encrypted.length();i++) {
			char ch = encrypted.charAt(i);
			if(i % 2 == 0) {
				evenString = evenString.append(ch);
			}
			else {
				oddString = oddString.append(ch);
			}
		}
		
		// decrypting the segregated Strings
		String oddPart = decryptCaesar(oddString.toString());
		String evenPart = decryptCaesar(evenString.toString());
		
		
		
		return construct(evenPart,oddPart);
	}
	
	public String construct(String evenPart , String oddPart) {
		int length = (evenPart.length() + oddPart.length()) / 2;
		StringBuilder message = new StringBuilder("");
		for(int i = 0 ;i<length;i++) {
			message = message.append(evenPart.charAt(i));
			message = message.append(oddPart.charAt(i));
		}
		if(evenPart.length() > oddPart.length()) {
			message = message.append(evenPart.charAt(evenPart.length()-1));
		}
		if(evenPart.length() < oddPart.length()) {
			message = message.append(oddPart.charAt(oddPart.length()-1));
		}
		return message.toString();
	}
	
	public static void main(String[] args) {
	 CryptoAnalysis ca = new CryptoAnalysis();
	 //FileResource fr = new FileResource();
	 //String message = fr.asString();
	 System.out.println(ca.decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));
	 //System.out.println(ca.decryptCaesar("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
	// System.out.println(ca.decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));
	 System.out.println(ca.construct("hlowrd", "el ol!"));
}
}