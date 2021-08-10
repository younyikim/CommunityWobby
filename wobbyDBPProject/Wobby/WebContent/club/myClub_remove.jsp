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
					<b><span class="text-info">* </span>내가 만든 클럽 목록</b>
				</h3>
				<!-- Blog Post -->
				<c:forEach var="club" items="${clubList}">
					<div class="card mb-4">
						<div class="card-body">
							<h2 class="card-title">${club.name}</h2>
							<p class="card-text">${club.clubId}</p>
							<a
								href="<c:url value='/board'>
                        <c:param name='boardId' value='${club.clubId}'/>
                     </c:url>"
								class="btn btn-primary">클럽 게시판 보기</a>
								</div>
						<div class="card-footer text-muted">
							<c:forEach var="category" items="${club.category}"> ${category} </c:forEach>
							| 시작일 ${club.startDate} | 회원 수
							${club.numOfMembers}/${club.maxNumMembers}명 | 지역 ${club.region}
							<a
								href="<c:url value='/club/removeClub'>
                        <c:param name='clubId' value='${club.clubId}'/>
                     </c:url>">
								> 클럽 삭제하기</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
	<!-- Footer -->
	<footer class="py-5" style="background-color: #04ADBF;">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; WOBBY 2020</p>
		</div>
		<!-- /.container -->
	</footer>
</html>