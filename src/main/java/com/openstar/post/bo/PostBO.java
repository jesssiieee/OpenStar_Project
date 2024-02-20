package com.openstar.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.openstar.common.FileManagerService;
import com.openstar.post.domain.Post;
import com.openstar.post.domain.Review;
import com.openstar.post.mapper.PostMapper;

@Service
public class PostBO {
	
	@Autowired
	private PostMapper postmapper;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	// communtiy
	
	public void addPost(int userId, String userName, int postId, String userLoginId, String content, MultipartFile file) {
		
		String imagePath = null;
		
		// 업로드 할 이미지가 있을 때 업로드
		if (file != null) {
			imagePath = fileManagerService.saveFile(userLoginId, file);
		}
		
		postmapper.insertPost(userId, userName, postId, content, imagePath);
		
	}
	
	public List<Post> getPostByPostId(int postId) {

		return postmapper.getPostByPostId(postId);
		
	}
	
	public Post getPostDetailByPostId(int id) {
		
		return postmapper.selectPostDetailByPostid(id);
		
	}
	
	// review
	
//	postBO.addReview(userId, userLoginId, content, rating, file);
	
	public void addReview(int userId, String userName, String content, double rating, MultipartFile file) {
		
		String imagePath = null;
		
		// 업로드 할 이미지가 있을 때 업로드
		if (file != null) {
			imagePath = fileManagerService.saveFile(userName, file);
		}
		
		postmapper.insertReview(userId, userName, content, rating, imagePath);
		
	}
	
	public List<Review> getReviewById() {
		return postmapper.selectReviewById();
	}
	
	// mypage에 로그인 한 사람이 작성한 글 목록 뿌리기
	public List<Post> getPostByUserId (int userId) {
		return postmapper.selectPostByUserId(userId);
	}
	
	public List<Review> getReviewByUserId(int userId) {
		return postmapper.selectReviewByUserId(userId);
	}
	
	public void deletePost(int postId) {
		postmapper.deletePostByPostId(postId);
	}
	
	public void deleteReview(int reviewId) {
		postmapper.deleteReviewByReviewId(reviewId);
	}

}
