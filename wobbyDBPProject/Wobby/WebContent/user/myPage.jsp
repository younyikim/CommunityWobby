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
<title>마이페이지</title>
</head>
<body class="pt-5">
	<!-- Navigation -->
   <%@include file="/navbar.jsp" %>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h2>
				<b>${userId}</b>님의 마이페이지
			</h2>
			<p>
				<a class="btn btn-light btn-sm"
					role="button" href="<c:url value='/user/delete'/>">회원 탈퇴 ></a>
			</p>
			<p>
				<c:if test="${deleteFailed}">
					<h6 class="text-danger">
						<c:out value="${exception}" />
					</h6>
				</c:if>
			</p>
		</div>
	</div>

	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
			<div class="col-md-4">
				<div class="pb-2">
					<h4>
						<b>알림</b>
					</h4>
					<i class="far fa-bell"></i> <span class="text-primary"><b>${alarmNum}</b></span>개의
					새로운 알림이 있습니다.<br>
				</div>
				<div class="pb-3">
					<small> <c:forEach var="alarm" items="${alarmList}"
							varStatus="status">
							<a class="text-muted pb-1"
								href="<c:url value='/community/post'>
                              <c:param name='boardId' value='${alarm.boardId}'/>
                              <c:param name='postId' value='${alarm.postId}'/>
                           </c:url>"
								class="btn btn-primary"><i class="fas fa-angle-right "></i>
								<b>[${alarm.title}]</b>에 댓글 <b>[${alarm.commentContent}]</b>이
								달렸습니다.</a>
						</c:forEach>
					</small>
				</div>
				<p>
					<a class="btn btn-light"
						href="<c:url value='/user/myPage/alarm/list'/>" role="button">알림
						더 보기</a>
				</p>
			</div>
			<div class="col-md-4">
				<div class="pb-2">
					<h4>
						<b>쪽지</b>
					</h4>
					<i class="far fa-envelope"></i> <span class="text-primary"><b>0</b></span>개의
					새로운 쪽지가 도착했습니다.<br>
				</div>
				<div>
					<small>점검 중 입니다.</small>
				</div>
				<!-- <div class="pb-3">
	      <small>
			<a class="text-muted pb-1"><i class="fas fa-angle-right "></i>
				<b>명지 </b> | 20.10.06  |  홈 UI 파일 <br></a>
			<a class="text-muted pb-1"><i class="fas fa-angle-right "></i>
				<b>지원 </b> | 20.10.05  |  팀프로젝트 게시판에..<br></a>
			<a class="text-muted pb-1"><i class="fas fa-angle-right "></i>
				<b>연이 </b> | 20.10.01  |  다음 회의<br></a>
		  </small>
		  </div> 
		  <p><a class="btn btn-light" href="<c:url value='/user/myPage/message/list'/>" role="button">쪽지 더 보기</a></p>-->
			</div>
			<div class="col-md-4">
				<div class="pb-2">
					<h4>
						<b>스크랩함</b>
					</h4>
					<i class="far fa-star"></i> <span class="text-primary"><b>${scrapNum}</b></span>개의
					새로운 스크랩이 추가되었습니다.<br>
				</div>
				<div class="pb-3">
					<small> <c:forEach var="scrapPost" items="${scrapList}"
							varStatus="status">
							<a class="text-muted pb-1"><i class="fas fa-angle-right "></i>
								<b>${scrapPost.boardName} </b> | ${scrapPost.title}<br></a>
						</c:forEach>
					</small>
				</div>
				<p>
					<a class="btn btn-light" role="button"
						href="<c:url value='/user/myPage/scrap/list'/>">스크랩함 보기</a>
				</p>
			</div>
		</div>

		<hr>

		<!-- <div class="container">
      <h4><b>가입 클럽 목록</b></h4>
      </div>
      <hr>
      <div class="container">
      <h4><b>내가 쓴 글</b></h4>
      </div>
      
      <hr> -->
	</div>
	<!-- /container -->
	<!-- Footer -->
	<footer class="py-5" style="background-color: #04ADBF;">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; WOBBY 2020</p>
		</div>
		<!-- /.container -->
	</footer>
</body>
</html>
