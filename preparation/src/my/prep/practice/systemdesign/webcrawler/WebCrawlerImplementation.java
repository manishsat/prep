package my.prep.practice.systemdesign.webcrawler;

import java.util.ArrayList;
import java.util.List;

public class WebCrawlerImplementation implements WebCrawler{

	
	List<Crawler> crawlerList = new ArrayList<Crawler>();
	@Override
	public void start() {
		for(Crawler c:crawlerList) {
			c.crawl();
		}
		
	}

	@Override
	public void stop() {
		for(Crawler c:crawlerList) {
			c.stop();
		}
		
	}

	@Override
	public void crawl(String[] seedUrls) {
		for(String seedUrl:seedUrls) {
			crawlerList.add(new CrawlerImplementation(seedUrl));	
		}
	}

	@Override
	public long getTotalCrawlPages() {
		// TODO Auto-generated method stub
		return 0;
	}

}
