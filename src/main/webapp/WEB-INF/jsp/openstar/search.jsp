<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="d-flex justify-content-center mt-5">
	<div class="sign-in-box">

		<div class="input-group input-group-lg">
			<div class="input-group-prepend">
				<span class="input-group-text">Search</span>
			</div>
			<input type="text" class="form-control" id="SearchFor"
				aria-label="Large" placeholder="작품의 제목, 배우(으)로 검색하세요.">
			<button class="btn btn-primary" id="searchButton">검색</button>
		</div>

	</div>
</div>

<script>
	$(document).ready(function() {

		$("#searchButton").on('click',function() {
			let searchKeyword = $("#SearchFor").val().trim();
				// 검색어를 공백을 기준으로 분리
				/* let keywords = searchKeyword.split(" "); */
				// 검색어에 공백이 포함되어 있고, 분리된 단어의 개수가 2개 이상이라면
				if (searchKeyword.length > 3) {// 두 번째 AJAX 요청 보내기
					$.ajax({
						type : "GET",
						/* url : "/post/post-search-all/"+ encodeURIComponent(searchKeyword), */
						success : function(result) {
							// AJAX 요청이 성공한 경우 처리
							location.href = "/openstar/search-view/"+ encodeURIComponent(searchKeyword);
						},
						error : function(error) {
							// AJAX 요청이 실패한 경우 처리
							console.log(error);
							alert("검색에 실패하였습니다.");
						}
					});
				} else {
					// 첫 번째 AJAX 요청 보내기
					$.ajax({
						type : "GET",
						/* url : "/post/post-search/"+ encodeURIComponent(searchKeyword), */
						success : function(result) {
							// AJAX 요청이 성공한 경우 처리
							location.href = "/openstar/search-view/"+ encodeURIComponent(searchKeyword);
						},error : function(error) {
							// AJAX 요청이 실패한 경우 처리
							console.log(error);
							alert("검색에 실패하였습니다.");
						}
					});
				}
			});

		});
</script>