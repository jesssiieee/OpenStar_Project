<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

<c:if test="${not empty movieTrendResultList}">
	<!-- movieTrendResultList가 비어 있지 않은 경우에만 이 부분이 실행됩니다 -->
	<h4 class="mt-3 ml-5">${movieTrendResultList.originalTitle}</h4>
</c:if>

<c:if test="${not empty multiResultList}">
	<!-- multiResultList가 비어 있지 않은 경우에만 이 부분이 실행됩니다 -->
	<c:forEach var="multiResult" items="${multiResultList}"></c:forEach>
</c:if>

<c:if test="${not empty movieTrendList}">
	<!-- movieTrendList가 비어 있지 않은 경우에만 이 부분이 실행됩니다 -->
</c:if>

<c:if test="${not empty tvTrendList}">
	<!-- tvTrendList가 비어 있지 않은 경우에만 이 부분이 실행됩니다 -->
</c:if>

