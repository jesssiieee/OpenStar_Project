package com.openstar.check.bo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.openstar.check.domain.Check;
import com.openstar.check.mapper.CheckMapper;
import com.openstar.movie.Entity.MovieTrend;
import com.openstar.movie.Entity.TvTrend;
import com.openstar.movie.bo.MovieTrendBO;
import com.openstar.movie.bo.TvTrendBO;

@Service
public class CheckBO {
	
	@Autowired
	private MovieTrendBO movieTrendBO;
	
	@Autowired
	private TvTrendBO tvTrendBO;
	
	@Autowired
	private CheckMapper checkMapper;
	
	// input: postId, userId output: X
	public void likeToggle(int userId, int contentId, String type) {

		// like가 있으면
		if (checkMapper.selectLikeCountByContentIdOrUserId(userId, contentId, type) > 0) {
			// 삭제
			checkMapper.deleteLikeByContentIdUserId(userId, contentId, type);
		}
		// 없으면
		else {
			// 추가
			checkMapper.addLikeByContentIdUserId(userId, contentId, type);
		}

	}
	
	public boolean getCountByContentIdUserIdType(Integer userId, int contentId, String type) {
		// 비로그인이면 무조건 빈 하트 => false
		if (userId == null) {
			return false;
		}
		// 로그인	 -  Count값이 0보다 크면(==1이면) 채운 하트, 그렇지 않으면 false
		return checkMapper.selectCountByContentIdUserIdType(userId, contentId, type) > 0; // <- true
		
	}
	
	public void bookmarkToggle(int userId, int contentId, String type) {
		
		if (checkMapper.selectBookMarkCountByContentIdOrUserId(userId, contentId, type) > 0) {
			// 삭제
			checkMapper.deleteBookMarkByContentIdUserId(userId, contentId, type);
		}
		// 없으면
		else {
			// 추가
			checkMapper.addBookMarkByContentIdUserId(userId, contentId, type);
		}
		
	}
	
	public List<Check> getCheckByUserId(int userId) {
		return checkMapper.selectCheckByUserId(userId);
	}
	
	
	public void processCheckList(int userId, String targetType, Model model) throws UnsupportedEncodingException, IOException {
	    List<Check> checkList = checkMapper.selectCheckByUserId(userId);
	    List<MovieTrend> movieTrendResultList = new ArrayList<>();
	    List<TvTrend> tvTrendResultList = new ArrayList<>();

	    for (Check check : checkList) {
	        String type = check.getType();
	        if (type.equals(targetType)) {
	            int checkId = check.getContentId();
	            try {
	                TvTrend tvTrendResult = tvTrendBO.parseTvTrendJson(checkId);
	                if (tvTrendResult != null) {
	                    tvTrendResultList.add(tvTrendResult);
	                } else {
	                    MovieTrend movieTrendResult = movieTrendBO.parseMovieTrendJson(checkId);
	                    if (movieTrendResult != null) {
	                        movieTrendResultList.add(movieTrendResult);
	                    }
	                }
	            } catch (FileNotFoundException e) {
	                MovieTrend movieTrendResult = movieTrendBO.parseMovieTrendJson(checkId);
	                if (movieTrendResult != null) {
	                    movieTrendResultList.add(movieTrendResult);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        } 
	    }

	    model.addAttribute("checkList", checkList);
	    model.addAttribute("tvTrendResultList", tvTrendResultList);
	    model.addAttribute("movieTrendResultList", movieTrendResultList);
	    if (targetType.equals("bookmark")) {
	        model.addAttribute("viewName", "post/bookMarkList");
	    } else if (targetType.equals("like")) {
	        model.addAttribute("viewName", "post/likeList");
	    }
	}

	

}
