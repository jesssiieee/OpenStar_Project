package com.openstar.post;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openstar.movie.Entity.PersonResult;
import com.openstar.movie.bo.PersonBO;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	private PersonBO personBO;


	// url : http://localhost/post/post-search
	@ResponseBody
	@GetMapping("/post-search/{searchActorName}")
	public List<PersonResult> postSearch(@PathVariable(name = "searchActorName") String searchActorName)
			throws UnsupportedEncodingException, IOException {

		List<PersonResult> parsePersonResult = personBO.parseJson(searchActorName);

        if (parsePersonResult != null) { // parsePersonResult가 null이 아닌 경우 = 성공
            // 성공을 나타내는 코드와 메시지를 반환할 필요가 없으므로 따로 처리하지 않음
        } else { // parsePersonResult가 null인 경우 = 실패
            // 실패를 나타내는 코드와 메시지를 반환할 필요가 없으므로 따로 처리하지 않음
        }

		return parsePersonResult;

	}

}
