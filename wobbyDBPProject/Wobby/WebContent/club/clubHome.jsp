<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
   src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
   src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- for Icon -->
<script src="https://kit.fontawesome.com/cfb4151af4.js"></script>
<title>클럽 홈</title>
</head>
<body class="pt-5">
   <!-- Navigation -->
   <%@include file="/navbar.jsp" %>
   
   <!-- Main jumbotron for a primary marketing message or call to action --><!-- 
   <div class = "container">
         <div class="card-body">
            <div class="input-group">
               <input type="text" class="form-control" placeholder="검색를 입력하세요.">
               <span class="input-group-append">
                  <button class="btn btn-secondary" type="button">클럽 검색</button>
               </span>
            </div>
         </div>
      </div> -->
      
    <div class="jumbotron">
      <div class="container">
        <h2><b>${userId}</b>님, 이런 클럽은 어떠세요?</h2>
        <p><a class="btn btn-light" href="<c:url value='/club/myClub'/>"role="button">내 클럽 목록 ></a></p>
         <p><a class="btn btn-light" href="<c:url value='/club/createClub/form'/>">새 클럽 만들기 ></a></p>
          <p><c:if test="${registerFailed}">
        <h6 class="text-danger"><c:out value="${exception}" /></h6>
				</c:if></p>
				  <p><c:if test="${memberFulled}">
        <h6 class="text-danger"><c:out value="${exception}" /></h6>
				</c:if></p>
      </div>
    </div>
    
   <!-- Main Container -->
   <div class="mb-5">
      <!-- POPULAR CLUB -->
      <div class="container col-md-10 mb-3">
         <div class="row"><div class="col-md-1"></div>
         <h3 class="my-4">
            <b><span class="text-info">* </span>실시간 인기 클럽 TOP 3</b>
         </h3>
         </div>
         <div class="row"><div class="col-md-1"></div>
            <c:forEach var="club" items="${pClubList}" varStatus="status">
            <div class="card col-md-3 mb-3 m-2">
               <div class="card-body ">
                     <h2 class="card-title">${club.name}</h2>
               </div>
               <a href="<c:url value='/club/register'>
                        <c:param name='clubId' value='${club.clubId}'/>
                     </c:url>" class="btn btn-light">클럽 가입하기</a>
                  <div class="card-footer text-muted">
                     <c:forEach var="category" items="${club.category}"> ${category} </c:forEach><br>
                        시작일  |  ${club.startDate}  <br> 회원수 | ${club.numOfMembers}명 <br>
                  </div>
            </div>
            </c:forEach>
          </div>
       </div>
       </div>
   
         <hr>
   
      <!-- SAME CATEGORY CLUB -->
      <div class="container col-md-10 mb-5">
         <div class="row"><div class="col-md-1"></div>
         <h3 class="my-4">
            <b><span class="text-info">* </span>관심분야 추천클럽</b>
         </h3>
         </div>
         <div class="row"><div class="col-md-1"></div>
            <c:forEach var="club" items="${cClubList}" varStatus="status">
            <div class="card col-md-3 mb-3 m-2">
               <div class="card-body ">
                     <h2 class="card-title">${club.name}</h2>
               </div>
               <a href="<c:url value='/club/register'>
                        <c:param name='clubId' value='${club.clubId}'/>
                     </c:url>" class="btn btn-light">클럽 가입하기</a>
                  <div class="card-footer text-muted">
                     <c:forEach var="category" items="${club.category}"> ${category} </c:forEach><br>
                        시작일  |  ${club.startDate}  <br> 회원수 | ${club.numOfMembers}명 <br>
                  </div>
            </div>
             <c:if test="${(status.index % 3) eq 2}">
                </div><div class="row"><div class="col-md-1"></div>
             </c:if>
            </c:forEach>
          </div>
   <!-- /container -->
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