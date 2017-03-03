package my.prep.practice.systemdesign.webcrawler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class URLDownloadChecker {

	private Map<String,URLResource> cache = new HashMap<String,URLResource>();
	private Lock lock = new ReentrantLock();
	public boolean isUrlAlreadyDownloaded(URLResource url) {
		try{
			lock.lock();
			if(cache.containsKey(url.getResourceAddress().hashCode())) {
				return Boolean.TRUE;
			}else{
				return Boolean.FALSE;
			}
		}finally {
			lock.unlock();
		}
		
	}
	
	public boolean markVisited(URLResource url) {
		try{
			lock.lock();
			if(cache.containsKey(getHashKey(url))) {
				throw new RuntimeException();
			}else{
				cache.put(getHashKey(url), url);
			}
			return Boolean.TRUE;
		}finally {
			lock.unlock();
		}
	}
	
	public String getHashKey(URLResource url) {
		return String.valueOf(url.getResourceAddress().hashCode());
	}
}
