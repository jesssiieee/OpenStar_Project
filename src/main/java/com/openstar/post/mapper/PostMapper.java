package com.openstar.post.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostMapper {
	
	// input: subject, content output: X
	public void insertPost(
			@Param("userId") int userId,
			@Param("content") String content,
			@Param("imagePath") String imagePath
	);

}
