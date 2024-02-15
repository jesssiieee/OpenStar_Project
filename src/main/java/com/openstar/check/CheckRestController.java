package com.openstar.check;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openstar.check.bo.CheckBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/check")
public class CheckRestController {
	
	@Autowired
	private CheckBO checkBO;

	@RequestMapping("/like/{contentId}")
	public Map<String, Object> likeToggle(
			@RequestParam("type") String type,
			@PathVariable("contentId") int contentId, 
			HttpSession session ) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		checkBO.likeToggle(userId, contentId, type);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
		
		
	}
	
}
