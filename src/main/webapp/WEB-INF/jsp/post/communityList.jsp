<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="d-flex">

	<%-- 왼쪽 20% --%>
	<div style="width: 20%;" class="contents border-right-info">

		<c:if test="${not empty movieTrendResultList}">
			<!-- movieTrendResultList를 사용하는 경우 -->
			<c:set var="movieTrend" value="${movieTrendResultList}" />
			<div class="inline-item">
				<img src="${movieTrend.posterPath}" alt="Poster" class="poster-img"
					style="width: 200px; height: 288px;">
				<h4 class="mt-3 ml-5">${movieTrend.originalTitle}</h4>
			</div>

			<!-- <a href="http://localhost/post/post-write"> -->
			<button id="writeBtn" class="btn btn-info"
				data-write-id=${movieTrend.id }>글쓰기</button>
			<!-- </a> -->

		</c:if>

		<c:if test="${not empty tvTrendResultList}">
			<!-- tvTrendResultList를 사용하는 경우 -->
			<c:set var="tvTrend" value="${tvTrendResultList}" />
			<div class="inline-item">
				<img src="${tvTrend.posterPath}" alt="Poster" class="poster-img"
					style="width: 200px; height: 288px;">
				<h4 class="mt-3 ml-5">${tvTrend.originalName}</h4>
			</div>

			<!-- <a href="http://localhost/post/post-write"> -->
			<button id="writeBtn" class="btn btn-info"
				data-write-id=${tvTrend.id }>글쓰기</button>
			<!-- </a> -->
		</c:if>

	</div>

	<%-- 오른쪽 80% --%>
	<div style="width: 80%;" class="contents">

		<c:forEach items="${getCommunityList}" var="post">
		
			<button id="communityDetail" class="btn communityDetail-btn" data-detail-id="${post.postId}" data-post-id="${post.id}" >
				<div class="d-flex mb-5 ml-5">
					<h4 class="mr-3">${post.userName }</h4>
					<img src="${post.imagePath}" style="width: 300px; height: 200px;">
					<h5>${post.content }</h5>
				</div>
			</button>
		</c:forEach>
		
	</div>

</div>

<script>
	$(document).ready(
			function() {

				$("#writeBtn").on('click',function() {
					alert("클릭");
					let writeId = $(this).data('write-id');
					alert(writeId);
					
					$.ajax({
						type : "GET",
						success : function(result) {
							location.href = "/post/post-write/"+ encodeURIComponent(writeId)
						},
						error : function(error) {
							console.log(error);
							alert("검색에 실패하였습니다.");
						}

					}); // ajax

				}); // writeBtn
						
				$(".communityDetail-btn").on('click', function() {
				    let detailId = $(this).data('detail-id');
				    let postId = $(this).data('post-id');
				    
				    $.ajax({
				        type: "GET",
				        success: function(result) {
				            location.href = "/post/post-community-detailView/" + encodeURIComponent(detailId) + "?postId=" + postId;
				        },
				        error: function(error) {
				            console.log(error);
				            alert("상세페이지로 이동을 실패하였습니다.");
				        }
				    }); // ajax
				}); // communityDetail


			}); // ready
</script>









