<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="d-flex justify-content-center">
	<div class="sign-up-box">
		
		<h1 class="mb-4">회원가입</h1>
		 <form id="signUpForm" method="post" action="/user/sign-up">
						<table class="sign-up-table table table-bordered">
				<tr>
					<th>* 아이디(4자 이상)<br></th>
					<td>
						<%-- 인풋박스 옆에 중복확인을 붙이기 위해 div를 하나 더 만들고 d-flex --%>
						<div class="d-flex">
							<input type="text" id="loginId" name="loginId" class="form-control col-9" placeholder="아이디를 입력하세요.">
							<button type="button" id="loginIdCheckBtn" class="btn btn-success">중복확인</button><br>
						</div>
						
						<%-- 아이디 체크 결과 --%>
						<%-- d-none 클래스: display none (보이지 않게) --%>
						<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
						<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
						<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
					</td>
				</tr>
				<tr>
					<th>* 비밀번호</th>
					<td><input type="password" id="password" name="password" class="form-control" placeholder="비밀번호를 입력하세요."></td>
				</tr>
				<tr>
					<th>* 비밀번호 확인</th>
					<td><input type="password" id="confirmPassword" class="form-control" placeholder="비밀번호를 입력하세요."></td>
				</tr>
				<tr>
					<th>* 이름</th>
					<td><input type="text" id="name" name="name" class="form-control" placeholder="이름을 입력하세요."></td>
				</tr>
				<tr>
					<th>* 이메일</th>
					<td><input type="text" id="email" name="email" class="form-control" placeholder="이메일 주소를 입력하세요."></td>
				</tr>
			</table>
			<br>
		
			<button type="submit" id="signUpBtn" class="btn btn-primary float-right">회원가입</button>
		</form>
		
	</div>
</div> 

<script>
	
	$(document).ready(function() {
		
		// 로그인 중복확인
		$('#loginIdCheckBtn').on('click', function() {
			
			// alert("중복확인");
			
			// 경고문구 초기화
			 $('#idCheckLength').addClass("d-none");
			 $('#idCheckDuplicated').addClass("d-none");
			 $('#idCheckOk').addClass("d-none");
			 
			 let loginId = $("#loginId").val().trim();
			 if (loginId.length < 4) {
				 $("#idCheckLength").removeClass("d-none");
				 return;
			 }
			 
			 // ajax 중복확인
			 $.get("/user/is-duplicated-id", {"loginId":loginId}) // request
			 .done(function(data) { // response
				 if (data.code == 200) {
					 if (data.is_duplicated_id) {
						 $("#idCheckDuplicated").removeClass('d-none');
					 } else  {
						 $("#idCheckOk").removeClass("d-none");
					 }
				 } else {
					 alert(data.error_messsage);
				 }
			 }); // duplicated ajax
		}); // loginIdCheckBtn 
		
		// 회원가입 form태그
		$('#signUpForm').on('submit', function(e) {
			e.preventDefault(); // submit 기능(화면 이동) 안됨.
			// alert("회원가입");
			
			let loginId = $("#loginId").val().trim();
			let password = $("#password").val();
			let confirmPassword = $("#confirmPassword").val();
			let name = $("#name").val().trim();
			let email = $("#email").val().trim();
			
			if (!loginId) {
				alert("아이디를 입력하세요.");
				return false;
			}
			
			if (!password || !confirmPassword) {
				alert("비밀번호를 입력하세요.");
				return false;
			}
			
			if (password != confirmPassword) {
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
			
			if (!name) {
				alert("이름을 입력하세요.");
				return false;
			}
			
			if (!email){
				alert("이메일을 입력하세요.");
				return false;
			}
			
			// idCheckOk에 d-none이 없는지 확인
			if ($("#idCheckOk").hasClass('d-none')) {
				alert("아이디 중복확인을 다시 해주세요.");
				return false;
			}
			
			let url = $(this).attr("action");
			console.log(url);
			
			// form 태그의 name 속성을 활용, 비밀번호 확인은 넘겨줄 필요 없으므로 name 속성없음.
			let params = $(this).serialize();
			console.log(params);
			
			$.post(url, params)
			.done(function(data) {
				if (data.code == 200) {
					alert("회원가입에 성공하였습니다. 로그인해주세요.");
					location.href = "/user/sign-in-view";
				} else {
					alert(data.error_message);
				}
			}); // 회원가입 ajax
			
		}); //signUpForm
		
	}); // ready
	
</script>