package my.prep.practice.systemdesign.webcrawler;

public interface WebResource {
	
	public WebResourceType getResourceType();
	
	public String getResourceAddress();
	
	public long getResourceSizeInBytes();
}
