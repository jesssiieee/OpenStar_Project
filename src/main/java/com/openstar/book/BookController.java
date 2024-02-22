package com.openstar.book;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openstar.movie.Entity.MovieTrend;
import com.openstar.movie.bo.MovieTrendBO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private MovieTrendBO movieTrendBO;

	@GetMapping("/booking-view/{searchId}")
	// url : http://localhost/book/booking-view
	public String booking(
			@PathVariable("searchId") int searchId,
			HttpSession session,
			Model model) throws UnsupportedEncodingException, IOException {
		
	    Integer userId = (Integer) session.getAttribute("userId");
	    
	    if (userId == null) {
	         return "redirect:/user/sign-in-view";
	    }
	    
		MovieTrend movieTrendResultList = movieTrendBO.parseMovieTrendJson(searchId);
		
		model.addAttribute("movieTrendResultList", movieTrendResultList);
		model.addAttribute("viewName", "book/booking");
		return "template/layout";
	}
	
}
