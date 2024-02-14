package com.openstar.post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.openstar.post.domain.Post;

@Mapper
public interface PostMapper {
	
	// input: subject, content output: X
	public void insertPost(
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("postId") int postId,
			@Param("content") String content,
			@Param("imagePath") String imagePath
	);
	
	public List<Post> getPostByPostId(
			@Param("postId") int postId
	);
	
	public Post selectPostDetailByPostid(
			@Param("id") int id
	);


}
