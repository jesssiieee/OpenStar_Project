<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

 <div class="content">
 
 	
 	<c:forEach items="${checkBookList }" var="check">
	 	<div class="review-item">
	 		<h3>${check.title }</h3>
	 		
	 		<div class="d-flex">
	 			<h4>${check.date }</h4>
	 			<h4 class="ml-5">${check.showtime }</h4>
	 		</div>
	 		
	 		<h4>${check.selectedSeats }</h4>
	 		
	 	</div>
	</c:forEach>
	
	
</div>   