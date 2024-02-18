<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<div class="content">

	<c:forEach items="${checkList}" var="check">
	 	<c:if test="${check.type eq 'like' }">
		dd
		</c:if> 
    </c:forEach>
    
</div>