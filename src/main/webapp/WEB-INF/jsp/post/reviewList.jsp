<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="height: 1200px;" class="d-flex">

	<div style="width: 90%;" class="contents">
		<%-- 글 목록 뿌리기 --%>
		<c:forEach items="${reviewList }" var="review">

			<div class="review-item">
				<h3>${userName }</h3>
				<h5>${review.subject }</h5>
				<div class="d-flex">
					<img style="width: 200px; height: 200px;"
						src="${review.imagePath }">
					<h5>${review.content }</h5>
				</div>
				<div class="d-flex justify-content-between align-items-center">
					<h4>${review.rating }</h4>
					<button class="btn btn-warning detail-btn" data-review-id="${review.id }">수정</button>
				</div>
			</div>

		</c:forEach>
	</div>


	<div style="width: 10%;" class="contents border-right-info">

		<a href="http://localhost/post/post-write-review">
			<button id="write-btn" class="btn btn-info float-right">글쓰기</button>
		</a>

	</div>

</div>


<script>

	$(document).ready(function() {
	    $(".detail-btn").on('click', function() {
	    	
	        let reviewId = $(this).data('review-id');
	        // alert(reviewId);
	        
	        $.ajax({
	            type: "GET",
	            data: {"reviewId":reviewId},
	            url: "/post/post-review-detail/" + reviewId,
	            success: function(result) {
	                // 서버에서 받은 리뷰 데이터(result)를 처리
	                location.href = "/post/post-review-detail/" + reviewId;
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });

	    }); // detail
	});


</script>

