package net.trustie.comment;

public class CsdnAsk_Comment {

	private String pageUrl;
	private String urlMd5;
	private int id;
	private int askId;
	private String comment_author_name;
	private String comment_author_url;
	private String comment_content;
	private String comment_time;
	private int comment_supportNum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAskId() {
		return askId;
	}

	public void setAskId(int askId) {
		this.askId = askId;
	}

	public String getComment_author_name() {
		return comment_author_name;
	}

	public void setComment_author_name(String comment_author_name) {
		this.comment_author_name = comment_author_name;
	}

	public String getComment_author_url() {
		return comment_author_url;
	}

	public void setComment_author_url(String comment_author_url) {
		this.comment_author_url = comment_author_url;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getComment_time() {
		return comment_time;
	}

	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}

	public int getComment_supportNum() {
		return comment_supportNum;
	}

	public void setComment_supportNum(int comment_supportNum) {
		this.comment_supportNum = comment_supportNum;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getUrlMd5() {
		return urlMd5;
	}

	public void setUrlMd5(String urlMd5) {
		this.urlMd5 = urlMd5;
	}

}
