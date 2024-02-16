package com.openstar.post;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openstar.movie.Entity.MultiEntity;
import com.openstar.movie.Entity.PersonResult;
import com.openstar.movie.bo.MultiBO;
import com.openstar.movie.bo.PersonBO;
import com.openstar.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	private PersonBO personBO;
	
	@Autowired
	private MultiBO multiBO;
	
	@Autowired
	private PostBO postBO;

	// url : http://localhost/post/post-search
	// 배우 이름 검색 -> 필모그래피
	@ResponseBody
	@GetMapping("/post-search/{searchKeyword}")
    public List<PersonResult> postSearch(@PathVariable(name = "searchKeyword") String searchKeyword)
            throws UnsupportedEncodingException, IOException {
		
		List<PersonResult> parsePersonResult = personBO.parseJson(searchKeyword);

        if (parsePersonResult != null) { // parsePersonResult가 null이 아닌 경우 = 성공
            // 성공을 나타내는 코드와 메시지를 반환할 필요가 없으므로 따로 처리하지 않음
        } else { // parsePersonResult가 null인 경우 = 실패
            // 실패를 나타내는 코드와 메시지를 반환할 필요가 없으므로 따로 처리하지 않음
        }
        
        return parsePersonResult;

	}
	
	@ResponseBody
	@GetMapping("/post-search-all/{searchKeyword}")
	// 필모그래피 -> 해당 작품 상세 점보
    public List<MultiEntity> postSearchAll(@PathVariable(name = "searchKeyword") String searchKeyword)
            throws UnsupportedEncodingException, IOException {
		
		List<MultiEntity> parseMultiResult = multiBO.parseJsonMulti(searchKeyword);

        if (parseMultiResult != null) { // parsePersonResult가 null이 아닌 경우 = 성공
            // 성공을 나타내는 코드와 메시지를 반환할 필요가 없으므로 따로 처리하지 않음
        } else { // parsePersonResult가 null인 경우 = 실패
            // 실패를 나타내는 코드와 메시지를 반환할 필요가 없으므로 따로 처리하지 않음
        }
        
        return parseMultiResult;

	}
	
	@PostMapping("/create")
	public Map<String, Object> create( 
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpSession session ) {
		
		// 글쓴이 번호 - session에 있는 userId를 꺼낸다. (로그인한 사용자)
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		// DB insert
		postBO.addPost(userId, userLoginId, postId, userLoginId, content, file);
		
		// 응답값 
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
	@PostMapping("/create-review")
	public Map<String, Object> createReview(
			@RequestParam("rating") double rating,
			@RequestParam("content") String content,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpSession session) {
		
		// 글쓴이 번호 - session에 있는 userId를 꺼낸다. (로그인한 사용자)
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		postBO.addReview(userId, userLoginId, content, rating, file);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result; 
	}

}
