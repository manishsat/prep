package my.prep.practice.systemdesign.webcrawler;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class CrawlerImplementation implements Crawler {

	private Queue<URLResource> processingQueue = new LinkedList<URLResource>();
	
	private URLFetcher fetcher = new URLFetcherImplementation();
	
	private URLDownloadChecker checker = new URLDownloadChecker();
	
	private String url;
	
	private AtomicBoolean isStopped = new AtomicBoolean(false);
	public CrawlerImplementation(String seedUrl) {
		this.url = url;
	}
	
	
	@Override
	public void stop() {
		isStopped.compareAndSet(false, true);
		
	}

	@Override
	public void crawl() {
		URLResource resource = new URLResource(url, WebResourceType.HTML_URL);
		processingQueue.add(resource);
		
		while(!processingQueue.isEmpty() && !isStopped.get()) {
			URLResource urlResource = processingQueue.poll();
			if(urlResource != null) {
				List<WebResource> content =  fetcher.fetchResource(urlResource);
				
				for(WebResource wr : content) {
					if(WebResourceType.HTML_URL == wr.getResourceType()) {
							URLResource r = (URLResource)wr;
							if(!isVisited(r)){
								if(checker.markVisited(r)) {
									processingQueue.offer(r);
								}
							}	
					}
				}
			}
		}
		
		
	}
	
	
	private boolean isVisited(URLResource r) {
		return checker.isUrlAlreadyDownloaded(r);
	}

}
