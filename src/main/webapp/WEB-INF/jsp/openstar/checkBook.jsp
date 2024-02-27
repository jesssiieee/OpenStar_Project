<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

 <div class="content">
	
	<table class="table">
		
		<thead>
			<tr>
				<th>영화제목</th>	
				<th>예매 인원 수</th>	
				<th>상영 시작 시간</th>	
				<th>좌석 번호</th>	
				<th>가격</th>			
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${checkBookList }" var="check">
				<tr>
					<td>${check.title }</td>
					<td>${check.headcount }</td>
					<td>${check.showtime }</td>
					<td>${check.selectedSeats }</td>
					<td>${check.price }</td>
				</tr>
			</c:forEach>
		</tbody>
		
		
	</table>
	
</div>   