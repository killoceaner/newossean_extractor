package net.trustie.comment.model;

import java.util.ArrayList;
import java.util.List;

import net.trustie.comment.CnblogsQ_Comment;
import net.trustie.utils.DateHandler;
import net.trustie.utils.StringHandler;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.selector.Html;
import core.AfterExtractor;
import core.Page;
import core.ValidateExtractor;

@ExtractBy("//*[@id='container']/div[@id='container_content']/div[@id='main']")
public class CnblogsQ_Comment_Model implements AfterExtractor,
		ValidateExtractor {
	Logger logger = Logger.getLogger(this.getClass());

	private String pageUrl;

	private String urlMd5;

	private String extractTime;

	private String questionId;

	private List<CnblogsQ_Comment> comments = new ArrayList<CnblogsQ_Comment>();

	// 定义jsoup路径用于抽取Commnets
	private String comment_author_path = "div.answer_author>a.bluelink";
	private String comment_author_fortune_path = "div.answer_author";
	private String comment_author_level_path = "div.answer_author>a.graylink";
	private String comment_time_path = "div.answer_author";
	private String comment_score_path = "div.q_digg_bury>span.q_diggbury_count";
	private String comment_content_path = "div.q_content";
	@ExtractBy("//*[@class='qitem_best_answer_inner qclear']/html() | //*[@class='qitem_all_answer_inner qclear']/div[@class='q_answeritem qclear']/html()")
	private List<String> tables;

	@Override
	public void afterProcess(Page page) {

		// 四个属性用来标记同一个page里的每条comment
		this.pageUrl = page.getPageUrl();

		this.urlMd5 = DigestUtils.md5Hex(pageUrl);

		this.extractTime = DateHandler.getExtractTime();

		this.questionId = StringHandler.matchRightString(this.pageUrl, "\\d+");
				
		// 下面是对评论的抽取
		for (int i = 0; i < tables.size(); i++) {
			String comment_author_name = "";
			String comment_author_url = "";
			int comment_author_fortune = -1;
			String comment_author_level = "";
			String comment_time = null;
			int comment_score = 0;
			String comment_content = "";

			String table = tables.get(i);
			Html html = new Html(table);
			Document doc = html.getDocument();
			Elements eles;
			// 抽取评论作者
			eles = doc.select(comment_author_path);
			if (eles.size() == 0) {
				logger.info("error:cannot extract the comment's author");
			} else {
				comment_author_name = eles.get(0).text();
				comment_author_url = eles.get(0).attr("href");
			}

			eles = doc.select(comment_author_fortune_path);
			if (eles.size() == 0) {
				logger.info("error:cannot extract the comment's author's fortune");
			} else {
				String tmp = eles.get(0).text();
				tmp = StringHandler.matchRightString(tmp, "园豆：\\d+");
				tmp = StringHandler.matchRightString(tmp, "\\d+");
				comment_author_fortune = Integer.parseInt(tmp);
			}

			eles = doc.select(comment_author_level_path);
			if (eles.size() == 0) {
				logger.info("error:cannot extract the comment's author's level");
			} else {
				String tmp = eles.get(0).text();
				if (i == 0) {
					comment_author_level = tmp.trim();
				} else {
					int start = tmp.indexOf("(");
					int end = tmp.lastIndexOf(")");
					comment_author_level = tmp.substring(start, end);
				}
			}

			eles = doc.select(comment_time_path);
			if (eles.size() == 0) {
				logger.info("error:connot extract the comment's time");
			} else {
				String tmp = eles.get(0).text();
				comment_time = StringHandler.matchRightString(tmp,
						"\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}");
			}

			eles = doc.select(comment_score_path);
			if (eles.size() == 0) {
				logger.info("error:connot extract the comment's score");
			} else {
				comment_score = Integer.parseInt(eles.get(0).text().trim());
			}

			eles = doc.select(comment_content_path);
			if (eles.size() == 0) {
				logger.info("error:connot extract the comment's content");
			} else {
				comment_content = eles.get(0).text();
			}
			// 处理Comment的内容
			CnblogsQ_Comment comment = new CnblogsQ_Comment();
			comment.setComment_author(comment_author_name);
			comment.setComment_author_fortune(comment_author_fortune);
			comment.setComment_author_level(comment_author_level);
			comment.setComment_author_url(comment_author_url);
			comment.setComment_content(comment_content);
			comment.setComment_score(comment_score);
			comment.setComment_time(comment_time);
			// 给每条Comment加上url,qustionId与 md5 标记来区别

			comment.setPageUrl(pageUrl);
			comment.setUrlMd5(urlMd5);
			comment.setExtractTime(extractTime);
			comment.setQuestionId(questionId);

			// 将抽取的Comment加入comment队列中
			comments.add(comment);
		}
	}

	@Override
	public void validate(Page page) {
		if (page.getResultSkip(this)) {
			return;
		}
		// 主要看每条comment的三个标记是否都全；
		if (this.urlMd5 == null && this.pageUrl == null
				&& this.extractTime == null) {
			page.getResultSkip(this);
			return;
		}
	}

	public String getQuestionUrl() {
		return pageUrl;
	}

	public void setQuestionUrl(String questionUrl) {
		this.pageUrl = questionUrl;
	}

	public List<CnblogsQ_Comment> getComments() {
		return comments;
	}

	public void setComments(List<CnblogsQ_Comment> comments) {
		this.comments = comments;
	}

	public List<String> getTables() {
		return tables;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public void setTables(List<String> tables) {
		this.tables = tables;
	}

	public String getUrlMd5() {
		return urlMd5;
	}

	public void setUrlMd5(String urlMd5) {
		this.urlMd5 = urlMd5;
	}

	public String getExtractTime() {
		return extractTime;
	}

	public void setExtractTime(String extractTime) {
		this.extractTime = extractTime;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
}
