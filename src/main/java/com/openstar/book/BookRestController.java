package com.openstar.book;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openstar.book.bo.BookBO;
import com.openstar.book.domain.Book;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/book")
public class BookRestController {
	
	@Autowired
	private BookBO bookBO;

	@PostMapping("/booking-content")
	public Map<String, Object> bookingContent(
			@RequestBody Book requestData,
			HttpSession session) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		int movieId = requestData.getMovieId();
		String location = requestData.getLocation();
		String date = requestData.getDate();
		String title = requestData.getTitle();
		String showtime = requestData.getShowtime();
		int child = requestData.getChild();
		int adult = requestData.getAdult();
		int headcount = child + adult;
		String selectedSeats = requestData.getSelectedSeats();
		int price = requestData.getPrice();
		
		// db insert
		bookBO.addContent(userId, movieId, location, date, title, showtime, adult, child, headcount, selectedSeats, price);
		
		// 응답값 
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result;
		
	}
	
	
}
