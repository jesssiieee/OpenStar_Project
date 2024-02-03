package com.openstar.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openstar.movie.Entity.TvTrendEntity;

public interface TvRepository extends JpaRepository<TvTrendEntity, Integer> {

}
