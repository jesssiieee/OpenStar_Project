package com.openstar.movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openstar.movie.Entity.MovieEntity;
import com.openstar.movie.bo.ApiService;
import com.openstar.movie.repository.MovieRepository;

@RestController
public class MovieRestController {
	
	@Autowired
	private ApiService apiService;
	
	@Autowired
	private MovieRepository movieRepository;
	
	public static final String KEY = "b250b43bc815002de64903f4433d25bd";
	
	@GetMapping("/api/trendMovies/{movieId}")
	public MovieEntity getMovieById(@PathVariable(name="movieId") int movieId) {
		 MovieEntity movie = movieRepository.findByMovieId(movieId);
		return movie;
	}
	
	@GetMapping("/api/trendMovies")
	public List<MovieEntity> getAllMovies(Model model) {
        return movieRepository.findAll();
    }
	

	@ResponseBody
	@GetMapping("/api/trendMoviesGetInfo")
	// url: http:localhost/api/trendMoviesGetInfo
	public String trendinMoviesGetInfo() throws IOException {
		int pages = 1;
		String result = "";
		
		for(int i = 1; i <= 10; i++) { 
			String apiURL = "https://api.themoviedb.org/3/discover/movie?api_key=" + 
					KEY + "&language=ko&page=" + i;
			
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
