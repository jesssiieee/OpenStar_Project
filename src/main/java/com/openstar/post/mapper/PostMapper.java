package com.openstar.post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.openstar.post.domain.Post;
import com.openstar.post.domain.Review;

@Mapper
public interface PostMapper {
	
	// community
	
	// input: subject, content output: X
	public void insertPost(
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("postId") int postId,
			@Param("content") String content,
			@Param("imagePath") String imagePath
	);
	
	public List<Post> getPostByPostId(
			@Param("postId") int postId
	);
	
	public Post selectPostDetailByPostid(
			@Param("id") int id
	);
	
	
	// review
	public void insertReview(
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("content") String content,
			@Param("rating") double rating,
			@Param("imagePath") String imagePath
	);
	
	public List<Review> selectReviewById();
	
	public List<Post> selectPostByUserId (int userId);
	
	public List<Review> selectReviewByUserId(int userId);
	
	public void deletePostByPostId(int postId);
	
	public void deleteReviewByReviewId(int reviewId);


}
