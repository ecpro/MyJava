package com.course2and3.allweeks;

import edu.duke.*;
public class WordLengths {
	private FileResource resource;
	private int [] count = new int [30];
	
	public FileResource getResource() {
		return resource;
	}
	public void setResource(FileResource resource) {
		this.resource = resource;
	}
	public int[] getCount() {
		return count;
	}
	public void setCount(int[] count) {
		this.count = count;
	}
	public void countWordLengths(FileResource resource, int [] count) {
		
		for(String word : resource.words()) {
			int length = word.length();
			if(length == 1 && !(Character.isAlphabetic(word.charAt(0)))) continue;
			if(!(Character.isAlphabetic(word.charAt(0)))) length--;
			if(!(Character.isAlphabetic(word.charAt(word.length()-1)))) length--;
			if(length >= count.length-1)
				{
					count[count.length-1]++;
					System.out.println("length:" + length + " " + word);
				}
			else
				{
					(count[length])++;
					if(length == 16 || length == 17) System.out.println("length:" + length + " " + word);
				}
		}
	}
	public static void main(String[] args) {
		WordLengths wl = new WordLengths();
		wl.setCount(new int [30]);
		wl.setResource(new FileResource());
		wl.countWordLengths(wl.getResource(), wl.getCount());
		int [] count = wl.getCount();
		
		for(int i=0;i<count.length;i++) {
			System.out.println("Word length : " + i + " Count :" + count[i]);
		}

	}

}
