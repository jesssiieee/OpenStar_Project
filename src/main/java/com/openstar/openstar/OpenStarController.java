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

import com.openstar.book.bo.BookBO;
import com.openstar.book.domain.Book;
import com.openstar.check.bo.CheckBO;
import com.openstar.check.mapper.CheckMapper;
import com.openstar.movie.Entity.MovieTrend;
import com.openstar.movie.Entity.MoviesTrendEntity;
import com.openstar.movie.Entity.MultiEntity;
import com.openstar.movie.Entity.PersonResult;
import com.openstar.movie.Entity.TvTrend;
import com.openstar.movie.Entity.TvTrendEntity;
import com.openstar.movie.bo.MovieTrendBO;
import com.openstar.movie.bo.MultiBO;
import com.openstar.movie.bo.PersonBO;
import com.openstar.movie.bo.TvTrendBO;
import com.openstar.movie.repository.MovieRepository;
import com.openstar.movie.repository.TvRepository;
import com.openstar.post.PostRestController;
import com.openstar.post.bo.PostBO;
import com.openstar.post.domain.Post;
import com.openstar.post.domain.Review;

import jakarta.servlet.http.HttpSession;

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
	private MultiBO multiBO;
	
	@Autowired
	private CheckBO checkBO;
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CheckMapper checkMapper;

	@Autowired
	private PostRestController postRestController;
	
	@Autowired
	private MovieTrendBO movieTrendBO;
	
	@Autowired
	private TvTrendBO trTrendBO;
	
	@Autowired
	private BookBO bookBO;
	
	
	@GetMapping("/first-view")
	// url: http://localhost/openstar/first-view
	public String firstView(Model model) {
		model.addAttribute("viewName", "openstar/firstView");
		return "template/layout";
	}

	@GetMapping("/home-list-view")
	// url: http://localhost/openstar/home-list-view
	public String homeListView(Model model) throws UnsupportedEncodingException, IOException {

		// DB 조회 영화
//		List<MoviesTrendEntity> movieTrendList = movieRepository.findAll();
//
//		// DB 조회 tv
//		List<TvTrendEntity> tvTrendList = tvRepository.findAll();
//
//		model.addAttribute("movieTrendList", movieTrendList);
//		model.addAttribute("tvTrendList", tvTrendList);
		
		List<MoviesTrendEntity> resultHomeMovieTrendList = movieTrendBO.parseHomeMovieTrendJson();
		List<TvTrendEntity> resultHomeTvTrendList = trTrendBO.parseHomeTvTrendJson();
		model.addAttribute("resultHomeMovieTrendList", resultHomeMovieTrendList);
		model.addAttribute("resultHomeTvTrendList", resultHomeTvTrendList);
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
			@PathVariable(name = "searchKeyword") String searchKeyword, 
			HttpSession session,
			Model model)
			throws UnsupportedEncodingException, IOException {
		
		int id = 0;
		Integer userId = (Integer) session.getAttribute("userId");

		List<MultiEntity> multiResultList = null;
		List<PersonResult> personResultList = null;
		boolean isLiked = false;
		boolean isBookMarked = false;
		MultiEntity multi = null;
		int contentId = 0;

		if (searchKeyword.length() > 3) {
			multiResultList = (List<MultiEntity>) multiBO.parseJsonMulti(searchKeyword);
			multi = multiResultList.get(0);
			contentId = multi.getId();
		} else {
			personResultList = (List<PersonResult>) personBO.parseJson(searchKeyword);
		}
		 
		
		isLiked = checkBO.getCountByContentIdUserIdType(userId, contentId, "like");
		isBookMarked = checkBO.getCountByContentIdUserIdType(userId, contentId, "bookmark");
		
		model.addAttribute("personResultList", personResultList);
		model.addAttribute("multiResultList", multiResultList);
		model.addAttribute("isLiked", isLiked);
		model.addAttribute("isBookMarked", isBookMarked);
		model.addAttribute("viewName", "openstar/searchView");
		return "template/layout";
	}

	@GetMapping("/search-view/detail/{searchKeyword}")
