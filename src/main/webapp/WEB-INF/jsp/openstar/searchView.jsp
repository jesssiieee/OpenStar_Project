<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex">

	<div style="width: 20%;" class="contents border-right-info">
		<c:forEach var="personResult" items="${personResultList}">
			<div>
				<img src="${personResult.profilePath}" alt="Poster"
					class="poster-img mt-3 ml-5" style="width: 200px; height: 288px;">
				<h4 class="mt-3 ml-5" >${personResult.originalName}</h4>
\			</div>
	</div>


	<div style="width: 80%;" class="contents d-flex">
		<c:forEach var="knownFor" items="${personResult.knownFor}">
			<div class="inline-item">

				<a
					href="http://localhost/openstar/search-view/detail/${personResult.originalName }">
					<img src="${knownFor.posterPath}" alt="Poster" class="poster-img"
					style="width: 200px; height: 288px;" data-content-id="${knownFor.id }"
					data-original-name="${personResult.originalName}" >
				</a>
				<h5 class="mt-3">작품 제목: ${knownFor.name}</h5>
				<h5 class="mt-3">공개 날짜: ${knownFor.firstAirDate}</h5>
				<h5 class="mt-3">평 점: ${knownFor.voteAverage}</h5>

			</div>
		</c:forEach>
	</div>

	</c:forEach> 
</div>

<script>
    $(document).ready(function() {
        // 이미지 클릭 이벤트 핸들러
        $('.poster-img').click(function() {
            let contentId = $(this).data('content-id');
            let searchActorName = $(this).data('original-name');
            // alert(contentId);
            // alert(searchActorName);
            
             if (contentId != '') {
            	$.ajax ({
            		type: "GET"
           			, success: function(result) {
                        location.href = "/openstar/search-view/detail/" + encodeURIComponent(searchActorName) + "?contentId=" + contentId;
   					}
   					, error: function(error) {
   						console.log(error);
   						alert("검색에 실패하였습니다.");
   					}
            	});  // ajax
            } 
            
        }); // click poster-img
    }); // ready
</script>










