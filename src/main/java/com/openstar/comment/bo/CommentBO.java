package com.openstar.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openstar.comment.domain.Comment;
import com.openstar.comment.mapper.CommentMapper;

@Service
public class CommentBO {
	
	@Autowired
	private CommentMapper commentMapper;
	
	public void addComment(int detailId, int postId, int userId, String userLoginId, String content) {
		
		commentMapper.insertComment(detailId, postId, userId, userLoginId, content);
		
	}
	
	public Comment getComment(int detailId, int postId) {
		
		return commentMapper.selectComment(detailId, postId);
		
	}

}
