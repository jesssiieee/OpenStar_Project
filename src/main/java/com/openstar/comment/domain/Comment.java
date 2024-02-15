package com.openstar.comment.domain;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Comment {
	
	private int id;
	private int detailId;
	private int postId;
	private int userId;
	private String userName;
	private String content;
	private Date createdAt;
	private Date updatedAt;

}
