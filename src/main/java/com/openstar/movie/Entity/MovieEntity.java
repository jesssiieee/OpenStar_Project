package com.openstar.movie.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Builder
@Entity
@Table(name="trendmovie")
public class MovieEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
	private int id;
	
	private int movieId;
	
    private Double popularity; // 인기도

    private String posterPath; // 포스터 이미지

    private LocalDate releaseDate; // 개봉일

    private String title; // 제목

	
}
