package com.openstar.check.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CheckMapper {
	
	public int selectLikeCountByContentIdOrUserId(
			@Param("userId") Integer userId,
			@Param("contentId") int contentId
	);
	
	public void addLikeByContentIdUserId(
			@Param("userId") Integer userId,
			@Param("contentId") int contentId,
			@Param("type") String type
			
	);
	
	public void deleteLikeByContentIdUserId(
			@Param("userId") Integer userId,
			@Param("contentId") int contentId
	);
	
	
	
}
