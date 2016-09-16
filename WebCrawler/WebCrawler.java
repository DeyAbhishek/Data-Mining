package WebCrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

	private Queue<String> queue;  //type is String since URLs are String
	private List<String> discoveredWebsiteList;
	
	private int websitesProcessedFromQueue = 0;  

	public WebCrawler() {
		queue = new LinkedList<>();
		this.discoveredWebsiteList = new ArrayList<>();
	}
	
	/*
	 * discoverWeb methods actually crawls the web and discover the websites 
	 * using BFS strategy
	 */
	public void discoverWeb(String root) {
		
		//BFS algorithm is implemented below
		this.queue.add(root);
		this.discoveredWebsiteList.add(root);

		while (!queue.isEmpty()) {

			String v = this.queue.remove();
			String rowHtml = readURL(v);  // read the html content of the newly found website

			String regexp = "https://(\\w+\\.)*(\\w+)";  // reg exp for url pattern starting with https://
			Pattern pattern = Pattern.compile(regexp);
			Matcher matcher = pattern.matcher(rowHtml);  // search for pattern in the html content
			
			while( matcher.find() ){  
				String w = matcher.group();  //group() -> Returns the input subsequence matched by the previous match.
				
				if( !discoveredWebsiteList.contains(w) ){
					discoveredWebsiteList.add(w);
					System.out.println("Website found with URL: " + w);
					queue.add(w);
				}
			}
			
			websitesProcessedFromQueue++;
			if (websitesProcessedFromQueue == 10) {
				break;  // stop condition: stop after processing children of first 10 websites from queue
			}
		}
	}
	
	/*
	 * readURL method returns the HTML content of a given website url
	 */
	private String readURL(String v) {
		
		String rawHtml = "";
		
		try {
			URL url = new URL(v);  // creating URL out of input string v
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			//openStream() -> Opens a connection to this URL and returns an InputStream for reading from that connection.

			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				rawHtml += inputLine;
			}

			in.close();				
		} catch (Exception e) {
			System.out.println("Error reading from " + v);
		}
		
		return rawHtml;
	}
}
