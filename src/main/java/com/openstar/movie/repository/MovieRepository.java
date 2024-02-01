package com.openstar.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openstar.movie.Entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

}
