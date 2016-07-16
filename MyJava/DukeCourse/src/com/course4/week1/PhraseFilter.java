package com.course4.week1;
public class PhraseFilter implements Filter {

	private String pos;
	private String phrase;
	
	public PhraseFilter(String pos, String phrase) {
		this.pos = pos;
		this.phrase = phrase;
	}
	
	public boolean satisfies(QuakeEntry qe) {
		
		if(pos.equals("start")) { return qe.getInfo().startsWith(phrase) ; }
		if(pos.equals("end")) { return qe.getInfo().endsWith(phrase); }
		if(pos.equals("any")) { return qe.getInfo().contains(phrase); }
		return false;
	}

	public String getName() { 
		return "Phrase Filter";
		
	}

}
