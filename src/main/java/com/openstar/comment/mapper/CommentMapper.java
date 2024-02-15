package com.openstar.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.openstar.comment.domain.Comment;

@Mapper
public interface CommentMapper {
	
	public void insertComment(
			@Param("detailId") int detailId , 
			@Param("postId") int postId, 
			@Param("userId") int userId, 
			@Param("userName") String userName, 
			@Param("content") String content
	);
	
	public Comment selectComment(
			@Param("detailId") int detailId,
			@Param("postId")int postId
	);

}
