<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String contentIdStr = request.getParameter("contentId");
int contentId = Integer.parseInt(contentIdStr);
%>

<div class="d-flex">

	<div style="width: 20%;" class="contents border-right-info">
		<c:forEach var="personResult" items="${personResultList}">
			<c:forEach var="knownFor" items="${personResult.knownFor}">
				<c:if test="${knownFor.id eq contentId}">
					<div>
						<img src="${knownFor.posterPath}" alt="Poster" class="poster-img mt-3 ml-5" style="width: 200px; height: 288px;">
						<h4 class="mt-3 ml-5">${knownFor.name}</h4>
					</div>
				</c:if>
			</c:forEach>
		</c:forEach>
	</div>
</div>










