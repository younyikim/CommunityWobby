<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Optional JavaScript -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- Optional JavaScript -->
<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- for Icon -->
<script src="https://kit.fontawesome.com/cfb4151af4.js"></script>
<script>
	function userCreate() {
		if (form.userId.value == "") {
			alert("사용자 ID를 입력하십시오.");
			form.userId.focus();
			return false;
		}
		if (form.password.value == "") {
			alert("비밀번호를 입력하십시오.");
			form.password.focus();
			return false;
		}
		if (form.password.value != form.password2.value) {
			alert("비밀번호가 일치하지 않습니다.");
			form.name.focus();
			return false;
		}
		if (form.name.value == "") {
			alert("이름을 입력하십시오.");
			form.name.focus();
			return false;
		}
		if (form.userId.value == "") {
			alert("닉네임를 입력하십시오.");
			form.nickname.focus();
			return false;
		}
	    if (hobbyChxCnt() == 0){
			return false;
	    }
		if (form.phone1.value == "") {
			alert("전화번호를 모두 입력하십시오.");
			form.phone1.focus();
			return false;
		}
		if (form.phone2.value == "") {
			alert("전화번호를 모두 입력하십시오.");
			form.phone2.focus();
			return false;
		}
		if (form.phone3.value == "") {
			alert("전화번호를 모두 입력하십시오.");
			form.phone3.focus();
			return false;
		}
		if (form.region1.value == "") {
			alert("지역을 모두 입력하십시오.");
			form.region1.focus();
			return false;
		}
		if (form.region2.value == "") {
			alert("지역을 모두 입력하십시오.");
			form.region2.focus();
			return false;
		}
		if (form.region3.value == "") {
			alert("지역을 모두 입력하십시오.");
			form.region3.focus();
			return false;
		}
		var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		if (emailExp.test(form.email.value) == false) {
			alert("이메일 형식이 올바르지 않습니다.");
			form.email.focus();
			return false;
		}
		form.submit();
	}

	function userList(targetUri) {
		form.action = targetUri;
		form.submit();
	}
	
	function hobbyChxCnt() {
		var chk_obj = document.getElementsByName("btn");
	    var chk_leng = chk_obj.length;
	    var checked = 0;
	    for (i=0; i < chk_leng; i++) {
	        if (chk_obj[i].checked == true) {
	            checked += 1;
	        }
	    }
	    if (checked != 3 ) {
	        alert("취미를 3개 선택해주세요");
	        return 0;
	    }
	    return 1;
	}
