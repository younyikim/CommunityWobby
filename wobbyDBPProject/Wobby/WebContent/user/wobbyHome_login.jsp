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
<title>WOBBY</title>
</head>
<body class="pt-5">
   <!-- Navigation -->
   <%@include file="/navbar.jsp" %>

   <!-- Page Content -->
   <div class="container" style="padding:100px 0px 50px 0px;">

      <!-- Jumbotron Header -->
      <header class=" my-4 text-center">
         <h6>여성들을 위한 취미 공유 플랫폼</h6>
         <h1 class="display-3">WOBBY</h1>
         <div class="mt-4"><b style="color:#049DBF;">${curUserId}</b>님, 어서오세요!</div>
         <div class="row mt-2 mb-5 text-center">
         <div class="col-md-5">&nbsp;</div>
         <div class="col-md-2">
            <a href="<c:url value='/user/myPage'/>" class="btn btn-outline-info mb-1 btn-block">마이 페이지</a>
         </div>
         </div>
      </header>
   
   <!-- 검색 -->
   <div class="row">
      <div class="col-md-2"></div>
      <form class="row col-md-8" action="<c:url value='/search'/>">
        <div class="form-group col-md-10">
          <input type="text" name="keyword" class="form-control" placeholder="검색어를 입력하세요">
        </div>
        <div class="col-md-2"><button type="submit" class="btn btn-info btn-block">검색</button></div>
      </form>
   </div>
      
   <div class="row mt-3">
      <div class="col-md-2"></div>
      <div class="container col-md-8">
      <h5 class="card-header">게시판</h5>
      </div>
      <div class="col-md-2"></div>
      </div>
      
   <div class="row mt-1">
      <div class="col-md-2"></div>
      <div class="btn-group btn-group-toggle pt-1 btn-block col-md-8"
               data-toggle="buttons">
               <a class="btn btn-outline-info"  href="<c:url value='/community/board'>
                        <c:param name="boardId" value="35"/>
                      </c:url>">
               그림 </a>
               <a class="btn btn-outline-info pull-right"  href="<c:url value='/community/board'>
                        <c:param name="boardId" value="36"/>
                      </c:url>">
               독서 </a>
               <a class="btn btn-outline-info pull-right"  href="<c:url value='/community/board'>
                        <c:param name="boardId" value="37"/>
                      </c:url>">
               게임 </a>
               <a class="btn btn-outline-info pull-right"  href="<c:url value='/community/board'>
                        <c:param name="boardId" value="38"/>
                      </c:url>">
               요리 </a>
               <a class="btn btn-outline-info pull-right"  href="<c:url value='/community/board'>
                        <c:param name="boardId" value="39"/>
                      </c:url>">
               언어 </a>
               <a class="btn btn-outline-info pull-right"  href="<c:url value='/community/board'>
                        <c:param name="boardId" value="40"/>
                      </c:url>">
               경제 </a>
               <a class="btn btn-outline-info pull-right"  href="<c:url value='/community/board'>
                        <c:param name="boardId" value="41"/>
                      </c:url>">
               음악 </a>
               <a class="btn btn-outline-info pull-right"  href="<c:url value='/community/board'>
                        <c:param name="boardId" value="42"/>
                      </c:url>">
               스터디 </a>
               <a class="btn btn-outline-info pull-right"  href="<c:url value='/community/board'>
                        <c:param name="boardId" value="43"/>
                      </c:url>">
               스포츠 </a>
               </div>
         </div>
         
      <div class="row my-5">
      <div class="col-md-2"></div>
      <div class="row col-md-8">
           <div class="col-md-4 center-block text-center" style="width:100px; padding:15px;">
             <a href="<c:url value='/matching/page'/>"><h2 style="color:#04ADBF;">MATCHING</h2></a>
             <p>클럽을 매칭해보세요.</p>
           </div>
           <div class="col-md-4 center-block text-center" style="width:100px; padding:15px;">
             <a href="<c:url value='/club/clubHome'/>"><h2 style="color:#04ADBF;">CLUBS</h2></a>
             <p>클럽을 둘러보세요.</p>
           </div>
           <div class="col-md-4 center-block text-center" style="width:100px; padding:15px;">
             <a href="<c:url value='/search'/>"><h2 style="color:#04ADBF;">SEARCHING</h2></a>
             <p>클럽과 게시글을<br>찾아보세요.</p>
           </div>
      </div>
      <div class="col-md-3"></div>
   </div>
   
   </div>

   <!-- Page Features -->
      <div class="my-5"></div>

   <!-- Footer -->
   <footer class="py-5" style="background-color:#04ADBF;">
      <div class="container">
         <p class="m-0 text-center text-white">Copyright &copy; WOBBY 2020</p>
      </div>
      <!-- /.container -->
   </footer>

   <!-- Bootstrap core JavaScript -->

</body>
</html>