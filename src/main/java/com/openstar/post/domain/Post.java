package com.openstar.post.domain;



import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Post {

	private int id;
	private int userId;
	private String content;
	private String imagePath;
	private Date createdAt;
	private Date updatedAt;
	
}
