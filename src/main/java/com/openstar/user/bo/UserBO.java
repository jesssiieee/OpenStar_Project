package com.openstar.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openstar.user.Entity.UserEntity;
import com.openstar.user.repository.UserRepository;

@Service
public class UserBO {
	
	@Autowired
	private UserRepository userRepsitory;

	// 로그인 id 중복확인을 위한 select
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepsitory.findByLoginId(loginId);
	}
	
	// 회원가입
	public Integer addUser(String loginId, String password, String name, String email) {
		
		UserEntity userEntity = userRepsitory.save(
				UserEntity.builder()
				.loginId(loginId)
				.password(password)
				.name(name)
				.email(email)
				.build()
			);
		
		return userEntity == null ? null : userEntity.getId();
		
	}
	
}
