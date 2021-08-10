<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- Optional JavaScript -->
<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
	function login() {
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
		form.submit();
	}

	function userCreate(targetUri) {
		form.action = targetUri;
		form.submit();
	}
</script>
<title>Login</title>
</head>
<body>
	<div class="col-xs-6 col-sm-4 py-5 container">
		<form class="form-login" method="POST"
			action="<c:url value='/user/login' />" id="form">
			<h2 class="pb-2 text-center form-signin-heading">Wobby</h2>
			<!-- 비밀번호 찾기를 통해 온 경우 exception 객체에 저장된 오류 메시지를 출력 -->
			<div class="col-lg-12">
				<c:if test="${pwUpdated}">
					<h6 class="text-success">
						비밀번호가 정상적으로 변경되었습니다. <br>변경된 비밀번호로 다시 로그인해주세요.
					</h6>
				</c:if>
			</div>
			<label for="inputEmail" class="sr-only">아이디</label>
				<c:if test="${not empty cookie.savedId.value}">
					<input type="text" id="inputEmail" class="form-control" placeholder="아이디"
					name="userId" required autofocus value="${cookie.savedId.value}">
				</c:if>
				<c:if test="${empty cookie.savedId.value}">
					<input type="text" id="inputEmail" class="form-control" placeholder="아이디"
					name="userId" required autofocus >
				</c:if>
				
				<label for="inputPassword"
				class="sr-only"> 비밀번호 </label> <input type="password" id="password"
				name="password" class="form-control" placeholder="비밀번호" required>
			<div class="py-1 row">
				<div class="col-md-6 checkbox">
					<label> <input type="checkbox" id="blankCheckbox" name="idSaveCheck">
						<small> 아이디 기억</small>
					</label>
				</div>
				<div class="col-md-6">
					<p class="text-right">
						<a class="text-right"
							href="<c:url value='/user/findUserInfoForm.jsp'/>"> <small>아이디
								/ 비밀번호 찾기</small>
						</a>
					</p>
				</div>
			</div>
			<!-- 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
			<div class="col-lg-12">
				<c:if test="${loginFailed}">
					<h6 class="text-danger">
						<c:out value="${exception.getMessage()}" />
					</h6>
				</c:if>
			</div>
			<button class="btn btn-lg btn-primary btn-block" onClick="login()">로그인</button>
		</form>
		<div class="pt-1">
			<a href="<c:url value='/user/signUp/form'/>">
				<button class="btn btn-lg btn-info btn-block">가입하기</button>
			</a>
		</div>
	</div>
</body>
</html>