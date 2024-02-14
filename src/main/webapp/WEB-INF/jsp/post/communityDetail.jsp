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
			<button id="comment" class="btn btn-success" data-detail-id="${detail.id}" data-post-id="${detail.postId}">댓글게시</button>
		</div>

	</div>

</div>

<script>
	
	$(document).ready(function() {
		
		$("#comment").on('click', function() {
			// alert("클릭");
			let detailId = $(this).data('detail-id');
			let postId = $(this).data('post-id');
			// alert(detailId);
			alert(postId);
		});
		
	});
	
</script>
