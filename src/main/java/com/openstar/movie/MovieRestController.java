package com.openstar.movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openstar.movie.bo.ApiService;

@RestController
public class MovieRestController {
	
	@Autowired
	private ApiService apiService;
	
	public static final String KEY = "b250b43bc815002de64903f4433d25bd";

	@ResponseBody
	// url: http:localhost/api/trendinMoviesGetInfo
	@GetMapping("/api/trendinMoviesGetInfo")
	public String trendinMoviesGetInfo() throws IOException {
		int pages = 1;
		String result = "";
		
		for(int i = 1; i <= 3; i++) { // 20개
			String apiURL = "https://api.themoviedb.org/3/discover/movie?api_key=" + 
					KEY + "&sort_by=release_date.desc&language=ko&page=" + i;
			
			try {
				URL url = new URL(apiURL);
				
				BufferedReader bf;
				bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
				
				result = bf.readLine();
				
				apiService.getInfo(result);
				
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		
		return result;
	}
	
}
