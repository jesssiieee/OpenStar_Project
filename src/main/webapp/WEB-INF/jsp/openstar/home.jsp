<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="justify-content-center mt-5" style="overflow-x: auto;">

	<h1>이 주의 인기 순 / 영화</h1>
	
	<div class="flex-container">
		<c:forEach items="${movieTrendList}" var="mTL">
			<div class="flex-item">
				 <img src="${mTL.posterPath}" alt="Poster"
					class="poster-img" style="width: 200px; height: 288px;" data-movie-id="${mTL.movieId}">
				
			</div>
		</c:forEach>
	</div>
	
</div>

<div class="justify-content-center mt-5" style="overflow-x: auto;">

	<h1>이 주의 인기 순 / TV</h1>
	
	<div class="flex-container">
		<c:forEach items="${tvTrendList}" var="tTL">
			<div class="flex-item">
				<a href="#"> <img src="${tTL.posterPath}" alt="Poster"
					class="poster-img" style="width: 200px; height: 288px;">
				</a>
			</div>
		</c:forEach>
	</div>
	
</div>

<script>
	$(document).ready(function () {
		$(".poster-img").on('click', function () {
			// alert("클릭");
			let movieId = $(this).data("movie-id");
			// alert(movieId);
			if (movieId != '') {
				$.ajax({
					type: "GET"
					, success: function(data) {
						location.href = "/openstar/search-view/trend/" + encodeURIComponent(movieId)
					}
					, error: function(error) {
						consol.log(error);
						alert("영화 정보를 불러오는데 실패하셨습니다.");
					}
					
				}); // ajax
			}
			
		}); // poster_img
		
	}); //ready
</script>
