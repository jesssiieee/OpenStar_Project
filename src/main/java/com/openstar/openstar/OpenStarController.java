package com.openstar.openstar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/openstar")
public class OpenStarController {

	@GetMapping("/first-view")
	// url: http://localhost/openstar/first-view
	public String firstView(Model model) {
		model.addAttribute("viewName", "openstar/firstView");
		return "template/layout";
	}
	
}
