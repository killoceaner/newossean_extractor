package net.trustie.dao;
import net.trustie.comment.CnblogsQ_Comment;
import net.trustie.model.CNblogsQ_Model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface CNblogsQ_Dao {
	
	@Insert("insert into cnblog_question"
			+ "(`questionId`,`questionUrl`,`questionTitle`,`tag`,`questionContent`,`voteNum`,`viewNum`,`scoreBean`,`answerNum`,`questionType`,`postTime`,`extractTime`,`author`,`authorUrl`,`pageMD5`,`history`)"
			+ " values (#{questionId},#{questionUrl},#{questionTitle},#{tag},#{questionContent},#{voteNum},#{viewNum},#{scoreBean},#{answerNum},#{questionType},#{postTime},#{extractTime},#{author},#{authorUrl},#{pageMD5},#{history})")
	public int add(CNblogsQ_Model cNblogsQ_Model);
	
	@Insert("insert into cnblog_question_comment"
			+"(`pageUrl`,`urlMd5`,`questionId`,`comment_author_name`,`comment_author_url`,`comment_author_fortune`,`comment_author_level`,`comment_time`,`comment_score`,`comment_content`,`extractTime`)"
			+" values (#{pageUrl},#{urlMd5},#{questionId},#{comment_author_name},#{comment_author_url},#{comment_author_fortune},#{comment_author_level},#{comment_time},#{comment_score},#{comment_content},#{extractTime})")
	public void addComment( CnblogsQ_Comment comment);

}
