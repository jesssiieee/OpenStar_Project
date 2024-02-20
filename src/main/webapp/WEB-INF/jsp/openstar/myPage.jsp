
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
    <div style="width: 80%;" class="content ">
    
		<div style="width: 1000px; height: 300px;" class="ml-5 mt-5 border-bottom-black">
		
			<h2>작성한 리뷰 목록</h2>
			<c:forEach items="${postList }" var="post">
				
				<div style="width: 800px; heigth">
					<h5>${post.userName }</h5>
					<span>${post.content }</span>
				</div>
				
			</c:forEach>
		
		</div>
		<div style="width: 1000px; height: 400px;" class="ml-5 mt-5"> 
		
			<h2>작성한 커뮤니티 글 목록</h2>
			 <c:forEach items="${reviewList }" var="review">
			 	
				<div style="width: 800px; heigth">
					<h5>${review.userName }</h5>
					<span>${review.content }</span>
				</div>
			
			</c:forEach>
			
			
		</div>
        
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

