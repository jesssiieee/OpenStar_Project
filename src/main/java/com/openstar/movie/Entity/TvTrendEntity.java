package com.openstar.movie.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
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
@Table(name="trendtv")
public class TvTrendEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
	private int id;
	
	@Column(name="movieId")
	private int movieId; // id
	
    private Double grade; // 평점, vote_average
    
    private String overview; // 내용 요약
    
    @Column(name="posterPath")
    private String posterPath; // 포스터 이미지, poster_path"

    @Column(name="releaseDate")
    private LocalDate releaseDate; // 개봉일 , first_air_date

    private String title; // 제목, name

}
