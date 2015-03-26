package net.trustie.comment.pipeline;

import java.util.List;

import javax.annotation.Resource;

import net.trustie.comment.CnblogsQ_Comment;
import net.trustie.comment.model.CnblogsQ_Comment_Model;
import net.trustie.dao.CNblogsQ_Dao;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Task;
import core.PageModelPipeline;

@Component("cnblogsQCommentPipeline")
public class CNblogsQ_Comment_pipeline implements PageModelPipeline<CnblogsQ_Comment_Model>{
	@Resource
	private CNblogsQ_Dao cNblogsQ_Dao;
	
	@Override
	public void process(CnblogsQ_Comment_Model cnblogsq_comment, Task task) {
		// TODO Auto-generated method stub
		List<CnblogsQ_Comment> comments = cnblogsq_comment.getComments();
		for(CnblogsQ_Comment comment : comments){
			cNblogsQ_Dao.addComment(comment);
		}
	}	

}
