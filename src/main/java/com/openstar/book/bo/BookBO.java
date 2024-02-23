package com.openstar.book.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openstar.book.domain.Book;
import com.openstar.book.mapper.BookMapper;

@Service
public class BookBO {
	
	@Autowired
	private BookMapper bookMapper;
	
	// bookBO.addContent(userId, movieId, location, date, title, showtime, adult, child, headcount, selectedSeats, price);
	public void addContent(int userId, int movieId, String location, String date, String title, String showtime,
			int adult, int child, int headcount, String selectedSeats, int price) {
		bookMapper.insertContent(userId, movieId, location, date, title, showtime, adult, child, headcount, selectedSeats, price);
	}
	
	public List<Book> selectBookListByUserId(int userId) {
		return bookMapper.getBookListByUserId(userId);
	}

}
