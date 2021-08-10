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
<title>스크랩 목록</title>
</head>
<body class="pt-5">
   <!-- Navigation -->
   <%@include file="/navbar.jsp" %>
   
    <!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
      <div class="container">
        <i class="far fa-star"></i> <h2><b>${userId}</b>님, 
        <span class="text-primary"><b>${scrapNum}</b></span>개의 스크랩을 확인해보세요.<br>
         </h2></div>
    </div>

   
    <div class="container pb-2">
    <form action="<c:url value='/user/myPage/scrap/delete' />" >
      <table class="table table-hover">
              <thead>
                <tr>
                  <th>#</th>
                  <th>제목</th>
                  <th>게시판</th>
                  <th>작성일</th>
                  <th>작성자</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                 <c:set var="pageIdx" value="${(page - 1) * 15}" />
                 <c:forEach var="scrapPost" items="${scrapList}" varStatus="status">
                    <tr>
                     <td>${status.count + pageIdx}</td>
                     <td><a class="text-muted" href="<c:url value='/community/post'>
                              <c:param name='boardId' value='${scrapPost.boardId}'/>
                              <c:param name='postId' value='${scrapPost.postId}'/>
                           </c:url>" class="btn btn-primary">${scrapPost.title}</a></td>
                     <td><a class="text-muted" href="<c:url value='/community/board'>
                              <c:param name='boardId' value='${scrapPost.boardId}'/>
                           </c:url>" class="btn btn-primary">${scrapPost.boardName}</a></td>
                     <td><a class="text-muted">${scrapPost.postDate}</a></td>
                     <td><a class="text-muted">${scrapPost.nickname}</a></td>
                     <td><input type="checkbox" name="delete" value="${scrapPost.scrapNo}"></td>
                   </tr>
                 </c:forEach>
                <c:if test="${scrapNum != 0}">
                <tr>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td><button class="btn btn-light" type="submit">삭제</button></td>
                </tr>
                </c:if>
              </tbody>
            </table>
       </form>
    </div> <!-- /container -->
    
    
    <div class="container pb-5">
       <ul class="pagination justify-content-center">
              <c:forEach var="i" begin="1" end="${lastPage}">
            <li class="page-item">
               <c:if test="${i eq page}"><a class="page-link" href="#"><b>${i}</b></a></c:if>
               <c:if test="${i ne page}"><a class="page-link" 
                  href="<c:url value='/user/scrapList'>
                           <c:param name='page' value='${i}'/>
                        </c:url>">${i}</a></c:if>
            </li>
         </c:forEach>
      </ul>
   </div>
   <!-- Footer -->
	<div class="py-5"></div>
	<footer class="py-5" style="background-color: #04ADBF;">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; WOBBY 2020</p>
		</div>
		<!-- /.container -->
	</footer>
</body>
</html>