<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
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
<title>Matching</title>
</head>
<body>
   <!-- Navigation -->
   <%@include file="/navbar.jsp" %>

	<!-- Header -->
	<!-- Header -->
	<header class="bg-info py-5 mb-5">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-lg-12">
					<h1 class="display-4 text-white mt-5 mb-3">
						<b>Matching</b></i>
					</h1>
					<h6 class="lead mb-5 text-white-50">
						<b>${userId}</b>님이 좋아하실 것 같은 클럽들을 매칭해드려요.
					</h6>
				</div>
			</div>
		</div>
	</header>

	<!-- Page Content -->
	<div class="container col-md-8 mb-5">
		<!-- CARD CONTENT -->
		<table class="table table-hover">
			<tr>
				<td><div class="container mb-5">
						<h2>
							<b><span class="text-info">* </span></b>MBTI
						</h2>
						<hr>
						<h6 class="text-black-70">▶ ${userId}님와 같은 성격 유형을 갖고있는 회원들이
							많이 가입한 클럽을 찾아드립니다.</h6>
						<br> <a href="<c:url value='/matching'>
                        <c:param name='type' value='mbti'/>
                        </c:url>"
							class="btn btn-primary">매칭</a>
					</div></td>
			</tr>
			<tr>
				<td><div class="container mb-5">
						<h2>
							<b><span class="text-info">* </span>지역</b>
						</h2>
						<hr>
						<h6 class="text-black-70">▶ ${userId}님이 살고있는 지역에서 활동하는 클럽을
							찾아드립니다.</h6>
						<br> <a
							href="<c:url value='/matching'>
                        <c:param name='type' value='region'/>
                     </c:url>"
							class="btn btn-primary">매칭</a>
						
					</div></td>
			</tr>
		</table>
	</div>

   <!-- Footer -->
	<footer class="py-5" style="background-color: #04ADBF;">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; WOBBY 2020</p>
		</div>
		<!-- /.container -->
	</footer>
</body>
</html>