package my.prep.practice.systemdesign.webcrawler;

import java.util.List;

public interface URLFetcher {

	public List<WebResource> fetchResource(String url);

	public List<WebResource> fetchResource(URLResource url);
	
}

