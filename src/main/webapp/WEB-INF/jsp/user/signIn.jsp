<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="d-flex justify-content-center">
	<div class="sign-in-box">
		<h1>로그인</h1>

		<form id="loginForm" action="/user/sign-in" method="post">
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">ID</span>
				</div>
				<input type="text" class="form-control" id="loginId" name="loginId">
			</div>

			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">PW</span>
				</div>
				<input type="password" class="form-control" id="password" name="password">
			</div>
			
			<input type="submit" id="loginBtn" class="btn btn-block btn-primary" value="로그인" >
			<a class="btn btn-block btn-dark" href="/user/sign-up-view">회원가입</a>
		</form>

	</div>
</div>

<script>
    $(document).ready(function() {
        $("#loginForm").on('submit', function(e) {
            e.preventDefault();
            
            let loginId = $("#loginId").val().trim();
            let password = $("#password").val();
            
            if (!loginId) {
                alert("아이디를 입력하세요.");
                return false;
            }
            
            if (!password) {
                alert("비밀번호를 입력하세요.");
                return false;
            }
            
            let url = $(this).attr("action");
            console.log(url);
            let params = $(this).serialize();
            console.log(params);
            
            $.post(url, params)
            .done(function(data) {
                if (data.code === 200) {
                    location.href = "/openstar/first-view";
                } else {
                    alert(data.error_message);
                }
            }); // login ajax
        }); //loginBtn
    }); // ready
</script>
