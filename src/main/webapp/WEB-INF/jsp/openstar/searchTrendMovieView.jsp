<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="d-flex">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<%-- 왼쪽 20% --%>
	<div style="width: 20%;" class="contents border-right-info">
		<div class="inline-item">
			<img src="${movieTrendResultList.posterPath}" alt="Poster"
				class="poster-img" style="width: 200px; height: 288px;">
			<h4 class="mt-3 ml-5">${movieTrendResultList.originalTitle}</h4>
		</div>

	</div>

	<%-- 오른쪽 80 --%>
	<div style="width: 80%;" class="contents d-flex">
		<div style="height: 400px;" class="">

			<%-- 좋아요, 북마크 --%>
			<div class="float-right d-flex mb-5">
				<%-- 좋아요 --%>

				<div class="float-right d-flex mb-5">
					<a href="#" class="like-btn" data-content-id="${multiResult.id }">
						<img style="height: 40px; width: 35px;" class="ml-2 mt-1"
						src="/static/image/noneheart.png">
					</a>
				</div>

				<%-- 북마크 --%>
				<div class="float-right d-flex mb-3">
					<a href="#" class="bookmark-btn"
						data-content-id="${multiResult.id }"> <img
						style="height: 50px; width: 50px;" class="ml-2 mb-4"
						src="/static/image/nonebookmark.png">
					</a>
				</div>

			</div>

			<div style="width: 1000px; hieght: 300px;"
				class="ml-5 mt-5 border-bottom-black">
				<h5>상세내용: ${movieTrendResultList.overview}</h5>
			</div>

			<div style="width: 1000px; hieght: 300px;"
				class="ml-5 mt-5 border-bottom-black">
				<h5>작품 공개 날짜: ${movieTrendResultList.releaseDate }</h5>
			</div>

			<div style="width: 1000px; hieght: 300px;" class="ml-5 mt-5">
				<!-- 오른쪽 80% 중 하단 1/3에 해당하는 내용 -->
				<h5>인기도: ${movieTrendResultList.popularity }</h5>
				<h5>평점: ${movieTrendResultList.voteAverage }</h5>
				<h5>총 수입: ${movieTrendResultList.revenue }</h5>
				<h5>상영시간: ${movieTrendResultList.runtime }</h5>
				<div class="float-right">
					<a href="http://localhost/post/post-community-view"><button
							class="btn btn-primary" id="community">커뮤니티</button></a>
				</div>
			</div>
		</div>
	</div>

</div>