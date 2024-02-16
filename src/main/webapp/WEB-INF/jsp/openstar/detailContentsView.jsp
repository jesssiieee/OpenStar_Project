<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
// contentId를 request.getParameter() 메서드를 사용하여 가져옵니다.
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

					<div style="height: 100px; justify-content: space-between;"
						class="d-flex">
						<!-- 오른쪽 80% 중 상단 1/3에 해당하는 내용 -->
						<div>
							<h2 class="ml-5 mt-3" style="color: red;">평점:
								${knownFor.voteAverage}</h2>
						</div>
						<div class="mr-5 mt-3">
							<a href="#">
								<img style="height: 35px; width: 35px;" class=""src="/static/image/noneheart.png">
							</a> 
							<a href="#">
								<img style="height: 50px; width: 50px;" class="ml-2"src="/static/image/nonebookmark.png">
							</a>
						</div>
					</div>

					<div style="height: 400px;" class="">
						<!-- 오른쪽 80% 중 중간 1/3에 해당하는 내용 -->
						<div style="width: 1000px; hieght: 300px;"
							class="ml-5 mt-5 border-bottom-black">
							<h5>상세내용: ${knownFor.overview}</h5>
						</div>
						<div style="width: 1000px; hieght: 300px;"
							class="ml-5 mt-5 border-bottom-black">
							<h5>작품 공개 날짜: ${knownFor.firstAirDate }</h5>
						</div>
					</div>

					<div style="width: 1000px; hieght: 300px;" class="ml-5 mt-5">
						<!-- 오른쪽 80% 중 하단 1/3에 해당하는 내용 -->
						<div class="float-right">
							<!-- <a href="http://localhost/post/post-community-view/${knownFor.id }">  -->
							<button class="btn btn-primary" id="community" data-search-id="${knownFor.id }">커뮤니티</button>
							<!-- </a>  -->
						</div>
					</div>

				</div>

			</c:if>

		</c:forEach>
	</c:forEach>
</div>

<script>

	$(document).ready(function() {
		$("#community").on('click', function() {
			// alert("클릭");
			
			let searchId = $(this).data('search-id');
			// alert(searchId);
			
			if (searchId != '') {
				$.ajax({
					type : "GET",
					success : function(result) {
						location.href = "/post/post-community-view/" + searchId;
					},
					error : function(
							error) {
						console
								.log(error);
						alert("검색에 실패하였습니다.");
					}
					
				}); // ajax
			}
			
		}); // community
	}); //ready

</script>














