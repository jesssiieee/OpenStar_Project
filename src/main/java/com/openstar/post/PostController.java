package com.openstar.post;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openstar.movie.Entity.MovieTrend;
import com.openstar.movie.Entity.TvTrend;
import com.openstar.movie.bo.MovieTrendBO;
import com.openstar.movie.bo.MultiBO;
import com.openstar.movie.bo.PersonBO;
import com.openstar.movie.bo.TvTrendBO;
import com.openstar.movie.repository.MovieRepository;
import com.openstar.movie.repository.TvRepository;


@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private MovieTrendBO movieTrendBO;
	
	@Autowired
	private MultiBO multiBO;
	
	@Autowired
	private PersonBO personBO;
	
	@Autowired
	private TvTrendBO tvTrendBO;
	
	@Autowired
	private PostRestController postRestController;
	
	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TvRepository tvRepository;
	
	@GetMapping("/post-write")
	// url: http://localhost/post/post-write
	public String createView(Model model) {
		model.addAttribute("viewName", "post/write");
		return "template/layout";
	}
	
	@GetMapping("/post-community-view/{searchId}")
	// url: http://localhost/post/post-community-view
	public String postCommunityView(
			@PathVariable(name = "searchId") int searchId,
			Model model) throws UnsupportedEncodingException, IOException {
		
		MovieTrend movieTrendResultList = null;
		TvTrend tvTrendResultList = null;

		try {
		    movieTrendResultList = movieTrendBO.parseMovieTrendJson(searchId);
		} catch (FileNotFoundException e) {
		    // 만약 NotFoundException이 발생하면 TvTrendBO로 분기
		    tvTrendResultList = tvTrendBO.parseTvTrendJson(searchId);
		}

		// movieTrendResultList가 null이 아니면 movieTrendResultList를 사용, 그렇지 않으면 tvTrendResultList를 사용
		if (movieTrendResultList != null) {
		    // movieTrendResultList 사용
			model.addAttribute("movieTrendResultList", movieTrendResultList);
		} else if (tvTrendResultList != null) {
		    // tvTrendResultList 사용
			model.addAttribute("tvTrendResultList", tvTrendResultList);
		}

		
		model.addAttribute("viewName", "post/communityList");
		return "template/layout";

	}
	
	@GetMapping("/post-review-view")
	// url: http://localhost/post/post-review-view
	public String postReviewView(Model model) {
		model.addAttribute("viewName", "post/reviewList");
		return "template/layout";
	}
	

}
