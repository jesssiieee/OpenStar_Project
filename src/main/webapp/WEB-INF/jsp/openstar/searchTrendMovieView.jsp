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
					<c:set value="${isLiked }" var="like"/>
					
					<c:if test="${like == false }">
						<a href="#" class="like-btn" data-content-id="${movieTrendResultList.id }" >
							<img style="height: 40px; width: 35px;" class="ml-2 mt-1" src="/static/image/noneheart.png">
						</a>
					</c:if>
					
					<c:if test="${like }">
						<a href="#" class="like-btn" data-content-id="${movieTrendResultList.id }" >
							<img style="height: 40px; width: 35px;" class="ml-2 mt-1" src="/static/image/heart.png">
						</a>
					</c:if>
				</div>
				
				<%-- 북마크 --%>
				<div class="float-right d-flex mb-5">
					<c:set value="${isBookMarked }" var="bookmark" />
					<c:if test="${bookmark eq false }">
						<a href="#" class="bookmark-btn" data-content-id="${movieTrendResultList.id }" >
							<img style="height: 50px; width: 50px;" class="ml-2" src="/static/image/nonebookmark.png">
						</a>
					</c:if>
					
					<c:if test="${bookmark eq true }">
						<a href="#" class="bookmark-btn" data-content-id="${movieTrendResultList.id }" >
							<img style="height: 50px; width: 50px;" class="ml-2" src="/static/image/bookmark.png">
						</a>
					</c:if>
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
					<button class="btn btn-primary" id="community" data-search-id="${movieTrendResultList.id }">커뮤니티</button>
					<button class="btn btn-primary" id="book" data-search-id="${movieTrendResultList.id }">예매하기</button>
				</div>
				
			</div>
		</div>
	</div>


	<script>
		$(document).ready(function() {

			let currentRequests = []; // 현재 실행 중인 AJAX 요청을 저장하는 배열

			// Like 버튼 클릭 이벤트 핸들러
			$(document).on('click', '.like-btn', function(e) {

				e.preventDefault();

				// alert("like");

				// Like 버튼에 대한 AJAX 요청만 수행
				let button = $(this);
				let contentId = button.data("content-id");
				let type = 'like';

				// alert(contentId);

				console.log("Like 버튼 클릭 - contentId: " + contentId);

				// 현재 실행 중인 AJAX 요청을 모두 취소합니다.
				currentRequests.forEach(function(request) {
					request.abort();
				});
				currentRequests = []; // 배열 비우기

				let request = $.ajax({
					url : "/check/like/" + contentId,
					data : {
						"type" : type
					},
					success : function(data) {
						if (data.code == 200) {
							location.reload(true);
						} else if (data.code == 300) {
							alert(data.error_message);
							location.href = "/user/sign-in-view";
						}
					},
					error : function(request, status, error) {
						alert("좋아요를 하는데 실패했습니다.");
					}
				});

				// 현재 요청을 배열에 추가합니다.
				currentRequests.push(request);
			});

			// Bookmark 버튼 클릭 이벤트 핸들러
			$(document).on('click', '.bookmark-btn', function(e) {

				e.preventDefault();

				// alert("bookmark");

				// Bookmark 버튼에 대한 AJAX 요청만 수행
				let button = $(this);
				let contentId = button.data("content-id");
				console.log(contentId);
				let type = 'bookmark';

				// alert(contentId);

				console.log("Bookmark 버튼 클릭 - contentId: " + contentId);

				// 현재 실행 중인 AJAX 요청을 모두 취소합니다.
				currentRequests.forEach(function(request) {
					request.abort();
				});
				currentRequests = []; // 배열 비우기

				let request = $.ajax({
					url : "/check/bookmark/" + contentId,
					data : {
						"type" : type
					},
					success : function(data) {
						if (data.code == 200) {
							location.reload(true);
						} else if (data.code == 300) {
							alert(data.error_message);
							location.href = "/user/sign-in-view";
						}
					},
					error : function(request, status, error) {
						alert("북마크를 하는데 실패했습니다.");
					}
				});

				// 현재 요청을 배열에 추가합니다.
				currentRequests.push(request);
			}); // bookmark
			
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
						error : function(error) {
							console.log(error);
							alert("검색에 실패하였습니다.");
						}
						
					}); // ajax
				}
				
			}); // community
			
			$("#book").on('click', function() {
				// alert("클릭");
				let searchId = $(this).data('search-id');
				// alert(searchId);
			});
			
		}); // ready
	</script>