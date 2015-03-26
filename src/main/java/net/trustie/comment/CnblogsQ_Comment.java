package net.trustie.comment;

public class CnblogsQ_Comment {

	private String pageUrl;
	private String urlMd5;
	private int id;
	private String questionId;
	private String comment_author_name;
	private String comment_author_url;
	private int comment_author_fortune;
	private String comment_author_level;
	private String comment_time;
	private int comment_score;
	private String comment_content;
	private String extractTime;
	
	public int getId() {
		return id;
	}

	public String getExtractTime() {
		return extractTime;
	}

	public void setExtractTime(String extractTime) {
		this.extractTime = extractTime;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getComment_author() {
		return comment_author_name;
	}

	public void setComment_author(String comment_author_name) {
		this.comment_author_name = comment_author_name;
	}

	public String getComment_author_url() {
		return comment_author_url;
	}

	public void setComment_author_url(String comment_author_url) {
		this.comment_author_url = comment_author_url;
	}

	public int getComment_author_fortune() {
		return comment_author_fortune;
	}

	public void setComment_author_fortune(int comment_author_fortune) {
		this.comment_author_fortune = comment_author_fortune;
	}

	public String getComment_author_level() {
		return comment_author_level;
	}

	public void setComment_author_level(String comment_author_level) {
		this.comment_author_level = comment_author_level;
	}

	public String getComment_time() {
		return comment_time;
	}

	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}

	public int getComment_score() {
		return comment_score;
	}

	public void setComment_score(int comment_score) {
		this.comment_score = comment_score;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
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

	public String getComment_author_name() {
		return comment_author_name;
	}

	public void setComment_author_name(String comment_author_name) {
		this.comment_author_name = comment_author_name;
	}
}
