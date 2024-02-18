package com.openstar.check.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.openstar.check.domain.Check;

@Mapper
public interface CheckMapper {
	
	public int selectLikeCountByContentIdOrUserId(
			@Param("userId") Integer userId,
			@Param("contentId") int contentId,
			@Param("type") String type
	);
	
	public int selectCountByContentIdUserIdType (
			@Param("userId") Integer userId,
			@Param("contentId") int contentId,
			@Param("type") String type
	);
	
	public int selectBookMarkCountByContentIdOrUserId (
			@Param("userId") Integer userId,
			@Param("contentId") int contentId,
			@Param("type") String type
	);
	
	public void addLikeByContentIdUserId(
			@Param("userId") Integer userId,
			@Param("contentId") int contentId,
			@Param("type") String type
			
	);
	
	public void addBookMarkByContentIdUserId(
			@Param("userId") Integer userId,
			@Param("contentId") int contentId,
			@Param("type") String type
			
	);
	
	public void deleteLikeByContentIdUserId(
			@Param("userId") Integer userId,
			@Param("contentId") int contentId,
			@Param("type") String type
	);
	
	public void deleteBookMarkByContentIdUserId(
			@Param("userId") Integer userId,
			@Param("contentId") int contentId,
			@Param("type") String type
	);
	
	public List<Check> selectCheckByUserId(
			@Param("userId") Integer userId
	);
	
	
	
}
