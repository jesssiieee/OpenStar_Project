package com.openstar.post.domain;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Review {
	
	private int id;
	private int userId;
	private String userName;
	private String subject;
	private String content;
	private double rating;
	private String imagePath;
	private Date createdAt;
	private Date updatedAt;
	
}
