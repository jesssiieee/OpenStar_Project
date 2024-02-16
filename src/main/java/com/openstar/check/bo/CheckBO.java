package com.openstar.check.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openstar.check.mapper.CheckMapper;

@Service
public class CheckBO {
	
	@Autowired
	private CheckMapper checkMapper;
	
	// input: postId, userId output: X
	public void likeToggle(int userId, int contentId, String type) {

		// like가 있으면
		if (checkMapper.selectLikeCountByContentIdOrUserId(userId, contentId, type) > 0) {
			// 삭제
			checkMapper.deleteLikeByContentIdUserId(userId, contentId, type);
		}
		// 없으면
		else {
			// 추가
			checkMapper.addLikeByContentIdUserId(userId, contentId, type);
		}

	}
	
//	// input: postId, userId(null or) output: boolean
//	public boolean getLikeCountByContentIdUserId(Integer userId, int contentId, String type) {
//		// 비로그인이면 무조건 빈 하트 => false
//		if (userId == null) {
//			return false;
//		}
//		// 로그인	 -  Count값이 0보다 크면(==1이면) 채운 하트, 그렇지 않으면 false
//		return checkMapper.selectLikeCountByContentIdOrUserId(userId, contentId, type) > 0; // <- true
//		
//	}
	
	public boolean getCountByContentIdUserIdType(Integer userId, int contentId, String type) {
		// 비로그인이면 무조건 빈 하트 => false
		if (userId == null) {
			return false;
		}
		// 로그인	 -  Count값이 0보다 크면(==1이면) 채운 하트, 그렇지 않으면 false
		return checkMapper.selectCountByContentIdUserIdType(userId, contentId, type) > 0; // <- true
		
	}
	
	public void bookmarkToggle(int userId, int contentId, String type) {
		
		if (checkMapper.selectBookMarkCountByContentIdOrUserId(userId, contentId, type) > 0) {
			// 삭제
			checkMapper.deleteBookMarkByContentIdUserId(userId, contentId, type);
		}
		// 없으면
		else {
			// 추가
			checkMapper.addBookMarkByContentIdUserId(userId, contentId, type);
		}
		
	}
	
//	public boolean getBookMarkCountByContentIdUserId(Integer userId, int contentId) {
//		if (userId == null) {
//			return false;
//		}
//		return checkMapper.selectBookMarkCountByContentIdOrUserId(userId, contentId) > 0;
//	}
	

}
