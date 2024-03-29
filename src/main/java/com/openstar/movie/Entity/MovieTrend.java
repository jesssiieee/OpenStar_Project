package com.openstar.movie.Entity;

import lombok.Data;

@Data
public class MovieTrend {
	
	private int id;
	
	private String originalTitle;
	
	private String overview;
	
	private String posterPath;
	
	private String releaseDate;
	
	private double popularity; // 인기도
	
	private double voteAverage; // 평점
	
	private int revenue; // 총 수입
	
	private int runtime;
	
	
	

}
