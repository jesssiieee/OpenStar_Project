<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex">
	<%-- 왼쪽 20% --%>
	<div style="width: 30%;" class="contents border-right-info">
		<c:set var="detail" value="${getCommunityPost}" />
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
			
			<!--<c:set var="comment" value="${getCommentList}"/> -->
			<c:forEach items="${getCommentList }" var="comment">
				<div class="d-flex">
				<a href="#" class="comment-del-btn" data-comment-id="${comment.id }" >
					<img src="/static/image/x-icon.png" width="10" height="10">
				</a>
					<h5>${comment.content }</h5>
				</div>
			</c:forEach>
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
		
		<%-- 댓글 달기 --%>
		$("#comment").on('click', function(e) {
			
			e.preventDefault(); // 위로 올라감 방지
			
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

		$('.comment-del-btn').on('click', function(e) {
			
			e.preventDefault(); // 위로 올라감 방지
			
			// alert('클릭');
			let commentId = $(this).data('comment-id');
			// alert(commentId);
			
			$.ajax ({
				// request
				type:"DELETE"				
				, url:"/comment/delete"
				, data:{"commentId":commentId}
				// response
				, success:function(data) {
					if (data.code==200) {
						location.reload(true);
						alert("댓글 삭제에 성공하였습니다.");
					} else {
						alert(data.error_message);
					}
				}
				, error(request, status, error) {
					alert("댓글 삭제에 실패하였습니다.");
				}
			}); // comment-del-btn ajax
			
		}); // comment-del-btn
		
	});
	
</script>
