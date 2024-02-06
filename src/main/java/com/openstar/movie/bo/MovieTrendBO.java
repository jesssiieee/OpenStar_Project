package com.openstar.movie.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

}
