<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex justify-content-center mt-5">
	<div class="first-page d-flex justify-content-space-between">

		${personResults }

		<c:forEach var="person" items="${personResults[0]}">
			<c:forEach items="${person }" var="map">
				${map[id] }
			</c:forEach>
		</c:forEach>


	</div>
</div>

