package com.openstar.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
	
//	@Autowired
//	private PostMapper postMapper;

	@ResponseBody
	@GetMapping("/test1")
	public String test1() {
		return "Hello World!";
	}
	
	@GetMapping("/test2")
	@ResponseBody
	public Map<String, Object> test2() {
		Map<String, Object> result = new HashMap<>();
		result.put("aaa", 111);
		result.put("bbb", 111);
		result.put("ccc", 222);
		return result; // json
	}
	
	@GetMapping("/test3")
	public String test3() {
		return "test/test";
	}
	
//	@ResponseBody
//	@GetMapping("/test4")
//	// url : http://localhost/test4
//	public List<Map<String, Object>> test4() {
//		
//		return postMapper.selectPostList();
//		
//	}
	

	
}
