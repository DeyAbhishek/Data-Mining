package WebCrawler;

public class App {

	public static void main(String[] args) {
		
		String rootUrl = "https://www.google.com";		
		WebCrawler crawler = new WebCrawler();	
		crawler.discoverWeb(rootUrl);
		
	}
}
