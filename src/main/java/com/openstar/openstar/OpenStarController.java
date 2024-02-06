package com.openstar.openstar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openstar.movie.Entity.MoviesTrendEntity;
import com.openstar.movie.Entity.PersonResult;
import com.openstar.movie.Entity.TvTrendEntity;
import com.openstar.movie.repository.MovieRepository;
import com.openstar.movie.repository.TvRepository;

@Controller
@RequestMapping("/openstar")
public class OpenStarController {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private TvRepository tvRepository;

	@GetMapping("/first-view")
	// url: http://localhost/openstar/first-view
	public String firstView(Model model) {
		model.addAttribute("viewName", "openstar/firstView");
		return "template/layout";
	}
	
	@GetMapping("/home-list-view")
	// url: http://localhost/openstar/home-list-view
	public String homeListView(Model model) {
		
		// DB 조회 영화
		List<MoviesTrendEntity> movieTrendList = movieRepository.findAll();
		
		// DB 조회 tv
		List<TvTrendEntity> tvTrendList = tvRepository.findAll();
		
		model.addAttribute("movieTrendList", movieTrendList);
		model.addAttribute("tvTrendList", tvTrendList);
		model.addAttribute("viewName", "openstar/home");
		return "template/layout";
	}
	
	@GetMapping("/search")
	// url: http://localhost/openstar/search
	public String search(Model model) {
		
		model.addAttribute("viewName", "openstar/search");
		return "template/layout";
	}
	
//	@GetMapping("/search-view")
//	// url: http://localhost/openstar/search-view
//	public String searchView(Model model) {
//		
//		List<PersonResult> personResultList = postSearchInfo();
//		
//		model.addAttribute("personResults", personResults);
//		model.addAttribute("viewName", "openstar/searchView");
//		return "template/layout";
//	}
	
	
	
}