</script>
<title>SignUp</title>
<style type="text/css">
.dropdown-menu {
	max-height: 200px;
	overflow-y: scroll;
}
</style>
</head>
<body>
	<div class="col-xs-6 col-sm-4 py-5 container">
		<form class="form-signin" method="POST"
			action="<c:url value='/user/signUp' />" id="form">
			<h2 class="pb-2 text-center form-signin-heading">Wobby</h2>
			<div class="form-group">
				<label for="inputName">아이디</label>
					<!-- 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
					<c:if test="${ExistingUserException}">
						<h6 class="text-danger">
							<c:out value="${exception.getMessage()}" />
						</h6>
					</c:if><input type="text"
					class="form-control" name="userId" required autofocus placeholder="">
			</div>
			<div class="form-group">
				<label for="inputPassword">비밀번호</label> <input type="password"
					class="form-control" name="password" required autofocus placeholder="">
			</div>
			<div class="form-group">
				<label for="inputPasswordCheck">비밀번호 확인</label> <input
					type="password" class="form-control" required autofocus name="password2"
					placeholder="">
			</div>
			<div class="form-group">
				<label for="inputName">이름</label> <input type="text"
					class="form-control" name="name" required autofocus placeholder="">
			</div>
			<div class="form-group">
				<label for="inputName">닉네임</label> <input type="text"
					class="form-control" name="nickname" required autofocus placeholder="">
			</div>
			<div class="form-group">
				<label for="InputEmail">이메일 주소</label> <input type="email"
					class="form-control" name="email" required autofocus placeholder="example@example.com">
			</div>
			<div class="form-group">
				<label for="inputName">핸드폰 번호</label>
				<div class="row">
					<div class="col-md-4">
						<input type="text" class="form-control" name="phone1"
							required autofocus placeholder="">
					</div>
					<div class="col-md-4">
						<input type="text" class="form-control" name="phone2"
							required autofocus placeholder="">
					</div>
					<div class="col-md-4">
						<input type="text" class="form-control" name="phone3"
							required autofocus placeholder="">
					</div>
				</div>
			</div>

			<div class="pt-2 form-group">
				<label for="selectHobby"><i class="fab fa-diaspora"></i> 취미
					선택 <small> <br>당신의 취미 3가지를 선택하세요. 이를 기반으로 맞춤 클럽과 전문가를
						추천해드립니다!<br></small></label>
				<div class="btn-group btn-group-toggle pt-1 btn-block"
					data-toggle="buttons">
					<label class="btn btn-light"> <input type="checkbox"
						name="btn" value="1"><i class="fas fa-palette"></i><br>
						<small>예술</small>
					</label> <label class="btn btn-light"> <input type="checkbox"
						name="btn" value="2"><i class="fas fa-book-reader"></i><br>
						<small>독서</small>
					</label> <label class="btn btn-light"> <input type="checkbox"
						name="btn" value="3"><i class="fas fa-gamepad"></i><br>
						<small>게임</small>
					</label> <label class="btn btn-light"> <input type="checkbox"
						name="btn" value="4"><i class="fas fa-pizza-slice"></i><br>
						<small>요리</small>
					</label> <label class="btn btn-light"> <input type="checkbox"
						name="btn" value="5"><i class="fas fa-dumbbell"></i><br>
						<small>운동</small>
					</label> <label class="btn btn-light"> <input type="checkbox"
						name="btn" value="6"><i class="fas fa-chart-line"></i><br>
						<small>재테크</small>
					</label> <label class="btn btn-light"> <input type="checkbox"
						name="btn" value="7"><i class="fas fa-language"></i><br>
						<small>스터디</small>
					</label> <label class="btn btn-light"><input type="checkbox"
						name="btn" value="8"><i class="fas fa-icons"></i><br>
						<small>친목</small> </label>
				</div>
			</div>
			
			<div class="pt-2 form-group">
				<label for="selectHobby"><i class="fab fa-diaspora"></i> 성향
					선택 <small> <br>당신의 MBTI를 선택하세요. 당신의 성향에 맞는 취미를
						추천해드립니다.<br></small></label>
				<div class="btn-group btn-group-toggle pt-1 btn-block"
					data-toggle="buttons">
					<label class="btn btn-light"> <input type="radio" name="IE"
						value="E">E
					</label> <label class="btn btn-light"> <input type="radio"
						name="IE" value="I">I
					</label>
				</div>
				<div class="btn-group btn-group-toggle pt-1 btn-block"
					data-toggle="buttons">
					<label class="btn btn-light"> <input type="radio" name="NS"
						value="N">N
					</label> <label class="btn btn-light"> <input type="radio"
						name="NS" value="S">S
					</label>
				</div>
				<div class="btn-group btn-group-toggle pt-1 btn-block"
					data-toggle="buttons">
					<label class="btn btn-light"> <input type="radio" name="TF"
						value="F">F
					</label> <label class="btn btn-light"> <input type="radio"
						name="TF" value="T">T
					</label>
				</div>
				<div class="btn-group btn-group-toggle pt-1 btn-block"
					data-toggle="buttons">
					<label class="btn btn-light"> <input type="radio" name="PJ"
						value="P">P
					</label> <label class="btn btn-light"> <input type="radio"
						name="PJ" value="J">J
					</label>
				</div>
			</div>
			<div class="pt-2 form-group">
				<label for="selectHobby"><i class="fab fa-diaspora"></i> 지역
					선택<small> <br>당신의 지역을 선택하세요. 당신의 지역에서 친구와 클럽을 찾아드립니다.<br></small></label>
				<div class="row">
					<div class="col-md-4">
						<input type="text" class="form-control" name="region1"
							placeholder="시도">
					</div>
					<div class="col-md-4">
						<input type="text" class="form-control" name="region2"
							placeholder="시군구">
					</div>
					<div class="col-md-4">
						<input type="text" class="form-control" name="region3"
							placeholder="읍면동">
					</div>
				</div>
			</div>

			<div class="pt-2 pb-5">
				<div class="checkbox">
					<label> <input type="checkbox" value=""> <a
						href="#">이용약관</a>에 동의합니다.
					</label>
				</div>
				<button type="button" class="btn btn-lg btn-primary btn-block" onClick="userCreate()" >회원가입</button>
			</div>
		</form>
	</div>
</body>
</html>