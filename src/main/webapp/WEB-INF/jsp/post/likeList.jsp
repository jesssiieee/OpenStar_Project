<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="justify-content-center mt-5" style="overflow-x: auto;">

	<div class="flex-container">

		<c:choose>
			<c:when test="${not empty tvTrendResultList}">
				<c:forEach items="${tvTrendResultList}" var="tv">
					<img src="${tv.posterPath}" alt="Poster" class="poster-img"
						style="width: 200px; height: 288px;">
				</c:forEach>
			</c:when>

			<c:otherwise>
				<p>No trend results found.</p>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${not empty movieTrendResultList}">
				<c:forEach items="${movieTrendResultList}" var="movie">
					<img src="${movie.posterPath}" alt="Poster" class="poster-img"
						style="width: 200px; height: 288px;">
				</c:forEach>
			</c:when>

			<c:otherwise>
				<p>No trend results found.</p>
			</c:otherwise>
		</c:choose>




	</div>

</div>
