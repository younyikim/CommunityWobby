<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
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
<title>Search</title>
<script>
   function search() {
      if (form.keyword.value == "") {
         alert("검색어를 입력해주세요.");
         form.keyword.focus();
         return false;
      }
      //생년월일, 취미 Hobby, 성향mbti 받아오기 
      form.submit();
   }
</script>
</head>
<body>
   <!-- Navigation -->
   <%@include file="/navbar.jsp" %>

   <!-- Header -->
   <header class="bg-info py-5 mb-5">
      <div class="container h-100">
         <div class="row h-100 align-items-center">
            <div class="col-lg-12">
               <h1 class="display-4 text-white mt-5 mb-2">Search</h1>
               <p class="lead mb-5 text-white-50">원하는 키워드로 검색해보세요.</p>
            </div>
         </div>
      </div>
   </header>

   <!-- Search Box Content -->
   <div class="container">
      <br />
      <div class="row justify-content-center">
         <div class="col-12 col-md-10 col-lg-8 mb-5">
            <form name="form" class="card card-sm mb-3"
               action="<c:url value='/search' />">
               <div class="card-body row no-gutters align-items-center">
                  <div class="col-auto">
                     <i class="fas fa-search h4 text-body"></i>
                  </div>
                  <div class="col">
                     <input
                        class="form-control form-control-lg form-control-borderless"
                        type="search" name="keyword" placeholder="${searchWord}">
                  </div>
                  <div class="col-auto">
                     <button class="btn btn-lg btn-info" onClick="search()">Search</button>
                  </div>
               </div>
            </form>
            <c:if test="${!searchFailed}">
               <p class="text-info"><b>"${searchWord}" 검색 결과입니다.</b></p>
            </c:if>
         </div>
      </div>
   </div>

   <c:if test="${!searchFailed}">
   <!-- Search Result Content -->
   <div class="container col-md-8 mb-5">
      <!-- POST List CONTENT -->
      <div class="container mb-5">
         <h3>
            <b><span class="text-info">* </span>게시글 검색 결과</b>
         </h3>
         <hr>
         <div class="container">
            <c:if test="${postList eq NULL}">
            게시글 검색 결과가 없습니다.
         </c:if>
            <c:if test="${postList ne NULL}">
               <table class="table">
                  <thead>
                     <tr>
                        <th>게시판</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>날짜</th>
                        <th>조회수</th>
                        <th>스크랩 수</th>
                     </tr>
                  </thead>

                  <tbody>
                     <c:forEach var="post" items="${postList}">
                        <tr>
                           <td><a href="<c:url value='/community/board'>
                     <c:param name='boardId' value='${post.boardId}'/>
                  </c:url>">${post.boardName}</a></td>
                           <td><a href="<c:url value='community/post'>
                     <c:param name='boardId' value='${post.boardId}'/>
                     <c:param name='postId' value='${post.postId}'/>
                  </c:url>">${post.title}</a></td>
                           <td>${post.nickname}</td>
                           <td>${post.postDate}</td>
                           <td>${post.numOfView}</td>
                           <td>${post.numOfScraps}</td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </c:if>
         </div>
      </div>
      <div class="container mb-2">&nbsp;</div>
      <div class="container mb-5">
         <h3>
            <b><span class="text-info">* </span>클럽 검색 결과</b>
         </h3>
         <hr>
         <c:if test="${clubList eq NULL}">
      클럽 검색 결과가 없습니다.
   </c:if>
         <hr>
         <c:if test="${clubList ne NULL}">
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
         </c:if>
      </div>
      <div class="container mb-2">&nbsp;</div>
   </div>
   </c:if>


   <!-- Footer -->
	<footer class="py-5" style="background-color: #04ADBF;">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; WOBBY 2020</p>
		</div>
		<!-- /.container -->
	</footer>
</body>
</html>