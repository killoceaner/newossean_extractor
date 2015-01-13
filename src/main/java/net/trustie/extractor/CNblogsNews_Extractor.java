package net.trustie.extractor;

import java.sql.SQLException;

import net.trustie.downloader.GenerateRawPage;
import net.trustie.model.CNblogsNews_Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import core.PageModelPipeline;
import core.Site;
import extension.OsseanExtractor;

@SuppressWarnings("unused")
@Component
public class CNblogsNews_Extractor {
	@SuppressWarnings("rawtypes")
	@Qualifier("CNBlogsNews")
	@Autowired
	private PageModelPipeline modelPipeline;
	private GenerateRawPage generateRawPage;
	public void begin(){
		generateRawPage.setTableName("存html的表");
		OsseanExtractor.create(Site.me(), modelPipeline, CNblogsNews_Model.class).setUUID("CNBlogsNews").setDownloader(generateRawPage).start();
	}
	public static void main(String[] args)throws SQLException{
		ApplicationContext aContext=new FileSystemXmlApplicationContext("./resources/spring/applicationContext*.xml");
		final CsdnTopic_Extractor extractor = aContext
				.getBean(CsdnTopic_Extractor.class);//@#@#
		extractor.begin();
	}

}