package com.openstar.book;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openstar.book.domain.Book;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/book")
public class BookRestController {

	@PostMapping("/booking-content")
	public Map<String, Object> bookingContent(
			@RequestBody Book requestData,
			HttpSession session) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		int movieId = requestData.getMovieId();
		
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result; 
		
	}
	
	
}
