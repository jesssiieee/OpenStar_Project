package com.openstar.book.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.openstar.book.domain.Book;

@Mapper
public interface BookMapper {
	
	public void insertContent(
			@Param("userId")int userId, 
			@Param("movieId")int movieId, 
			@Param("location")String location, 
			@Param("date")String date, 
			@Param("title")String title, 
			@Param("showtime")String showtime,
			@Param("adult")int adult, 
			@Param("child")int child, 
			@Param("headcount")int headcount, 
			@Param("selectedSeats")String selectedSeats, 
			@Param("price")int price
	);
	
	public List<Book> getBookListByUserId(int userId);
	

}
