package com.openstar.movie.Entity;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class MultiEntity {
	
    private int id;
//  private boolean adult;
//  private int gender;
//  private String name;
  private String originalName;
  private String overView;
//  private double popularity;
  private String profilePath;
  private String posterPath;
  private String firstAirDate;
  private double voteAverage;
  private List<Map<String,Object>> knownFor; 

}
