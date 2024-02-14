package com.openstar.post;

import java.io.FileNotFoundException;
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

import com.openstar.movie.Entity.MovieTrend;
import com.openstar.movie.Entity.TvTrend;
import com.openstar.movie.bo.MovieTrendBO;
import com.openstar.movie.bo.MultiBO;
import com.openstar.movie.bo.PersonBO;
import com.openstar.movie.bo.TvTrendBO;
import com.openstar.movie.repository.MovieRepository;
import com.openstar.movie.repository.TvRepository;
import com.openstar.post.bo.PostBO;
import com.openstar.post.domain.Post;

import jakarta.servlet.http.HttpSession;

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
	private PostBO postBO;

	@Autowired
	private PostRestController postRestController;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TvRepository tvRepository;

	@GetMapping("/post-write/{writeId}")
	// url: http://localhost/post/post-write
	public String createView(@PathVariable(name = "writeId") int writeId, Model model) {

		model.addAttribute("writeId", writeId);
		model.addAttribute("viewName", "post/write");
		return "template/layout";
	}

	@GetMapping("/post-community-view/{searchId}")
	// url: http://localhost/post/post-community-view
	public String postCommunityView(@PathVariable(name = "searchId") int searchId, HttpSession session, Model model)
			throws UnsupportedEncodingException, IOException {

		MovieTrend movieTrendResultList = null;
		TvTrend tvTrendResultList = null;

		try {
			movieTrendResultList = movieTrendBO.parseMovieTrendJson(searchId);
		} catch (FileNotFoundException e) {
			// 만약 NotFoundException이 발생하면 TvTrendBO로 분기
			tvTrendResultList = tvTrendBO.parseTvTrendJson(searchId);
		}

		// movieTrendResultList가 null이 아니면 movieTrendResultList를 사용, 그렇지 않으면
		// tvTrendResultList를 사용
		if (movieTrendResultList != null) {
			// movieTrendResultList 사용
			model.addAttribute("movieTrendResultList", movieTrendResultList);
		} else if (tvTrendResultList != null) {
			// tvTrendResultList 사용
			model.addAttribute("tvTrendResultList", tvTrendResultList);
		}

//		Integer userId = (Integer)session.getAttribute("userId");
//		if (userId == null) {
//			// 비로그인이면, 로그인 페이지로 이동.
//			return "redirect:/user/sign-in-view";
//		}

		// db selectselectAll
		List<Post> getCommunityList = (List<Post>) postBO.getPostByPostId(searchId);

		model.addAttribute("getCommunityList", getCommunityList);
		model.addAttribute("viewName", "post/communityList");
		return "template/layout";

	}

	@GetMapping("/post-community-detailView/{detailId}")
	// url: http://localhost/post/post-community-detailView
	public String postCommunitydetailView(
			@PathVariable(name = "detailId") int detailId, 
			@RequestParam(name = "postId", required = false) int postId, 
			HttpSession session,
			Model model) {

		Post getCommunityPost = postBO.getPostDetailByPostId(postId);

		model.addAttribute("getCommunityList", getCommunityPost);
		model.addAttribute("viewName", "post/communityDetail");
		return "template/layout";
	}

	@GetMapping("/post-review-view")
	// url: http://localhost/post/post-review-view
	public String postReviewView(Model model) {
		model.addAttribute("viewName", "post/reviewList");
		return "template/layout";
	}

}
