<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex">
	<%-- 왼쪽 20% --%>
	<div style="width: 30%;" class="contents border-right-info">
		<c:set var="detail" value="${getCommunityList}" />
		<div class="ml-5">
			<h3>${detail.userName}</h3>
			<img src="${detail.imagePath}" style="width: 400px; height: 300px;">
		</div>
	</div>

	<%-- 오른쪽 70% --%>
	<div style="width: 70%;" class="">

		<div style="width: 1000px; height: 300px;"
			class="ml-5 mt-5 border-bottom-black">${detail.content}</div>

		<div style="width: 1000px; height: 600px;"
			class="ml-5 mt-5 border-bottom-black">
			댓글
			// getCommentList
			
			<c:set var="comment" value="${getCommentList}"/>
				<h5>${comment.content }</h5>
			<div class="d-flex">
				<input type="text" id="commentValue" class="form-control mr-2 comment-input" placeholder="댓글 내용을 입력하세요.">
				<button id="comment" class="btn btn-success" 
				data-detail-id="${detail.id}" data-post-id="${detail.postId}" data-post-username="${detail.userName }" >게시</button> 
			</div>
		</div>

	</div>

</div>

<script>
	
	$(document).ready(function() {
		
		$("#comment").on('click', function() {
		    let detailId = $(this).data('detail-id');
		    let postId = $(this).data('post-id');
		    let commentValue = $("#commentValue").val();
		    // alert(detailId);
		    // alert(postId);
		    // alert(commentValue);

		    $.ajax({
		        type: "POST",
		        url: "/comment/create",
		        data: {
		            "detailId": detailId,
		            "postId": postId,
		            "content": commentValue
		        },
		        success: function(data) {
		            // 서버로부터 받은 응답에 따라 동적으로 처리
		            console.log(data); // 예시: 콘솔에 응답 로그 출력
		            // 이동할 필요가 있으면 여기서 처리
		        },
		        error: function(error) {
		            console.log(error);
		            alert("댓글 게시에 실패하였습니다.");
		        }
		    }); // ajax
		}); // comment

		
	});
	
</script>
