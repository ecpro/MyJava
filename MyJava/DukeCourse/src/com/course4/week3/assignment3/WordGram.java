package com.course4.week3.assignment3;

public class WordGram {
    private String[] myWords;
    //private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(int i = 0; i < length(); i++) {
            ret = ret + myWords[i] + " ";
        }

        return ret.trim();
    }

    public boolean equals(Object o) {
        if(o == null || o.getClass() != this.getClass()) return false;
        WordGram other = (WordGram) o;
        if(this.length() != other.length()) return false;
        for(int i = 0; i < length(); i++) {
            if(!myWords[i].equals(other.wordAt(i))) return false;
        }
        return true;

    }



    public int hashCode() {
        return this.toString().hashCode();
    }

    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        String [] temp = new String[myWords.length];
        for(int i = 0; i < out.length() - 1; i++) {
            temp[i] = new String(out.wordAt(i+1));
        }
        temp[temp.length -1] = new String(word);

        out = new WordGram(temp, 0, temp.length);
        return out;
    }

}