package com.openstar.check.domain;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Check {

	private int userId;
	private int contentId;
	private String type;
	private Date createdAt;
	
}
