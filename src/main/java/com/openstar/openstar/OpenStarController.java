package com.openstar.openstar;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openstar.movie.Entity.MoviesTrendEntity;
import com.openstar.movie.Entity.MultiEntity;
import com.openstar.movie.Entity.PersonResult;
import com.openstar.movie.Entity.TvTrendEntity;
import com.openstar.movie.bo.PersonBO;
import com.openstar.movie.repository.MovieRepository;
import com.openstar.movie.repository.TvRepository;
import com.openstar.post.PostRestController;

@Controller
@RequestMapping("/openstar")
public class OpenStarController {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TvRepository tvRepository;

	@Autowired
	private PersonBO personBO;

	@Autowired
	private PostRestController postRestController;

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

	@GetMapping("/search-view/{searchKeyword}")
//	 url: http://localhost/openstar/search-view
	public String searchView(
			@PathVariable(name = "searchKeyword") String searchKeyword
			,Model model) throws UnsupportedEncodingException, IOException {
		
		List<MultiEntity> multiResultList = null;
		List<PersonResult> personResultList = null;
		
		if (searchKeyword.length() > 3) {
			multiResultList = (List<MultiEntity>) postRestController.postSearchAll(searchKeyword);
		} else {
			personResultList = (List<PersonResult>) postRestController.postSearch(searchKeyword);
		}
		
		
		model.addAttribute("personResultList", personResultList);
		model.addAttribute("multiResultList", multiResultList);
		model.addAttribute("viewName", "openstar/searchView");
		return "template/layout";
	}
	
	@GetMapping("/search-view/detail/{searchKeyword}")
//	 url: http://localhost/openstar/search-view/detail
	public String detailSearchView(
			@PathVariable(name = "searchKeyword") String searchKeyword
			,@RequestParam(name = "contentId", required = false) String contentId
			,Model model) throws UnsupportedEncodingException, IOException {
		List<PersonResult> personResultList = (List<PersonResult>) postRestController.postSearch(searchKeyword);
		List<MultiEntity> multiResultList = postRestController.postSearchAll(searchKeyword);
		model.addAttribute("personResultList", personResultList);
		model.addAttribute("multiResultList", multiResultList);
	    model.addAttribute("contentId", contentId);
		model.addAttribute("viewName", "openstar/detailContentsView");
		return "template/layout";
	}

}
