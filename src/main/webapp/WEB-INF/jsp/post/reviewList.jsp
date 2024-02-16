<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

<div style="height: 1200px;" class="">
	
	<div style="height: 900px;" class="contents border-bottom-black">
		<%-- 글 목록 뿌리기 --%>
		<c:forEach items="${reviewList }" var="review">
			<h4>${review.rating }</h4>
			<img alt="" src="${review.imagePath }">
		
		</c:forEach>
	</div>
	
	<div>
		
		<a href="http://localhost/post/post-write-review">
			<button id="write-btn" class="btn btn-info float-right">글쓰기</button>
		</a>
	
	</div>

</div>