//	 url: http://localhost/openstar/search-view/detail
	public String detailSearchView(
			@PathVariable(name = "searchKeyword") String searchKeyword,
			@RequestParam(name = "contentId", required = false) String contentId, 
			Model model)
			throws UnsupportedEncodingException, IOException {
		
		List<PersonResult> personResultList = personBO.parseJson(searchKeyword);
//		List<MultiEntity> multiResultList = postRestController.postSearchAll(searchKeyword);
		model.addAttribute("personResultList", personResultList);
//		model.addAttribute("multiResultList", multiResultList);
		model.addAttribute("contentId", contentId);
		model.addAttribute("viewName", "openstar/detailContentsView");
		return "template/layout";
	}
	
	@GetMapping("/search-view/trend/{movieId}")
	public String trendMovieSearchView(
			@PathVariable(name = "movieId") int movieId,
			HttpSession session
			,Model model) throws UnsupportedEncodingException, IOException {
		
		MovieTrend movieTrendResultList = movieTrendBO.parseMovieTrendJson(movieId);
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		boolean isLiked = checkBO.getCountByContentIdUserIdType(userId, movieId, "like");
		boolean isBookMarked = checkBO.getCountByContentIdUserIdType(userId, movieId, "bookmark");
		
		model.addAttribute("isLiked", isLiked);
		model.addAttribute("isBookMarked", isBookMarked);
		model.addAttribute("movieTrendResultList", movieTrendResultList);
		model.addAttribute("viewName", "openstar/searchTrendMovieView");
		return "template/layout";
	}
	
	@GetMapping("/search-view/trendTv/{tvId}")
	public String trendTvSearchView(
			@PathVariable(name = "tvId") int tvId,
			HttpSession session
			,Model model) throws UnsupportedEncodingException, IOException {
		
		TvTrend tvTrendResultList = trTrendBO.parseTvTrendJson(tvId);
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		boolean isLiked = checkBO.getCountByContentIdUserIdType(userId, tvId, "like");
		boolean isBookMarked = checkBO.getCountByContentIdUserIdType(userId, tvId, "bookmark");
		
		model.addAttribute("isLiked", isLiked);
		model.addAttribute("isBookMarked", isBookMarked);
		
		model.addAttribute("tvTrendResultList", tvTrendResultList);
		model.addAttribute("viewName", "openstar/searchTrendTvView");
		return "template/layout";
	}
	
	@GetMapping("/mypage-view")
	public String myPageViewPost(
	        HttpSession session,
	        Model model) throws UnsupportedEncodingException, IOException {
	    
	    Integer userId = (Integer) session.getAttribute("userId");
	    
	    if (userId == null) {
	         return "redirect:/user/sign-in-view";
	    }
	    
	    String userLoginId = (String) session.getAttribute("userLoginId");
	    String userName = (String) session.getAttribute("userName");

	    List<Post> postList = postBO.getPostByUserId(userId);
	    List<Review> reviewList = postBO.getReviewByUserId(userId); 
	    
	    model.addAttribute("postList", postList);
	    model.addAttribute("reviewList", reviewList);
	    model.addAttribute("viewName", "openstar/myPage");
	    return "template/layout";
	}
	
	@GetMapping("/check-Book")
	public String checkBook(HttpSession session, Model model) {
	    Integer userId = (Integer) session.getAttribute("userId");
	    
	    if (userId == null) {
	         return "redirect:/user/sign-in-view";
	    }
	    
	    // db select
	    List<Book> checkBookList = bookBO.selectBookListByUserId(userId);
	    
	    model.addAttribute("checkBookList", checkBookList);
	    model.addAttribute("viewName", "openstar/checkBook");
	    return "template/layout";
	}


}
