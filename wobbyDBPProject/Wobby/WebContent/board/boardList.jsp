<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 부트스트랩 css 사용 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	
	<title>게시판 UI</title>
		
</head>
<body>

<%@include file="/board/navbar.jsp" %>

<div class="container">
<table class="table">
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>스크랩 수</th>
		</tr>
	</thead>
	
	<tbody>
	
	<c:forEach var="post" items="${postList}" varStatus="status">
		<tr>
			<td>		
			<!--   ${post.postId} -->
			${status.count}
			</td>
			<td>
				<a href="<c:url value='/community/post'>
						<c:param name='boardId' value='${post.boardId}' />
			 			<c:param name='postId' value='${post.postId}' />
			 		 </c:url>"> 
			 		 ${post.title}
			 		 <c:if test="${post.numOfComments != 0}">
			 		 [${post.numOfComments}]
			 		 </c:if>
			 	</a>
			</td>
			<td>
			${post.nickname}
			</td>
			
			<td>
			${post.postDate}
			</td>
			
			<td>
			${post.numOfView}
			</td>
			
			<td>
			${post.numOfScraps}
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>


	<c:if test="${curUserId != null}">
	
		<a class="btn btn-primary pull-right"  
				href = "<c:url value='/community/post/create/form' >
				<c:param name="board_Id" value='${param.boardId }' /> </c:url>">글쓰기</a>
	</c:if>

								
	<div class="text-center">
		<ul class="pagination">
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
		</ul>
	</div>
</div>

<!--  부트스트랩 js 사용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>

</body>
</html>