package com.openstar.movie.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.openstar.movie.Entity.PersonResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonBO {
	
    @Value("${api.person.base-url}")
    private String baseUrl;
    
    @Value("${api.key}")
    private String key;
	
    public List<PersonResult> parseJson(@PathVariable(name = "searchKeyword") String searchKeyword) throws UnsupportedEncodingException, IOException {
    	
		String result = "";
		String apiURL = "";
        List<PersonResult> personResults = new ArrayList<>();
        
        char firstChar = searchKeyword.charAt(0);
        if ((firstChar >= '가' && firstChar <= '힣') || (firstChar >= 'ㄱ' && firstChar <= 'ㅎ')) {
        	apiURL = baseUrl + key + "&query=" + searchKeyword;
        }   else if ((firstChar >= 'a' && firstChar <= 'z') || (firstChar >= 'A' && firstChar <= 'Z')) {
        	searchKeyword = URLEncoder.encode(searchKeyword, "UTF-8");
        	apiURL = baseUrl + key + "&query=" + searchKeyword;
        } 
        
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
                PersonResult personResult = new PersonResult();

                // Set common properties
                personResult.setId(personObject.getAsJsonPrimitive("id").getAsInt());
                personResult.setOriginalName(personObject.getAsJsonPrimitive("original_name").getAsString());
                personResult.setProfilePath(ImgUrl + personObject.getAsJsonPrimitive("profile_path").getAsString().replaceAll(match, ""));

                // Set known_for list
                JsonArray knownForArray = personObject.getAsJsonArray("known_for");
                List<Map<String, Object>> knownForList = new ArrayList<>();

                for (int j = 0; j < knownForArray.size(); j++) {
                    JsonObject knownForObject = knownForArray.get(j).getAsJsonObject();
                    Map<String, Object> knownForMap = new HashMap<>();
                    
                    knownForMap.put("id", knownForObject.getAsJsonPrimitive("id").getAsInt());
                    if (knownForObject.has("original_name")) {
                        knownForMap.put("name", knownForObject.getAsJsonPrimitive("original_name").getAsString());
                    } else if (knownForObject.has("original_title")) {
                        knownForMap.put("name", knownForObject.getAsJsonPrimitive("original_title").getAsString());
                    }
                    knownForMap.put("overview", knownForObject.getAsJsonPrimitive("overview").getAsString());
                    knownForMap.put("posterPath", ImgUrl + knownForObject.getAsJsonPrimitive("poster_path").getAsString().replaceAll(match, ""));
                    if (knownForObject.has("first_air_date")) {
                        knownForMap.put("firstAirDate", knownForObject.getAsJsonPrimitive("first_air_date").getAsString());
                    } else if (knownForObject.has("release_date")) {
                        knownForMap.put("firstAirDate", knownForObject.getAsJsonPrimitive("release_date").getAsString());
                    }
                    knownForMap.put("voteAverage", knownForObject.getAsJsonPrimitive("vote_average").getAsDouble());

                    knownForList.add(knownForMap);
                }

                personResult.setKnownFor(knownForList);

                personResults.add(personResult);
                
                
            }
        } catch (Exception e) {
            log.error("Error parsing JSON", e);
        }
        
        return personResults;
    }
    
}