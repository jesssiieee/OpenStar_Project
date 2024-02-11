package com.openstar.movie.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.gson.JsonArray;
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

            tvRepository.save(
            		TvTrendEntity.builder()
                    .movieId(contents.get("id").getAsInt())
                    .grade(contents.get("vote_average").getAsDouble())
                    .overview(contents.get("overview").getAsString())
                    .posterPath(ImgUrl + contents.get("poster_path").toString().replaceAll(match, ""))
                    .releaseDate(LocalDate.parse(contents.get("first_air_date").getAsString()))
                    .title(contents.get("name").toString())
                    .build()
            );

        }
        return "ok";
    }
    
    public TvTrend parseTvTrendJson(@PathVariable(name = "tvId") int tvId) throws UnsupportedEncodingException, IOException {
    	// movieId select
    	TvTrendEntity dbTvId = tvRepository.findByMovieId(tvId);
    	int dbSearchTvId = dbTvId.getMovieId();
    	
        String apiURL = "https://api.themoviedb.org/3/tv/" + tvId + "?api_key=" + KEY;
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
