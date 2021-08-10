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
  <title>MatchingResult</title>
</head>
<body>
   <!-- Navigation -->
   <%@include file="/navbar.jsp" %>

  <!-- Header -->
  <header class="bg-info py-5 mb-5">
    <div class="container h-100">
      <div class="row h-100 align-items-center">
        <div class="col-lg-12">
          <h1 class="display-4 text-white mt-5 mb-2">Matching - <b><i>${ResultKeyword}</b></i></h1>
             <c:if test="${matchingType eq 'MBTI'}">
             <p class="lead mb-5 text-white-50"><b>${name}</b>님의 MBTI인<b><i> ${ResultKeyword}</b></i>와 일치하는 클럽들을 확인해보세요.</p>
         </c:if>
             <c:if test="${matchingType eq 'REGION'}">
             <p class="lead mb-5 text-white-50"><b>${name}</b>님의 지역인 &nbsp;<b><i>${ResultKeyword}</b></i> 주변의 클럽들을 확인해보세요.</p>
         </c:if>
        </div>
      </div>
    </div>
  </header>

  <!-- Page Content -->
   <div class="container col-md-11 mb-5">
   <!-- Blog Post -->
      <div class="row"><div class="col-md-1"></div>
         <c:forEach var="club" items="${clubList}" varStatus="status">
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
  </div>
  <!-- /.container -->

   <!-- Footer -->
	<footer class="py-5" style="background-color: #04ADBF;">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; WOBBY 2020</p>
		</div>
		<!-- /.container -->
	</footer>
</body></html>