
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- ${userId }  
${userName }   --%>

<h1 class="ml-3">My Page</h1>

<div class="d-flex">


    <%-- 왼쪽 20% --%>
    <div style="width: 20%;" class="content border-right-info">
    
    	<div class="ml-4">
	        <br><br><br><br>
	        <span class="ml-5" style="font-size: xx-large;">${userName }</span>
	        
	        <br><br>
	        <div class="ml-3 mt-3">
	        	<button id="like" class="btn btn-info ">좋아요</button>
   				<button id="bookmark" class="btn btn-success ">찜한 목록</button>
	        </div>
        </div>
    </div>
    
    <%-- 오른쪽 80% --%>
    <div style="width: 80%;" class="content d-flex">
    
<%--         <c:choose>
            <c:when test="${check.type eq 'like' }">
                <c:set value="${check.contentId }" var="contentId" />
                ㅇ
            </c:when>
            
            <c:when test="${check.type eq 'bookmark' }">
                <c:set value="${check.contentId }" var="contentId" />
            </c:when>
            
            <c:otherwise>
                <!-- 다른 어떤 경우에도 해당하지 않을 때 실행될 내용 -->
            </c:otherwise>
        </c:choose> --%>
        
    </div>
    


</div>

<script>
    $(document).ready(function () {
    	
    	$("#like").on('click', function() {
    		// alert("클릭");
    		
    		$.ajax ({
    			type: "GET"
    			, success: function(data) {
    				location.href = "http://localhost/post/post-like-list";
    			}
    			, error: function(error) {
    				console.log(error);
    			}
    		}); // ajax
    		
    	}); // like
    	
    	$("#bookmark").on('click', function() {
    		// alert("zmfflr");
    		
    		$.ajax ({
    			type: "GET"
    			, success: function(data) {
    				location.href = "http://localhost/post/post-bookmark-list";
    			}
    			, error: function(error) {
    				console.log(error);
    			}
    		}); // ajax
    		
    	});
    	
    });

</script>

