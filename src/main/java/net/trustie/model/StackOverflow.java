package net.trustie.model;

import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractBy.Source;
import core.AfterExtractor;
import core.Page;
import core.ValidateExtractor;
import extension.StringHandler;

@ExtractBy("//*[@class='container']/div[@id='content']/div/div[@id='mainbar']/div[@id='question']/table")
public class StackOverflow implements AfterExtractor, ValidateExtractor {
	private String tag;

	private String extractTime;

	private String url;

	private String urlMD5;

	private String pageMD5;
	
	private String questionId;

	@ExtractBy(value = "//*[@id='question-header']/h1/a[@class='question-hyperlink']/text()", source = Source.RawHtml)
	private String questionTitle;

	@ExtractBy("//*[@class='postcell']/div/div[@class='post-text']/allText()")
	private String questionContent;

	@ExtractBy("//*[@class='postcell']/div/div[@class='post-taglist']/a/allText()")
	private List<String> tags;

	@ExtractBy("//*div[@class='user-details']/a/@href")
	private String authorUrl;

	@ExtractBy("//*div[@class='user-details']/a/text()")
	private String author;

	@ExtractBy("//*div[@class='user-action-time']/span[@class='relativetime']/@title")
	private String postTime;

	@ExtractBy("//*td[@class='votecell']/div[@class='vote']/span/text()")
	private String voteNum;

	@ExtractBy("//*td[@class='votecell']/div[@class='vote']/div[@class='favoritecount']/b/text()")
	private String likeNum;

	@ExtractBy(value = "//*table[@id='qinfo']/tbody/tr[2]/td[2]/p[@class='label-key']/b/text()", source = Source.RawHtml)
	private String viewNum;

	@ExtractBy(value = "//*div[@id='answers-header']/div/h2/span/text()", source = Source.RawHtml)
	private String answerNum;

	@ExtractBy(value = "//*table[@id='qinfo']/tbody/tr[3]/td[2]/p[@class='label-key']/b/a/@title", source = Source.RawHtml)
	private String active;

	public void afterProcess(Page page) {
		// 处理标签
		this.tag = StringHandler.combineTags(tags);

		// 处理url
		this.url = page.getPageUrl();

		// 处理作者
		if (StringUtils.isNotBlank(this.author))
			this.authorUrl = "http://stackoverflow.com" + this.authorUrl;
		else
			page.setResultSkip(this, true);

		// 处理viewNum
		this.viewNum = StringHandler.matchRightString(this.viewNum, "\\d+");

		// 处理likeNum
		if (StringUtils.isBlank(this.likeNum))
			this.likeNum = "0";

		// 处理postTime
		this.postTime = this.postTime.substring(0, this.postTime.length() - 1);
		this.postTime=StringHandler.addTimeToDate(this.postTime, 8*60*60);

		// 处理active
		this.active = this.active.substring(0, this.active.length() - 1);
		this.active=StringHandler.addTimeToDate(this.active, 8*60*60);

		// 处理urlMD5
		this.urlMD5 = DigestUtils.md5Hex(this.url);

		// pageMD5
		this.pageMD5 = DigestUtils.md5Hex(this.questionTitle + this.viewNum);

		// 处理extractorTime
		this.extractTime = StringHandler.getExtractTime();
		
		//处理帖子的ID
		this.questionId=StringHandler.matchRightString(this.url, "questions/\\d+/");
		this.questionId=StringHandler.matchRightString(this.questionId, "\\d+");
	}

	@Override
	public void validate(Page page) {
		if (page.getResultSkip(this))
			return;
		
		if (StringHandler.isAtLeastOneBlank(this.url, this.questionTitle,
				this.author, this.authorUrl)) {
			page.setResultSkip(this, true);
			return;
		}

		if (!StringHandler.canFormatterInteger(this.viewNum, this.voteNum,
				this.likeNum, this.answerNum)) {
			page.setResultSkip(this, true);
			return;
		}

		if (!StringHandler.canFormatterDate(this.postTime, this.extractTime))
			page.setResultSkip(this, true);
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getAuthorUrl() {
		return authorUrl;
	}

	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getVote() {
		return voteNum;
	}

	public void setVote(String voteNum) {
		this.voteNum = voteNum;
	}

	public String getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(String likeNum) {
		this.likeNum = likeNum;
	}

	public String getViewNum() {
		return viewNum;
	}

	public void setViewNum(String viewNum) {
		this.viewNum = viewNum;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getExtractTime() {
		return extractTime;
	}

	public void setExtractTime(String extractTime) {
		this.extractTime = extractTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlMD5() {
		return urlMD5;
	}

	public void setUrlMD5(String urlMD5) {
		this.urlMD5 = urlMD5;
	}

	public String getPageMD5() {
		return pageMD5;
	}

	public void setPageMD5(String pageMD5) {
		this.pageMD5 = pageMD5;
	}

	public String getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(String voteNum) {
		this.voteNum = voteNum;
	}

	public String getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(String answerNum) {
		this.answerNum = answerNum;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}	
}
