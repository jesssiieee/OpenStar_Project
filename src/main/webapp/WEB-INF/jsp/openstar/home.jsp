<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="justify-content-center mt-5" style="overflow-x: auto;">
    <div class="flex-container">

        <c:forEach items="${movieTrendList}" var="mTL">
            <div class="flex-item">
                <img src="${mTL.posterPath}" alt="Poster" class="poster-img" style="width:200px; height: 288px;">
            </div>
        </c:forEach>

    </div>
</div>
