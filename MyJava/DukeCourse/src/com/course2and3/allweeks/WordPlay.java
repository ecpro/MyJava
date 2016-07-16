package com.course2and3.allweeks;

public class WordPlay {
	public boolean isVowel(char ch) {
		ch = Character.toLowerCase(ch);
		if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
			return true;
		}
		return false;
	}
	public String replaceVowels(String phrase,char ch) {
		StringBuilder sb = new StringBuilder(phrase);
		for(int i = 0;i<phrase.length();i++) {
			if(isVowel(sb.charAt(i))) {
				sb.setCharAt(i, ch);;
			}
		}
		return sb.toString();
	}
	public String emphasize(String phrase, char ch) {
		StringBuilder sb = new StringBuilder(phrase);
		for(int i = 0;i<phrase.length();i++) {
			if(sb.charAt(i) == Character.toUpperCase(ch) || sb.charAt(i) == Character.toLowerCase(ch)) {
				if(i % 2 == 0) {
					sb.setCharAt(i, '*');
				} else {
					sb.setCharAt(i,'+');
				}
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		WordPlay wp = new WordPlay();
		System.out.println(wp.replaceVowels("Hello World AEiou", '4'));
		System.out.println(wp.emphasize("Mary Bella Abracadabra", 'a'));
		

	}

}
