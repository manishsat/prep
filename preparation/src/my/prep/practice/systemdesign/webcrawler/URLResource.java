package my.prep.practice.systemdesign.webcrawler;

public class URLResource implements WebResource {

	private String url;
	private WebResourceType type;
	
	public URLResource(String url, WebResourceType type) {
		this.url = url;
		this.type = type;
	}
	@Override
	public WebResourceType getResourceType() {
		return type;
	}

	@Override
	public String getResourceAddress() {
		return url;
	}

	@Override
	public long getResourceSizeInBytes() {
		return url.length();
	}


}
