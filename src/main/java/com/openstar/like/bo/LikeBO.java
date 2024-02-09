package com.openstar.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openstar.like.mapper.LikeMapper;

@Service
public class LikeBO {
	
	@Autowired
	private LikeMapper likeMapper;
	
	public void likeToggle(int userId, int movieId) {

		// like가 있으면
		if (likeMapper.selectLikeCountByPostIdOrUserId(userId, movieId) > 0) {
			// 삭제
			likeMapper.deleteLikeByPostIdUserId(userId, movieId);
		}
		// 없으면
		else {
			// 추가
			likeMapper.addLikeByPostIdUserId(userId, movieId);
		}

	}

}
