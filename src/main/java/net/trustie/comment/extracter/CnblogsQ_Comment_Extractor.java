package net.trustie.comment.extracter;

import java.sql.SQLException;

import net.trustie.comment.model.CnblogsQ_Comment_Model;
import net.trustie.downloader.DataBasePageErrorOutPut;
import net.trustie.downloader.GenerateRawPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import core.PageModelPipeline;
import core.Site;
import extension.OsseanExtractor;

@Component
public class CnblogsQ_Comment_Extractor {
	@SuppressWarnings("rawtypes")
	@Qualifier("cnblogsQCommentPipeline")
	@Autowired
	private PageModelPipeline modelPipeline;

	@Qualifier("generateRawPage")
	@Autowired
	private GenerateRawPage generateRawPage;

	@Qualifier("errorPageToDB")
	@Autowired
	private DataBasePageErrorOutPut dbPageErrorOutPut;
	
	public void begin() {
		generateRawPage.setTable("cnblogs_q_solved_html_detail");
		dbPageErrorOutPut.setTableName("cnblogs_q_solved_error_page");

		OsseanExtractor
				.create(Site.me().setResultNum(100), modelPipeline,
						CnblogsQ_Comment_Model.class).setUUID("cnblog_q_solve_comment")
				.setDownloader(generateRawPage)
				.setPageErrorOutPut(dbPageErrorOutPut).start();
	}
	
	public static void main(String[] args) throws SQLException {
		ApplicationContext aContext = new ClassPathXmlApplicationContext(
				"classpath:/spring/applicationContext*.xml");

		final CnblogsQ_Comment_Extractor extractor = aContext
				.getBean(CnblogsQ_Comment_Extractor.class);

		extractor.begin();
	}
}
