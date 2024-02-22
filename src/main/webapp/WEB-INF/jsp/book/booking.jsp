<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex justify-content-center">
	<div class="sign-in-box">
	
		<h1 class="mb-5">예매</h1>

		<form id="bookForm" action="/book/booking-content" method="post">
			<div class="input-group mb-3 ">
				
				<div class="">
				
					<div class="d-flex">
						<h3>영화관</h3>
						 <select style="width:100px; font-size:22px;" name="location" class="form-control ml-5">
							<option value="CGV">CGV</option>
							<option value="메가박스">메가박스</option>
						</select> 
					</div>
					<br><br><br>
					
					<div class="d-flex">
						<h3>날짜 선택</h3>
						<input class="form-control ml-5" type="text" id="datepicker" >
					</div>
					<br><br><br>
					
					<div class="d-flex">
						<h3>제목</h3> 
						<h4 id="title">${movieTrendResultList.originalTitle }</h4>
						
					</div>
					<br><br><br>
					
					<div>
					    <h3>상영시간</h3>
					    <div class="d-flex form-check">
					        <input type="radio" name="chk_info" value="13">13:00 &nbsp; &nbsp;
					        <input type="radio" name="chk_info" value="17">17:00 &nbsp; &nbsp;
					        <input type="radio" name="chk_info" value="21">21:00 &nbsp; &nbsp;
					    </div>
					</div>
					<br><br><br>
					
					<div class="d-flex">
						
						<h4>성인</h4>
					  	<select style="width:100px; font-size:22px;" name="adult" class="form-control ml-5">
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select> 
						
				        &nbsp;&nbsp;&nbsp;&nbsp;
				        <h4>어린이</h4>
					  	<select style="width:100px; font-size:22px;" name="child" class="form-control ml-5">
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select> 
						
					</div>
					
					<h5 id="total-price" style="color: red"></h5>
					
					<br><br>
					
				    <div class="container">
				        <h2>좌석 선택</h2>
				        <div id="seat-map">
				            <!-- 좌석 배치도 -->
				        </div>
				        <button id="checkout-btn" data-movie-id="${movieTrendResultList.id }">결제하기</button>
				    </div>
					
					
				</div>	
				
								
			</div>
		</form>
	
	</div> 
</div>

<script>

	$(document).ready(function() {
		
		$("#datepicker").datepicker({
			minDate: 0
		}); // datepicker
		
        // JavaScript 코드, 좌석 선택 기능
        const seatMap = document.getElementById('seat-map');

        // 가상의 좌석 배치도를 생성합니다.
        const rows = 5; // 행 수
        const seatsPerRow = 10; // 행당 좌석 수

        for (let i = 1; i <= rows; i++) {
            const rowElement = document.createElement('div');
            rowElement.classList.add('row');
            for (let j = 1; j <= seatsPerRow; j++) {
                const seatElement = document.createElement('div');
                seatElement.classList.add('seat');
                seatElement.dataset.seatNumber = i + '-' + j;  // 좌석 번호 설정
                rowElement.appendChild(seatElement);
            }
            seatMap.appendChild(rowElement);
        } 

        // 좌석을 선택하거나 해제하는 함수
        function toggleSeatSelection(seatElement) {
            seatElement.classList.toggle('selected');
        }

        // 좌석 선택 이벤트 리스너 추가
        seatMap.addEventListener('click', function(event) {
            if (event.target.classList.contains('seat')) {
                toggleSeatSelection(event.target);
            }
        }); 

		// js 총금액 계산
		
        // 성인 1명당 금액
        const adultPrice = 8000;
        // 어린이 1명당 금액
        const childPrice = 6000;

        // 선택한 성인과 어린이 수를 가져오는 함수
        function calculateTotal() {
            // 선택한 성인 수
            const adultCount = parseInt($('select[name="adult"]').val());
            // 선택한 어린이 수
            const childCount = parseInt($('select[name="child"]').val());
            
            // 총 금액 계산
            const totalPrice = adultCount * adultPrice + childCount * childPrice;
            
            // 총 금액을 HTML에 업데이트
            $('#total-price').text(totalPrice);
        }

        // 페이지 로드 시 초기 총 금액 계산
        calculateTotal();

        // select 박스가 변경될 때마다 총 금액을 다시 계산
        $('select[name="adult"], select[name="child"]').change(function() {
            calculateTotal();
        });
        
        $('#checkout-btn').click(function(event) {
        	
        	//event.preventDefault();
        	
        	//alert("클릭");
        	
            // 선택한 좌석 정보를 추적합니다.
            var selectedSeats = [];
            $('.seat.selected').each(function() {
                var seatNumber = $(this).data('seatNumber');
                selectedSeats.push(seatNumber);
            });
            // alert(selectedSeats);
            // 선택된 좌석을 "1-1", "1-2"와 같은 형식의 문자열로 변환합니다.
            var selectedSeatString = selectedSeats.join(', ');
            // alert(selectedSeatString); 
            
/*             const selectedSeats = document.querySelectorAll('.seat.selected');
            // alert(${selectedSeats});
            const selectedSeatNumbers = Array.from(selectedSeats).map(seat => seat.dataset.seatNumber);
            // alert(`선택한 좌석: ${selectedSeatNumbers.join(', ')}`);
            alert(selectedSeatNumbers); */
            
            let movieId = $(this).data('movie-id');
            // alert(movieId);

            // 각 필드에서 값을 가져옵니다.
            var location = $('select[name="location"]').val();
           	// alert(location);
            var date = $('#datepicker').val();
            // alert(date);
            var title = $('#title').text();
            // alert(title);
            var showtime = $('input[name="chk_info"]:checked').val();
            // alert(showtime);
            var adult = $('select[name="adult"]').val();
            // alert(adult);
            var child = $('select[name="child"]').val();
            // alert(child);
            var totalPrice = parseInt($('#total-price').text(), 10); // 10진수로 파싱
            // alert(totalPrice);
            
            
            // 서버에 전송할 데이터를 객체로 만듭니다.
            var requestData = {
            	movieId: movieId,
                location: location,
                date: date,
                title: title,
                showtime: showtime,
                adult: adult,
                child: child,
                selectedSeats: selectedSeatString, // 선택한 좌석 정보 추가
                price: totalPrice
            };
            
            console.log(requestData);

            $.ajax({
                type: 'POST',
                url: '/book/booking-content',
                contentType: 'application/json', // JSON 형식으로 데이터 전송
                data: JSON.stringify(requestData), // JSON 형식으로 데이터 변환
                success: function(result) {
                    // 서버로부터의 응답을 처리합니다.
                    console.log('서버 응답:', response);
                    // alert("영화 예약에 성공하였습니다.");
                    location.href = "http://localhost/openstar/search-view/trend/" + ${movieTrendResultList.id };
                },
                error: function(xhr, status, error) {
                    // 오류 발생 시 처리합니다.
                    console.error('오류:', error);
                }
            });

        }); //checkout

        
		
	}); // ready

</script>