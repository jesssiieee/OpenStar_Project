<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex">

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:choose>
		<%-- 배우검색 - 필모그래피 리스트 --%>
		<c:when test="${not empty personResultList}">
			<!-- personResultList에 대한 forEach -->
			<div style="width: 20%;" class="contents border-right-info">
				<c:forEach var="personResult" items="${personResultList}">
					<div class="inline-item">
						<img src="${personResult.profilePath}" alt="Poster"
							class="poster-img" style="width: 200px; height: 288px;">
						<h4 class="mt-3 ml-5">${personResult.originalName}</h4>
					</div>
				</c:forEach>
			</div>

			<div style="width: 80%;" class="contents d-flex">
				<c:forEach var="personResult" items="${personResultList}">
					<c:forEach var="knownFor" items="${personResult.knownFor}">
						<div class="inline-item">
							<a
								href="http://localhost/openstar/search-view/detail/${personResult.originalName}">
								<img src="${knownFor.posterPath}" alt="Poster"
								class="poster-img" style="width: 200px; height: 288px;"
								data-content-id="${knownFor.id}"
								data-original-name="${personResult.originalName}">
							</a>
							<h5 class="mt-3">작품 제목: ${knownFor.name}</h5>
						</div>
					</c:forEach>
				</c:forEach>
			</div>

		</c:when>
		
		<%-- 작품검색 - 작품상세정보 --%>
		<c:otherwise>
			<!-- multiResultList에 대한 forEach -->
			<div style="width: 20%;" class="contents border-right-info">
				<c:forEach var="multiResult" items="${multiResultList}">
					<div class="inline-item">
						<img src="${multiResult.posterPath}" alt="Poster"
							class="poster-img" style="width: 200px; height: 288px;">
						<h4 class="mt-3 ml-3">${multiResult.originalName}</h4>
					</div>
			</div>

			<div style="width: 80%;" class="contents d-flex">
				<div style="height: 400px;" class="">
					
					<%-- 좋아요, 북마크 --%>
					<div class="float-right mb-5">
						<a href="#"><img style="height: 35px; width: 35px;" class="" src="/static/image/noneheart.png"></a>
						<a href="#"><img style="height: 50px; width: 50px;" class="ml-2" src="/static/image/nonebookmark.png"></a>
					</div>
						
					<!-- 오른쪽 80% 중 중간 1/3에 해당하는 내용 -->
					<div style="width: 1000px; hieght: 300px;"
						class="ml-5 mt-5 border-bottom-black">
						<h5>상세내용: ${multiResult.overView}</h5>
					</div>

					<div style="width: 1000px; hieght: 300px;"
						class="ml-5 mt-5 border-bottom-black">
						<h5>작품 공개 날짜: ${multiResult.firstAirDate }</h5>
					</div>

					<div style="width: 1000px; hieght: 300px;"
						class="ml-5 mt-5">
						<!-- 오른쪽 80% 중 하단 1/3에 해당하는 내용 -->
						<div class="float-right">
							<a href="#"><button class="btn btn-primary" id="community">커뮤니티</button></a>
						</div>
					</div>
				</div>
				</c:forEach>

			</div>

		</c:otherwise>
	</c:choose>

</div>

<script>
	$(document)
			.ready(
					function() {
						// 이미지 클릭 이벤트 핸들러
						$('.poster-img')
								.click(
										function() {
											let contentId = $(this).data(
													'content-id');
											let searchActorName = $(this).data(
													'original-name');
											// alert(contentId);
											// alert(searchActorName);

											if (contentId != '') {
												$
														.ajax({
															type : "GET",
															success : function(
																	result) {
																location.href = "/openstar/search-view/detail/"
																		+ encodeURIComponent(searchActorName)
																		+ "?contentId="
																		+ contentId;
															},
															error : function(
																	error) {
																console
																		.log(error);
																alert("검색에 실패하였습니다.");
															}
														}); // ajax
											}

										}); // click poster-img
					}); // ready
</script>










