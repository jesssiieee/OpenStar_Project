package com.openstar.post;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openstar.check.bo.CheckBO;
import com.openstar.check.domain.Check;
import com.openstar.comment.bo.CommentBO;
import com.openstar.comment.domain.Comment;
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
import com.openstar.post.domain.Review;

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
	private CommentBO commentBO;

	@Autowired
	private CheckBO checkBO;

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

	@GetMapping("/post-write-review")
	// url: http://localhost/post/post-write-review
	public String createView(Model model) {

		model.addAttribute("viewName", "post/writeReview");
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
	public String postCommunitydetailView(@PathVariable(name = "detailId") int detailId,
			@RequestParam(name = "postId", required = false) int postId, HttpSession session, Model model) {

		int userId = (int) session.getAttribute("userId");
		String userLoginId = (String) session.getAttribute("userLoginId");

		Post getCommunityPost = postBO.getPostDetailByPostId(postId);
		List<Comment> getCommentList = commentBO.getComment(detailId, postId);

		model.addAttribute("getCommunityPost", getCommunityPost);
		model.addAttribute("getCommentList", getCommentList);

		model.addAttribute("viewName", "post/communityDetail");
		return "template/layout";
	}

	@GetMapping("/post-review-view")
	// url: http://localhost/post/post-review-view
	public String postReviewView(Model model) {

		// db select by id(pk)
		List<Review> reviewList = postBO.getReviewById();

		model.addAttribute("reviewList", reviewList);
		model.addAttribute("viewName", "post/reviewList");
		return "template/layout";
	}
	
	@GetMapping("/post-review-detail/{reviewId}")
	public String postReviewDetail(
			@PathVariable(name = "reviewId") int reviewId,
			HttpSession session,
			Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			return "redirect:/user/sign-in-view";
		}
		
		Review reviewList = postBO.getReviewById(reviewId);
		
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("viewName", "post/writeReviewDetail");
		return "template/layout";
	}

	@GetMapping("/post-bookmark-list")
	// url: http://localhost/post/post-bookmark-list
	public String postBookMarkList(HttpSession session, Model model) throws UnsupportedEncodingException, IOException {

		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			return "redirect:/user/sign-in-view";
		}

		MovieTrend movieTrendResult = null;
		TvTrend tvTrendResult = null;

		List<MovieTrend> movieTrendResultList = new ArrayList<>();
		List<TvTrend> tvTrendResultList = new ArrayList<>();

		List<Check> checkList = (List<Check>) checkBO.getCheckByUserId(userId);

		for (int i = 0; i < checkList.size(); i++) {
		    String type = checkList.get(i).getType();
		    if (type.equals("bookmark")) {
		        int checkId = checkList.get(i).getContentId();
		        try {
		            // tvTrendBO.parseTvTrendJson() 호출
		            tvTrendResult = tvTrendBO.parseTvTrendJson(checkId);
		            if (tvTrendResult != null) {
		                tvTrendResultList.add(tvTrendResult);
		            } else {
		                // tvTrendResult가 null인 경우, movieTrendBO.parseMovieTrendJson() 호출
		                movieTrendResult = movieTrendBO.parseMovieTrendJson(checkId);
		                if (movieTrendResult != null) {
		                    movieTrendResultList.add(movieTrendResult);
		                }
		            }
		        } catch (FileNotFoundException e) {
		            // tvTrendBO.parseTvTrendJson()에서 FileNotFoundException이 발생하는 경우
		            // movieTrendBO.parseMovieTrendJson() 호출
		            movieTrendResult = movieTrendBO.parseMovieTrendJson(checkId);
		            if (movieTrendResult != null) {
		                movieTrendResultList.add(movieTrendResult);
		            }
		        } catch (Exception e) {
		            // 기타 예외에 대한 처리
		            e.printStackTrace(); // 또는 로그에 기록
		        }
		    }
		}

		model.addAttribute("checkList", checkList);
		model.addAttribute("tvTrendResultList", tvTrendResultList);
		model.addAttribute("movieTrendResultList", movieTrendResultList);
		model.addAttribute("viewName", "post/bookMarkList");
		return "template/layout";
	}

	@GetMapping("/post-like-list")
	// url: http://localhost/post/post-like-list
	public String postLikeList(HttpSession session, Model model) throws UnsupportedEncodingException, IOException {

		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			return "redirect:/user/sign-in-view";
		}

		MovieTrend movieTrendResult = null;
		TvTrend tvTrendResult = null;

		List<MovieTrend> movieTrendResultList = new ArrayList<>();
		List<TvTrend> tvTrendResultList = new ArrayList<>();

		List<Check> checkList = (List<Check>) checkBO.getCheckByUserId(userId);

		for (int i = 0; i < checkList.size(); i++) {
		    String type = checkList.get(i).getType();
		    if (type.equals("like")) {
		        int checkId = checkList.get(i).getContentId();
		        try {
		            // tvTrendBO.parseTvTrendJson() 호출
		            tvTrendResult = tvTrendBO.parseTvTrendJson(checkId);
		            if (tvTrendResult != null) {
		                tvTrendResultList.add(tvTrendResult);
		            } else {
		                // tvTrendResult가 null인 경우, movieTrendBO.parseMovieTrendJson() 호출
		                movieTrendResult = movieTrendBO.parseMovieTrendJson(checkId);
		                if (movieTrendResult != null) {
		                    movieTrendResultList.add(movieTrendResult);
		                }
		            }
		        } catch (FileNotFoundException e) {
		            // tvTrendBO.parseTvTrendJson()에서 FileNotFoundException이 발생하는 경우
		            // movieTrendBO.parseMovieTrendJson() 호출
		            movieTrendResult = movieTrendBO.parseMovieTrendJson(checkId);
		            if (movieTrendResult != null) {
		                movieTrendResultList.add(movieTrendResult);
		            }
		        } catch (Exception e) {
		            // 기타 예외에 대한 처리
		            e.printStackTrace(); // 또는 로그에 기록
		        }
		    }
		}

		model.addAttribute("checkList", checkList);
		model.addAttribute("tvTrendResultList", tvTrendResultList);
		model.addAttribute("movieTrendResultList", movieTrendResultList);
		model.addAttribute("viewName", "post/likeList");
		return "template/layout";
	}

}
