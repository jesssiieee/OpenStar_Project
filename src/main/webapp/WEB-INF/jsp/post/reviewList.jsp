<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

<div style="height: 1200px;" class="d-flex">
	
	<div style="width: 90%;" class="contents">
		<%-- 글 목록 뿌리기 --%>
		<c:forEach items="${reviewList }" var="review">
		
			<div class="review-item">
				<h3>${userName }</h3>
				<div class="d-flex">
					<img style="width: 200px; height: 200px;" src="${review.imagePath }">
					<h5>${review.content }</h5>
				</div>
				<h4>${review.rating }</h4>
			</div>
			
		</c:forEach>
	</div>
	
	<div style="width: 10%;" class="contents border-right-info">
		
		<a href="http://localhost/post/post-write-review">
			<button id="write-btn" class="btn btn-info float-right">글쓰기</button>
		</a>
	
	</div>

</div>
