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
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" >
   <!-- Optional JavaScript -->
   <!-- JS, Popper.js, and jQuery -->
   <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
   <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" ></script>
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
   <!-- for Icon -->
   <script src="https://kit.fontawesome.com/cfb4151af4.js"></script>
   
<title>쪽지함</title>
</head>
<body class="pt-5">
   <!-- Navigation -->
     <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
       <div class="container">
         <a class="navbar-brand" href="#">Wobby</a>
         <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
           <span class="navbar-toggler-icon"></span>
         </button>
         <div class="collapse navbar-collapse" id="navbarResponsive">
           <ul class="navbar-nav ml-auto">
             <li class="nav-item active">
               <a class="nav-link" href="#">My Page
                 <span class="sr-only">(current)</span>
               </a>
             </li>
             <li class="nav-item">
               <a class="nav-link" href="#">Clubs</a>
             </li>
             <li class="nav-item">
               <a class="nav-link" href="#">Matching</a>
             </li>
             <li class="nav-item">
               <a class="nav-link" href="#">Search</a>
             </li>
           </ul>
         </div>
       </div>
     </nav>
    <!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
      <div class="container">
        <i class="far fa-bell"></i> <h2><b>${curUserId}</b>님, 
         <span class="text-primary"><b>3</b></span>개의 쪽지를 놓치지마세요.<br>
         </h2></div>
    </div>

    <div class="container pb-2">
      <table class="table table-hover">
              <thead>
                <tr>
                  <th><a class="btn btn-light" role="button" href="<c:url value='/user/myPage/message/send/form'/>">쪽지 쓰기</a></th>
                  <th><button class="btn btn-light" role="button" onclick="location.href='messageBox.jsp'">받은 쪽지</button></th>
                  <th><button class="btn btn-light" role="button" onclick="location.href='messageBox.jsp'">보낸 쪽지</button></th>
                  <th></th>
                </tr>
              </thead>
              
              <tbody>
              <c:forEach var="msg" items="${msgList}" begin="1" end="3">
					<tr>
						<td>${msg.msgNo}</td>
						<td>${msg.sendId}</td>
						<td><a href="#">${msg.content}</a></td>
						<td><input type="checkbox" value="delete"></td>
					</tr>
				</c:forEach>
				
				<!--<c:forEach var="i" begin="1" end="3">
					<tr>
						<td>${i}</td>
						<td>댓글 ${curUserId}</td>
						<td><a href="#">메시지내용~</a></td>
						<td><input type="checkbox" value="delete"></td>
					</tr>
				</c:forEach>
				<c:forEach var="i" begin="4" end="15">
					<tr>
						<td>${i}</td>
						<td>답댓글</td>
						<td><a class="text-muted" href="#">게시글 제목 게시글 제목 게시글 제목
								게시글 제목</a></td>
						<td><input type="checkbox" value="delete"></td>
					</tr>
				</c:forEach>-->
              
                 <tr>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td><button class="btn btn-light" type="delete">삭제</button></td>
                </tr>
              </tbody>
              
            </table>
    </div> <!-- /container -->
    
    <div class="container pb-5">
       <ul class="pagination justify-content-center">
            <li class="page-item"><a class="page-link" href="#">이전</a></li>
            <li class="page-item"><a class="page-link" href="#"><b>1</b></a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">4</a></li>
            <li class="page-item"><a class="page-link" href="#">5</a></li>
            <li class="page-item"><a class="page-link" href="#">다음</a></li>
      </ul>
   </div>
</body>
</html>