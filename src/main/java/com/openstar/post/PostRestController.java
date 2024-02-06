package com.openstar.post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openstar.movie.Entity.KnownFor;
import com.openstar.movie.Entity.PersonResult;
import com.openstar.movie.bo.PersonBO;

@Controller
@RequestMapping("/post")

public class PostRestController {

	@Autowired
	private PersonBO personBO;

	public static final String KEY = "b250b43bc815002de64903f4433d25bd";

	// url : http://localhost/post/post-search
	@ResponseBody
	@GetMapping("/post-search/{searchActorName}")
	public List<PersonResult> postSearchInfo(@PathVariable(name = "searchActorName") String searchActorName, Model model)
			throws IOException {

		String result = "";
		List<PersonResult> personResults = null;

		String apiURL = "https://api.themoviedb.org/3/search/person?api_key=" + KEY + "&query=" + searchActorName;

		try {
			URL url = new URL(apiURL);

			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			result = bf.readLine();

			personResults = personBO.parseJson(result);

//			model.addAttribute("personResults", personResults);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return personResults;
		
	}
	

}
