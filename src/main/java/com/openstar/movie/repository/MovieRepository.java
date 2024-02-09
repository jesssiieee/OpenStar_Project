package com.openstar.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openstar.movie.Entity.MoviesTrendEntity;

public interface MovieRepository extends JpaRepository<MoviesTrendEntity, Integer> {
	
	public MoviesTrendEntity findByMovieId(int movieId);
	
	
}
