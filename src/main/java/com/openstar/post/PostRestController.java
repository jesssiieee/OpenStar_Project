package com.openstar.post;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openstar.movie.Entity.MultiEntity;
import com.openstar.movie.Entity.PersonResult;
import com.openstar.movie.bo.MultiBO;
import com.openstar.movie.bo.PersonBO;

@Controller
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	private PersonBO personBO;
	
	@Autowired
	private MultiBO multiBO;

	// url : http://localhost/post/post-search
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

}
