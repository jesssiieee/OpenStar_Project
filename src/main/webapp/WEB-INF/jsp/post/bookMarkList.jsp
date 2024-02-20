<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="content">
		<h2 class="ml-2">나의 '북마크' 목록</h2>

		<c:choose>
			<c:when test="${not empty tvTrendResultList}">
				<c:forEach items="${tvTrendResultList}" var="tv">
					<div class="d-flex mb-3 ml-5">				
						<img src="${tv.posterPath}" alt="Poster" class="poster-img"
							style="width: 200px; height: 288px;">
						<h4 class="mt-2 ml-4">${tv.originalName }</h4>
					</div>
				</c:forEach>
			</c:when>

			<c:otherwise>
				<p>No trend results found.</p>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${not empty movieTrendResultList}">
				<c:forEach items="${movieTrendResultList}" var="movie">
					<div class="d-flex mb-3 ml-5">	
						<img src="${movie.posterPath}" alt="Poster" class="poster-img"
							style="width: 200px; height: 288px;">
						<h4 class="mt-2 ml-4">${movie.originalTitle }</h4>
					</div>
				</c:forEach>
			</c:when>

			<c:otherwise>
				<p>No trend results found.</p>
			</c:otherwise>
		</c:choose>

</div>
