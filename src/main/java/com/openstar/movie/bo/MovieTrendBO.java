package com.openstar.movie.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.openstar.movie.Entity.MovieTrend;
import com.openstar.movie.Entity.MoviesTrendEntity;
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

//    public String getInfo(String result) {
//
//        JsonArray list = null;
//
//        log.info("서비스 시작" );
//        JsonParser jsonParser = new JsonParser();
//        JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
//        list = (JsonArray) jsonObject.get("results");
//
//        for (int k = 0; k < list.size(); k++) {
//            JsonObject contents = (JsonObject) list.get(k);
//
//            String ImgUrl = "https://image.tmdb.org/t/p/w200";
//            String match = "[\"]";
//
//            movieRepository.save(
//            		MoviesTrendEntity.builder()
//                    .movieId(contents.get("id").getAsInt())
//                    .grade(contents.get("vote_average").getAsDouble())
//                    .overview(contents.get("overview").getAsString())
//                    .posterPath(ImgUrl + contents.get("poster_path").toString().replaceAll(match, ""))
//                    .releaseDate(LocalDate.parse(contents.get("release_date").getAsString()))
//                    .title(contents.get("title").toString())
//                    .build()
//            );
//
//        }
//        return "ok";
//    }

	public List<MoviesTrendEntity> parseHomeMovieTrendJson() throws UnsupportedEncodingException, IOException {
	    String ImgUrl = "https://image.tmdb.org/t/p/w200";
	    String match = "[\"]";
	    List<MoviesTrendEntity> resultHomeMovieTrendList = new ArrayList<>(); // Initialize as an empty list

	    int maxPages = 10; // Maximum number of pages to fetch
	    int page = 1;

	    while (page <= maxPages) {
	        try {
	            String apiURL = "https://api.themoviedb.org/3/trending/movie/week?api_key=" + KEY
	                    + "&language=ko&sort_by=vote_average.desc&page=" + page;
	            URL url = new URL(apiURL);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");

	            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	            StringBuilder response = new StringBuilder();
	            String line;
	            while ((line = bf.readLine()) != null) {
	                response.append(line);
	            }
	            bf.close();

	            JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
	            JsonArray resultsArray = jsonObject.getAsJsonArray("results");

	            // Check if there are no more pages
	            if (resultsArray.size() == 0) {
	                break;
	            }

	            for (JsonElement element : resultsArray) {
	                JsonObject movieObject = element.getAsJsonObject();
	                MoviesTrendEntity homeMovieTrendList = new MoviesTrendEntity();
	                homeMovieTrendList.setMovieId(movieObject.getAsJsonPrimitive("id").getAsInt());
	                homeMovieTrendList.setTitle(movieObject.getAsJsonPrimitive("title").getAsString());
	                homeMovieTrendList.setOverview(movieObject.getAsJsonPrimitive("overview").getAsString());
	                homeMovieTrendList.setPosterPath(
	                        ImgUrl + movieObject.getAsJsonPrimitive("poster_path").getAsString().replaceAll(match, ""));
	                homeMovieTrendList.setReleaseDate(movieObject.getAsJsonPrimitive("release_date").getAsString());
	                homeMovieTrendList.setVoteAverage(movieObject.getAsJsonPrimitive("vote_average").getAsDouble());

	                resultHomeMovieTrendList.add(homeMovieTrendList);
	            }

	            page++;
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        }
	    }

	    return resultHomeMovieTrendList;
	}



	public MovieTrend parseMovieTrendJson(@PathVariable(name = "movieId") int movieId)
			throws UnsupportedEncodingException, IOException {

		// movieId select
		// MoviesTrendEntity 인스턴스 생성
//		MoviesTrendEntity movieEntity = new MoviesTrendEntity();
		// movieId 가져오기
//		movieId = movieEntity.getMovieId();


		String apiURL = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + KEY;
		String ImgUrl = "https://image.tmdb.org/t/p/w200";
		String match = "[\"]";
		String result = "";

		try {
			URL url = new URL(apiURL);
			BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			result = bf.readLine();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();

		MovieTrend movieTrendResult = new MovieTrend();
		movieTrendResult.setId(jsonObject.getAsJsonPrimitive("id").getAsInt());
		movieTrendResult.setOriginalTitle(jsonObject.getAsJsonPrimitive("original_title").getAsString());
		movieTrendResult.setPosterPath(
				ImgUrl + jsonObject.getAsJsonPrimitive("poster_path").getAsString().replaceAll(match, ""));
		movieTrendResult.setOverview(jsonObject.getAsJsonPrimitive("overview").getAsString());
		movieTrendResult.setReleaseDate(jsonObject.getAsJsonPrimitive("release_date").getAsString());
		movieTrendResult.setPopularity(jsonObject.getAsJsonPrimitive("popularity").getAsDouble());
		movieTrendResult.setVoteAverage(jsonObject.getAsJsonPrimitive("vote_average").getAsDouble());
		movieTrendResult.setRevenue(jsonObject.getAsJsonPrimitive("revenue").getAsInt());
		movieTrendResult.setRuntime(jsonObject.getAsJsonPrimitive("runtime").getAsInt());

		return movieTrendResult;
	}

}
