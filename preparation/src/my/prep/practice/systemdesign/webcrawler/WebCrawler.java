package my.prep.practice.systemdesign.webcrawler;

public interface WebCrawler {

	public void start();
	public void stop();
	public void crawl(String[] seedUrls);
	public long getTotalCrawlPages();
}
