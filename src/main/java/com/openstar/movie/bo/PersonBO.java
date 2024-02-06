package com.openstar.movie.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.openstar.movie.Entity.KnownFor;
import com.openstar.movie.Entity.PersonResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonBO {

    public List<PersonResult> parseJson(String json) {
        List<PersonResult> personResults = new ArrayList<>();

        try {
            JsonObject jsonObject = new com.google.gson.JsonParser().parse(json).getAsJsonObject();
            JsonArray results = jsonObject.getAsJsonArray("results");

            for (int i = 0; i < 1; i++) {
                JsonObject personObject = results.get(i).getAsJsonObject();
                PersonResult personResult = new PersonResult();

                // Set common properties
                personResult.setId(personObject.getAsJsonPrimitive("id").getAsInt());
                personResult.setGender(personObject.getAsJsonPrimitive("gender").getAsInt());
                personResult.setName(personObject.getAsJsonPrimitive("name").getAsString());
                personResult.setPopularity(personObject.getAsJsonPrimitive("popularity").getAsDouble());
                personResult.setProfilePath(personObject.getAsJsonPrimitive("profile_path").getAsString());

                // Set known_for list
                JsonArray knownForArray = personObject.getAsJsonArray("known_for");
                List<Map<String, Object>> knownForList = new ArrayList<>();

                for (int j = 0; j < knownForArray.size(); j++) {
                    JsonObject knownForObject = knownForArray.get(j).getAsJsonObject();
                    Map<String, Object> knownForMap = new HashMap<>();

                    knownForMap.put("id", knownForObject.getAsJsonPrimitive("id").getAsInt());
                    knownForMap.put("name", knownForObject.getAsJsonPrimitive("name").getAsString());
                    knownForMap.put("overview", knownForObject.getAsJsonPrimitive("overview").getAsString());
                    knownForMap.put("posterPath", knownForObject.getAsJsonPrimitive("poster_path").getAsString());
                    knownForMap.put("mediaType", knownForObject.getAsJsonPrimitive("media_type").getAsString());
                    knownForMap.put("popularity", knownForObject.getAsJsonPrimitive("popularity").getAsDouble());
                    knownForMap.put("voteAverage", knownForObject.getAsJsonPrimitive("vote_average").getAsDouble());
                    knownForMap.put("originCountry", knownForObject.getAsJsonArray("origin_country"));

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
