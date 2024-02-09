package com.openstar.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {

	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int userId, 
			@Param("userId") Integer movieId // 안 쓰면
	);

	// insert
	public void addLikeByPostIdUserId(
			@Param("postId") int userId, 
			@Param("userId") int movieId
	);

	// delete
	public void deleteLikeByPostIdUserId(
			@Param("postId") int userId, 
			@Param("userId") int movieId
	);

}
