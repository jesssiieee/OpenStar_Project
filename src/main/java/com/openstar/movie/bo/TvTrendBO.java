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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.openstar.movie.Entity.TvTrend;
import com.openstar.movie.Entity.TvTrendEntity;
import com.openstar.movie.repository.TvRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class TvTrendBO {
	
	@Autowired
	private TvRepository tvRepository;
	
    @Value("${api.tv-trend.base-url}")
    private String baseUrl;
    
    @Value("${api.tv-parse.base-url}")
    private String parseBaseUrl;

    @Value("${api.tv-trend.language}")
    private String language;

    @Value("${api.tv-trend.sort-by}")
    private String sortBy;

    @Value("${api.key}")
    private String key;
	
	public static final String KEY = "b250b43bc815002de64903f4433d25bd";

    LocalDateTime dateTime = LocalDateTime.now();

    public List<TvTrendEntity> parseHomeTvTrendJson() {
	    String ImgUrl = "https://image.tmdb.org/t/p/w200";
	    String match = "[\"]";
	    List<TvTrendEntity> resultHomeTvTrendList = new ArrayList<>(); 
	    
	    int maxPages = 10; // Maximum number of pages to fetch
	    int page = 1;
	    
	    while(page <= maxPages) {
	    	try {
	    		
	    		String apiURL = baseUrl + "?api_key=" + key + "&language=" + language + "&sort_by=" + sortBy + "&page=" + page;
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
	                JsonObject tvObject = element.getAsJsonObject();
	                TvTrendEntity homeTvTrendList = new TvTrendEntity();
	                
	                homeTvTrendList.setMovieId(tvObject.getAsJsonPrimitive("id").getAsInt());
	                homeTvTrendList.setTitle(tvObject.getAsJsonPrimitive("name").getAsString());
	                homeTvTrendList.setOverview(tvObject.getAsJsonPrimitive("overview").getAsString());
	                homeTvTrendList.setPosterPath(
	                        ImgUrl + tvObject.getAsJsonPrimitive("poster_path").getAsString().replaceAll(match, ""));
	                homeTvTrendList.setReleaseDate(tvObject.getAsJsonPrimitive("first_air_date").getAsString());
	                homeTvTrendList.setVoteAverage(tvObject.getAsJsonPrimitive("vote_average").getAsDouble());

	                resultHomeTvTrendList.add(homeTvTrendList);
	            }

				page++;
			} catch (Exception e) {
				 e.printStackTrace();
			}
	    }
	    
	    return resultHomeTvTrendList;
	    
    }
    
    
    
    
    public TvTrend parseTvTrendJson(@PathVariable(name = "tvId") int tvId) throws UnsupportedEncodingException, IOException {
    	
    	String apiURL = parseBaseUrl + tvId + "?api_key=" + key;
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
        
        TvTrend tvTrendResult = new TvTrend();
        tvTrendResult.setId(jsonObject.getAsJsonPrimitive("id").getAsInt());
        tvTrendResult.setOriginalName(jsonObject.getAsJsonPrimitive("original_name").getAsString());
        tvTrendResult.setPosterPath(ImgUrl + jsonObject.getAsJsonPrimitive("poster_path").getAsString().replaceAll(match, ""));
        tvTrendResult.setOverview(jsonObject.getAsJsonPrimitive("overview").getAsString());
        tvTrendResult.setReleaseDate(jsonObject.getAsJsonPrimitive("first_air_date").getAsString());
        tvTrendResult.setPopularity(jsonObject.getAsJsonPrimitive("popularity").getAsDouble());
        tvTrendResult.setVoteAverage(jsonObject.getAsJsonPrimitive("vote_average").getAsDouble());

        return tvTrendResult;
    }

}
