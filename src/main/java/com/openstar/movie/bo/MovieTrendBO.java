package com.openstar.movie.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.openstar.movie.Entity.MovieTrend;
import com.openstar.movie.Entity.MoviesTrendEntity;
import com.openstar.movie.Entity.TvTrendEntity;
import com.openstar.movie.repository.MovieRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class MovieTrendBO {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public static final String KEY = "b250b43bc815002de64903f4433d25bd";

    LocalDateTime dateTime = LocalDateTime.now();

    public String getInfo(String result) {

        JsonArray list = null;

        log.info("서비스 시작" );
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
        list = (JsonArray) jsonObject.get("results");

        for (int k = 0; k < list.size(); k++) {
            JsonObject contents = (JsonObject) list.get(k);

            String ImgUrl = "https://image.tmdb.org/t/p/w200";
            String match = "[\"]";

            movieRepository.save(
            		MoviesTrendEntity.builder()
                    .movieId(contents.get("id").getAsInt())
                    .grade(contents.get("vote_average").getAsDouble())
                    .overview(contents.get("overview").getAsString())
                    .posterPath(ImgUrl + contents.get("poster_path").toString().replaceAll(match, ""))
                    .releaseDate(LocalDate.parse(contents.get("release_date").getAsString()))
                    .title(contents.get("title").toString())
                    .build()
            );

        }
        return "ok";
    }
    
    public List<MovieTrend> parseMovieTrendJson(@PathVariable(name = "movieId") int movieId) throws UnsupportedEncodingException, IOException {
    	
    	// movieId select
    	MoviesTrendEntity dbMovieId = movieRepository.findByMovieId(movieId);
    	
    	String result = "";
		String apiURL = "";
        List<MovieTrend> personResults = new ArrayList<>();
        
        apiURL = "https://api.themoviedb.org/3/movie/" + dbMovieId + "?api_key=" + KEY;
        
        String ImgUrl = "https://image.tmdb.org/t/p/w200";
        String match = "[\"]";

		try {
			URL url = new URL(apiURL);
			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			result = bf.readLine();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

        try {
            JsonObject jsonObject = new com.google.gson.JsonParser().parse(result).getAsJsonObject();
            JsonArray results = jsonObject.getAsJsonArray("results");

            for (int i = 0; i < 1; i++) {
                JsonObject personObject = results.get(i).getAsJsonObject();
                MovieTrend personResult = new MovieTrend();

                // Set common properties
                personResult.setId(personObject.getAsJsonPrimitive("id").getAsInt());
                personResult.setOriginalTitle(personObject.getAsJsonPrimitive("original_title").getAsString());
                personResult.setPosterPath(ImgUrl + personObject.getAsJsonPrimitive("poster_path").getAsString().replaceAll(match, ""));

                
                
                personResults.add(personResult);
                
                
            }
        } catch (Exception e) {
            log.error("Error parsing JSON", e);
        }
        
        return personResults;
    }
    	
}
