<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String contentIdStr = request.getParameter("contentId");
int contentId = Integer.parseInt(contentIdStr);
%>

<div class="d-flex">
	<c:forEach var="personResult" items="${personResultList}">
		<c:forEach var="knownFor" items="${personResult.knownFor}">

			<c:if test="${knownFor.id eq contentId}">
				<div style="width: 20%;" class="content border-right-info">
					<div class="mt-5">
						<img src="${knownFor.posterPath}" alt="Poster"
							class="poster-img mt-3 ml-5" style="width: 200px; height: 288px;">
						<h4 class="mt-3 ml-5">${knownFor.name}</h4>
					</div>
				</div>

				<div style="width: 80%;" class="content">
				
					<div style="height: 100px; justify-content: space-between;" class="d-flex">
					<!-- 오른쪽 80% 중 상단 1/3에 해당하는 내용 -->
						<div>
							<h2 class="ml-5 mt-3" style="color: red;">평점: ${knownFor.voteAverage}</h2>
						</div>
						<div class="mr-5 mt-3">
							<a href="#"><img style="height: 35px; width: 35px;" class="" src="/static/image/noneheart.png"></a>
							<a href="#"><img style="height: 50px; width: 50px;" class="ml-2" src="/static/image/nonebookmark.png"></a>
						</div>
					</div>
					
					<div style="height: 400px;" class="">
						<!-- 오른쪽 80% 중 중간 1/3에 해당하는 내용 -->
						<div style="width: 1000px; hieght: 300px;" class="ml-5 mt-5 border-bottom-black">
							<h5>상세내용: ${knownFor.overview}</h5>
						</div>
						<div style="width: 1000px; hieght: 300px;" class="ml-5 mt-5 border-bottom-black">
							<h5>작품 공개 날짜: ${knownFor.firstAirDate }</h5>
						</div>
					</div>
					
					<div style="height: 300px;">
						<!-- 오른쪽 80% 중 하단 1/3에 해당하는 내용 -->
					</div>
					
				</div>

			</c:if>

		</c:forEach>
	</c:forEach>
</div>














