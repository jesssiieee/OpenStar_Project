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

import com.openstar.movie.Entity.MoviesTrendEntity;
import com.openstar.movie.bo.MovieTrendBO;
import com.openstar.movie.bo.TvTrendBO;
import com.openstar.movie.repository.MovieRepository;

@RestController
public class MovieRestController {
	
	@Autowired
	private MovieTrendBO movieTrendBO;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private TvTrendBO tvtrendBO;
	
	
	public static final String KEY = "b250b43bc815002de64903f4433d25bd";
	
	@GetMapping("/api/trendMovies/{movieId}")
	public MoviesTrendEntity getMovieById(@PathVariable(name="movieId") int movieId) {
		MoviesTrendEntity movie = movieRepository.findByMovieId(movieId);
		return movie;
	}
	
	@GetMapping("/api/trendMovies")
	public List<MoviesTrendEntity> getAllMovies(Model model) {
        return movieRepository.findAll();
    }
	

	@ResponseBody
	@GetMapping("/api/trendMoviesGetInfo")
	// url: http:localhost/api/trendMoviesGetInfo
	public String trendinMoviesGetInfo() throws IOException {
		int pages = 1;
		String result = "";
		
		for(int i = 1; i <= 10; i++) { 
			String apiURL = "https://api.themoviedb.org/3/trending/movie/week?api_key=" + 
					KEY + "&language=ko&sort_by=vote_average.desc&page=" + i;
			
			try {
				URL url = new URL(apiURL);
				
				BufferedReader bf;
				bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
				
				result = bf.readLine();
				
				movieTrendBO.getInfo(result);
				
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	@ResponseBody
	@GetMapping("/api/trendTvGetInfo")
	// url: http:localhost/api/trendTvGetInfo
	public String trendTvGetInfo() throws IOException {
		int pages = 1;
		String result = "";
		
		for(int i = 1; i <= 10; i++) { 
			String apiURL = "https://api.themoviedb.org/3/trending/tv/week?api_key=" + 
					KEY + "&language=ko&sort_by=vote_average.desc&page=" + i;
			
			try {
				URL url = new URL(apiURL);
				
				BufferedReader bf;
				bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
				
				result = bf.readLine();
				
				tvtrendBO.getInfo(result);
				
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	
}
