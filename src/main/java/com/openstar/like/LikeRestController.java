package com.openstar.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openstar.like.bo.LikeBO;

@Controller
public class LikeRestController {
	
	@Autowired
	private LikeBO likeBO;

}
