<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="d-flex">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<%-- 왼쪽 20% --%>
	<div style="width: 20%;" class="contents border-right-info">
		<div class="inline-item">
			<img src="${tvTrendResultList.posterPath}" alt="Poster"
				class="poster-img" style="width: 200px; height: 288px;">
			<h4 class="mt-3 ml-5">${tvTrendResultList.originalName}</h4>
		</div>

	</div>

	<%-- 오른쪽 80 --%>
	<div style="width: 80%;" class="contents d-flex">
		<div style="height: 400px;" class="">

			<div style="width: 1000px; hieght: 300px;"
				class="ml-5 mt-5 border-bottom-black">
				<h5>상세내용: ${tvTrendResultList.overview}</h5>
			</div>

			<div style="width: 1000px; hieght: 300px;"
				class="ml-5 mt-5 border-bottom-black">
				<h5>작품 공개 날짜: ${tvTrendResultList.releaseDate }</h5>
			</div>

			<div style="width: 1000px; hieght: 300px;" class="ml-5 mt-5">
				<!-- 오른쪽 80% 중 하단 1/3에 해당하는 내용 -->
				<h5>인기도: ${tvTrendResultList.popularity }</h5>
				<h5>평점: ${tvTrendResultList.voteAverage }</h5>
				<div class="float-right">
					<a href="http://localhost/post/post-community-view"><button
							class="btn btn-primary" id="community">커뮤니티</button></a>
				</div>
			</div>
		</div>
	</div>

</div>
>
