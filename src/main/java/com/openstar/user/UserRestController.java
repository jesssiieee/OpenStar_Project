package com.openstar.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openstar.user.Entity.UserEntity;
import com.openstar.user.bo.UserBO;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	// 중복확인
	@RequestMapping("/is-duplicated-id")
	public Map<String, Object> isDuplicatedId (
			@RequestParam("loginId") String loginId) {
		
		// DB 조회 userId select
		
		UserEntity user = userBO.getUserEntityByLoginId(loginId);
		
		Map<String, Object> result = new HashMap<>();
		
		if (user != null) { // user가 null 이지 않다면 = 중복
			result.put("code", 200);
			result.put("is_duplicated_id", true);
		} else { // 중복 아님 
			result.put("code", 200);
			result.put("is_duplicated_id", false);
		}
		
		return result;
	}
	
	// 회원가입
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId, 
			@RequestParam("password") String password, 
			@RequestParam("name") String name, 
			@RequestParam("email") String email ) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// $2a$10$5IhgiirQLWrmXa1hCpK3C.JaK5Jjrnp25fQv/uWK6dg9AGAVwnrfq
		String hashedPassword = encoder.encode(password);
		
		// db insert
		Integer userId = userBO.addUser(loginId, hashedPassword, name, email);
		
		Map<String, Object> result = new HashMap<>();
		if (userId != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "회원가입에 실패했습니다.");
		}
		return result;
	}
	
}
