<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="content">

	<c:forEach items="${checkList}" var="check">
	 	<c:if test="${check.type eq 'bookmark' }">
		dd
		</c:if> 
    </c:forEach>
    
</div>
