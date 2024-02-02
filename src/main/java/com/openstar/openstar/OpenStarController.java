package com.openstar.openstar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openstar.movie.Entity.MovieEntity;
import com.openstar.movie.repository.MovieRepository;

@Controller
@RequestMapping("/openstar")
public class OpenStarController {
	
	@Autowired
	private MovieRepository movieRepository;

	@GetMapping("/first-view")
	// url: http://localhost/openstar/first-view
	public String firstView(Model model) {
		model.addAttribute("viewName", "openstar/firstView");
		return "template/layout";
	}
	
	@GetMapping("/home-list-view")
	// url: http://localhost/openstar/home-list-view
	public String homeListView(Model model) {
		
		// DB 조회 
		List<MovieEntity> movieTrendList = movieRepository.findAll();
		
		model.addAttribute("movieTrendList", movieTrendList);
		model.addAttribute("viewName", "openstar/home");
		return "template/layout";
	}
	
}
