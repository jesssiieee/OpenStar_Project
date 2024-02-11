package com.openstar.movie.Entity;

import lombok.Data;

@Data
public class TvTrend {

	private int id;

	private String originalName;

	private String overview;

	private String posterPath;

	private String releaseDate;

	private double popularity; // 인기도

	private double voteAverage; // 평점


}
