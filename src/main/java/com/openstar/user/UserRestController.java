package com.openstar.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openstar.user.Entity.UserEntity;
import com.openstar.user.bo.UserBO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	/**
	 * 종복확인
	 * @param loginId
	 * @return
	 */
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
	
	/**
	 * 회원가입
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
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
	
	// 로그인
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId, 
			@RequestParam("password") String password,
			HttpServletRequest request ) {
		
		boolean isValid = false;
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		// db 조회 
		UserEntity user = userBO.getUserEntityByLoginId(loginId);
	
		
		Map<String, Object> result = new HashMap<>();
		
		if (user != null) {
			// 비밀번호 일치 확인
			if (isValid = BCrypt.checkpw(password, user.getPassword())) {
				
				// 로그인 정보 세션에 담아두가
				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getId());
				session.setAttribute("userLoginId", user.getLoginId());
				session.setAttribute("userName", user.getName());
				
				result.put("code", 200);
				result.put("result", "성공");
			}
		} else {
			result.put("code", 300);
			result.put("error_message", "존재하지 않는 사용자입니다.");
		}
		
		return result;
	}
	
}
