<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="d-flex justify-content-center mt-5">
	<div class="sign-in-box">

		<div class="input-group input-group-lg">
			<div class="input-group-prepend">
				<span class="input-group-text">Search</span>
			</div>
			<input type="text" class="form-control" id="SearchFor" aria-label="Large"
				placeholder="작품의 제목, 배우, 감독으로 검색하세요.">
			<button class="btn btn-primary" id="searchButton">검색</button>
		</div>

	</div>
</div>

<script>
	
	$(document).ready(function() {
		
		$("#searchButton").on('click', function() {
			// alert("검색");
			let searchActorName = $("#SearchFor").val().trim();
			// alert(searchActorName);
			if (searchActorName !== '') {
				// ajax
				$.ajax ({
					type: "GET"
					, url: "/post/post-search/" + encodeURIComponent(searchActorName)
					, success: function(result) {
						 location.href = "/openstar/search-view/" + encodeURIComponent(searchActorName);
					}
					, error: function(error) {
						console.log(error);
						alert("검색에 실패하였습니다.");
					}
					
				});
			}
			
		}); // searchButton
		
	}); // ready
	
</script>