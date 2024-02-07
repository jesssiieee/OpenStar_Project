<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex justify-content-center mt-5">
	<div class="first-page d-flex justify-content-space-between">

		<!--${personResultList }  -->

		<c:forEach var="personResult" items="${personResultList}">
			${personResult.originalName}<br>
			<c:forEach var="knownFor" items="${personResult.knownFor}">
				${knownFor.name}<br>
				<a href="#"> <img src="${knownFor.posterPath}" alt="Poster"
					class="poster-img" style="width: 200px; height: 288px;">
				<!-- "originalName" 대신에 "name"으로 수정 -->
			</c:forEach>
		</c:forEach>


	</div>

</div>

