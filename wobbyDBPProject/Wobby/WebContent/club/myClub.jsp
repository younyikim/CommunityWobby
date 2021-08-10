<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
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
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- for Icon -->
<script src="https://kit.fontawesome.com/cfb4151af4.js"></script>
<script>
</script>
<title>My Club</title>
</head>
<body class="pt-5">
   <!-- Navigation -->
   <%@include file="/navbar.jsp" %>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<!-- Blog Entries Column -->
			<div class="col-md-8">			
				<h3 class="my-4">
					<b><span class="text-info">* </span>가입한 클럽 목록</b>
				</h3>
				<!-- Blog Post -->
				<c:forEach var="club" items="${clubList}">
					<div class="card mb-4">
						<div class="card-body">
							<h2 class="card-title">${club.name}</h2>
							<p class="card-text">${club.clubId}</p>
							<a
								href="<c:url value='/club/board'>
                        <c:param name='clubId' value='${club.clubId}'/>
                     </c:url>"
								class="btn btn-primary">클럽 게시판 보기</a>
						</div>
						<div class="card-footer text-muted">
							<c:forEach var="category" items="${club.category}"> ${category} </c:forEach>
							| 시작일 ${club.startDate} | 회원 수
							${club.numOfMembers}/${club.maxNumMembers}명 | 지역 ${club.region} <a
								href="<c:url value='/club/outClub'>
                        <c:param name='clubId' value='${club.clubId}'/>
                     </c:url>">
								> 클럽 탈퇴하기</a>
						</div>
					</div>
				</c:forEach>
				
				<hr>
				<a href="<c:url value='/club/manageMyClub'/>">
					<h3 class="my-4">
					<b><span class="text-info">* </span>내가 만든 클럽 목록</b>
				</h3></a>
				<hr>
				
				<!-- Pagination -->
				<ul class="pagination justify-content-center mb-4">
					<li class="page-item disabled"><a class="page-link" href="#">다음
							&rarr;</a></li>
				</ul>

			</div>

			<!-- Sidebar Widgets Column -->
			<div class="col-md-4">

				<!-- Search Widget -->
				<div class="card my-4">
					<h5 class="card-header">클럽 검색</h5>
					<div class="card-body">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="찾고 싶은 클럽을 검색하세요."> <span
								class="input-group-append">
								<button class="btn btn-secondary" type="button">검색</button>
							</span>
						</div>
					</div>
				</div>

				<div class="card my-4">
					<h5 class="card-header">지역 클럽 검색</h5>
					<div class="card-body">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="찾고 싶은 지역을 입력하세요."> <span
								class="input-group-append">
								<button class="btn btn-secondary" type="button">검색</button>
							</span>
						</div>
					</div>
				</div>

				<!-- Categories Widget -->
				<div class="card my-4">
					<h5 class="card-header">인기 카테고리</h5>
					<div class="card-body">
						<div class="row">
							<div class="col-lg-6">
								<ul class="list-unstyled mb-0">
									<li><a href="#">운동</a></li>
									<li><a href="#">독서</a></li>
									<li><a href="#">음악</a></li>
								</ul>
							</div>
							<div class="col-lg-6">
								<ul class="list-unstyled mb-0">
									<li><a href="#">요리</a></li>
									<li><a href="#">영상</a></li>
									<li><a href="#">영어</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>

				<!-- Side Widget -->


			</div>

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->
</body>
	<!-- Footer -->
	<footer class="py-5" style="background-color: #04ADBF;">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; WOBBY 2020</p>
		</div>
		<!-- /.container -->
	</footer>
</html>