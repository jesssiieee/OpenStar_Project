package com.openstar.book.domain;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Book {
	
	private int id;
	private int userId;
	private int movieId;
	private String location;
	private String date;
	private String title;
	private String showtime;
	private int headcount;
	private String selectedseats;
	private int price;
	private Date createdAt;
	private Date updatedAt;

}
