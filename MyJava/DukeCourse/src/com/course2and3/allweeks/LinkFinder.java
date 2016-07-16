package com.course2and3.allweeks;

import edu.duke.StorageResource;
import edu.duke.URLResource;


public class LinkFinder {
	//private URLResource webpage = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
	//private URLResource webpage = new URLResource("https://users.cs.duke.edu/~rodger/manylinks.html");
	private URLResource webpage = new URLResource("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
	private StorageResource urllist = new StorageResource();
	// method to find youTube URL in a web page
	public void findLink()
	{
		
		for(String link : webpage.words())
		{
			
			String youtubeLink = link;
			youtubeLink = youtubeLink.toLowerCase();
			int youtubeIndex = youtubeLink.indexOf("youtube");
			int start = 0;
			int end = 0;
			if(youtubeIndex != -1) {
				start = youtubeLink.lastIndexOf("\"",youtubeIndex);
				end = youtubeLink.lastIndexOf("\"");
				System.out.println(link.subSequence(start, end+1));
				//urllist.add((String) link.subSequence(start, end+1));
			}
		}
	}
	public StorageResource findURL(String url) {
		int start = 0;
		int end = 0;
		int pos = 0;
		StorageResource sr = new StorageResource();
		String href = "href=\"";
		while(true) {
			if(end >= url.length() - 1) break;
			pos = url.indexOf(href, start);
			if(pos == -1) {
				break;
			}
			start = pos + href.length();
			end = url.indexOf("\"", start);
			//System.out.println("start " + start + "    " + "end " + end);
			sr.add(url.substring(start, end));
			//System.out.println(url.substring(start, end));
			start = end + 1;
		}
		return sr;
	}
	
	public void testURLwithStorage() {
		urllist = findURL(webpage.asString());
		int httpsCount = 0;
		int dotComCount = 0;
		int comCount = 0;
		int dotCount = 0;
		for(String links : urllist.data()) {
			System.out.println(links);
		}
		System.out.println("# ouf URLs in this page : " + urllist.size());
		
		for(String links : urllist.data()) {
			
			if(links.startsWith("https://")) {
				httpsCount++;
				System.out.println(links);
			}
		}
		
		System.out.println("total links with https:// are : " + httpsCount);
		
		for(String links : urllist.data()) {
			if(links.contains(".com")) {
				dotComCount++;
				System.out.println(links);
			}
		}
		System.out.println("links with .com : " + dotComCount);
		
		for (String links : urllist.data() ) {
			if(links.endsWith(".com") || links.endsWith(".com/")) {
				comCount++;
				System.out.println(links);
			}
		}
		System.out.println("URLs ending with .com or .com/ are " + comCount);
		for(String links : urllist.data() ) {
			int start = 0;
			int pos = 0;
			while(true) {
				if(start > links.length() - 1) break;
				pos = links.indexOf(".", start);
				if(pos == -1 ) {
					break;
				}
				start = pos + 1;
				dotCount++;
			}
		}
		System.out.println("total . count is : " + dotCount);
	}
	
}
