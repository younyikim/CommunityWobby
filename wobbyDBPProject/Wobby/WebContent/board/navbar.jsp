<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="bs-example-navbar-collapse-1"
				aria-expaned="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>

			</button>

		</div>

		<div class="collapse navbar-collapse"
			id="#bs-example-navbar-collapse-1">

			<ul class="nav navbar-nav">

				<li><a href="<c:url value='/home' />">메인</a></li>

			</ul>

			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a> <c:if
						test="${curUserId == null}">
						<ul class="dropdown-menu">
							<li><a href="<c:url value='/user/login/form' />">로그인</a></li>

							<li><a href="<c:url value='/user/register/form' />">회원가입</a></li>
						</ul>
					</c:if> <c:if test="${curUserId != null}">
						<ul class="dropdown-menu">
							<li><a href="<c:url value='/user/logout' />">로그아웃</a></li>
						</ul>
					</c:if></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown"><c:if test="${curUserId == 'admin'}">
						<a href="<c:url value='/admin/post' />" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">회원관리<span class="caret"></span></a>
					</c:if>
					<ul class="dropdown-menu">

						<li><a href="<c:url value='/user/logout' />">로그아웃</a></li>
					</ul></li>
			</ul>

		</div>
	</nav>
</body>
</html>