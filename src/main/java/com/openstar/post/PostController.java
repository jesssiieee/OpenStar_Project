package com.openstar.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/post")
public class PostController {
	
	@GetMapping("/post-write")
	// url: http://localhost/post/post-write
	public String createView(Model model) {
		model.addAttribute("viewName", "post/write");
		return "template/layout";
	}
	
	@GetMapping("/post-community-view")
	// url: http://localhost/post/post-community-view
	public String postCommunityView(Model model) {
		model.addAttribute("viewName", "post/communityList");
		return "template/layout";
	}
	
	@GetMapping("/post-review-view")
	// url: http://localhost/post/post-review-view
	public String postReviewView(Model model) {
		model.addAttribute("viewName", "post/reviewList");
		return "template/layout";
	}
	

}
