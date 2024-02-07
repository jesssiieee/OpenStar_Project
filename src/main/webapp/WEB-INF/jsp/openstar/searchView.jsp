<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 
<div class="justify-content-center mt-5" style="overflow-x: auto;">
	<h1>검색 결과</h1>
	<div class="flex-container">
		<c:forEach var="personResult" items="${personResultList}">
			<div class="flex-item d-flex">
				<c:forEach var="knownFor" items="${personResult.knownFor}">
					<a href="#"> <img src="${knownFor.posterPath}" alt="Poster"
						class="poster-img" style="width: 200px; height: 288px;">
				</c:forEach>
			</div>
		</c:forEach>
	</div>
</div>
-->
<!--${personResultList }  

		<c:forEach var="personResult" items="${personResultList}">
		${personResult.profilePath}
			<img src ="${personResult.profilePath}" class="poster-img" style="width: 200px; height: 288px;" ><br>
			
			${personResult.originalName }
			<c:forEach var="knownFor" items="${personResult.knownFor}">
				<a href="#"> <img src="${knownFor.posterPath}" alt="Poster"
					class="poster-img" style="width: 200px; height: 288px;">
			</c:forEach>
		</c:forEach>

-->

<div class="d-flex">

	<div style="width: 20%;" class="contents border-right-info">
		<c:forEach var="personResult" items="${personResultList}">
			<div>
				<img src="${personResult.profilePath}" alt="Poster"
					class="poster-img mt-3 ml-5" style="width: 200px; height: 288px;">
				<h4 class="mt-3 ml-5">${personResult.originalName}</h4>

			</div>
	</div>


	<div style="width: 80%;" class="contents d-flex">
		<c:forEach var="knownFor" items="${personResult.knownFor}">
			<div class="inline-item">

				<img src="${knownFor.posterPath}" alt="Poster" class="poster-img"
					style="width: 200px; height: 288px;">
				<h5 class="mt-3">작품 제목: ${knownFor.name}</h5>
				<h5 class="mt-3">공개 날짜: ${knownFor.firstAirDate}</h5>
				<h5 class="mt-3">평    점: ${knownFor.voteAverage}</h5>

			</div>
		</c:forEach>
	</div>

	</c:forEach>
</div>










