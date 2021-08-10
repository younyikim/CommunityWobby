<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.*, persistence.dao.impl.PostDAOImpl"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 부트스트랩 css 사용 -->

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 부트스트랩 css 사용 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<script>
function userRemove() {
	return confirm("정말 삭제하시겠습니까?");		
}

function writeComment() {
	if(writeCommentForm.contents.value == "") {
		alert("댓글 내용을 입력하세요");
		writeCommentForm.contents.focus();
		return false;
	}
	writeCommentForm.submit();
}
function updateComment() {
	if(updateCommentForm.contents.value == "") {
		alert("댓글 내용을 입력하세요");
		updateCommentForm.contents.focus();
		return false;
	}
	updateCommentForm.submit();
}
</script>

<title>게시글 상세 확인</title>
</head>

<body>
	<%@include file="/board/navbar.jsp"%>

	<div class="container">
		<div class="row">
			<c:if test="${!scrapSuccessed && !isScraped}">
				<a class="btn btn-outline-dark pull-right"
				href="<c:url value='/community/post/scrap' >
				 <c:param name="postId" value="${post.postId}" />
		     	 <c:param name="board_Id" value="${param.boardId}"/>
							  </c:url>">
					스크랩 </a>
			</c:if>
			<c:if test="${isScraped}">
				<a class="btn btn-outline-dark pull-right" role="button" disabled="disabled" >
					스크랩 </a>
			</c:if>
			<c:if test="${scrapSuccessed}">
				<div class="alert alert-success" role="alert">
				    해당 글이 스크랩되었습니다.<a href="<c:url value='/user/myPage/scrap/list'/>" class="alert-link">
				    스크랩함으로 이동</a>
				</div>
			</c:if>
			<form action="readAction" method="get">
				<table class="table table-striped"
					style="text-align: center; border: 1px solid #dddddd">
					<thead>

					</thead>
					<tbody>

						<tr>
							<td style="width: 20%;">제목</td>
							<td colspan="2" style="min-height: 10px; text-align: left;">${post.title}
							</td>
						</tr>
						<tr>
							<td style="width: 20%;">작성자</td>
							<td colspan="2" style="min-height: 10px; text-align: left;">${post.nickname}
							</td>
						</tr>
						<tr>
							<td style="width: 20%;">작성일</td>
							<td colspan="2" style="min-height: 10px; text-align: left;">
								${post.postDate}</td>
						</tr>
						<tr>
							<td style="width: 20%;">조회수</td>
							<td colspan="2" style="min-height: 10px; text-align: left;">${post.numOfView}
							</td>
						</tr>
						<tr>
							<td style="width: 20%;">스크랩수</td>
							<td colspan="2" style="min-height: 10px; text-align: left;">${post.numOfScraps}
							</td>
						</tr>
						<tr>
							<td colspan="2" style="min-height: 300px; text-align: left;">${post.contents}
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>


		<br>
		<c:if test="${post.userId == curUserId || curUserId == 'admin'}">
			<a class="btn btn-primary pull-right"
				href="<c:url value='/community/post/update' >
		    		     <c:param name='postId' value='${post.postId}'/>
		    		     <c:param name='board_Id' value='${param.boardId}' />   
				  </c:url>">수정</a>
		</c:if>
		<c:if test="${post.userId == curUserId || curUserId == 'admin'}">

			<a class="btn btn-primary pull-right"
				href="<c:url value='/community/post/delete'>
		     	 <c:param name="postId" value="${post.postId}" />
		     	 <c:param name="board_Id" value="${param.boardId}"/>
	 	      </c:url>"
				onclick="return userRemove();">삭제 </a>
		</c:if>


		<a class="btn btn-primary"
			href="<c:url value='/community/board' >
				<c:param name="boardId" value="${param.boardId}"/> 
			  </c:url>">
			목록 </a>


	<br>

	<!-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
	<c:if test="${updateFailed || deleteFailed}">
		<h6 class="text-danger">
			<c:out value="${exception.getMessage()}" />
		</h6>
	</c:if>

	<br>

	<!-- 댓글 부분 --><!-- 로그인 했을 경우만 댓글 작성가능 -->
		<c:if test="${curUserId != null && !isUpdateComment}">
			<form id="writeCommentForm"
				action="<c:url value='/community/comment/create' />">
				<table class="table table-striped"
					style="text-align: center; border: 1px solid #dddddd">

					<tbody>
						<tr>
							<td>
							<input type="hidden" name="boardId" value="${param.boardId}">
							<input type="hidden" name="postId" value="${param.postId}">
							<textarea class="form-control" placeholder="댓글 내용"
									name="contents" maxlength="1000" style="height: 50px;"></textarea>
						</tr>
					</tbody>
				</table>
			<button type="button" class="btn btn-primary pull-right" onClick="writeComment()">댓글등록</button>
			</form>
		</c:if>

		<c:if test="${curUserId != null && isUpdateComment}">
			<form id="updateCommentForm" method="post"
				action="<c:url value='/community/comment/update' />">
				<table class="table table-striped"
					style="text-align: center; border: 1px solid #dddddd">

					<tbody>
						<tr>
							<td>
							<input type="hidden" name="boardId" value="${param.boardId}">
							<input type="hidden" name="postId" value="${param.postId}">
							<input type="hidden" name="commentId" value="${param.commentId}">
						<textarea class="form-control"
									name="contents" maxlength="1000" style="height: 50px;">${updateComment.contents}</textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<button type="button" class="btn btn-info pull-right" onClick="updateComment()">댓글수정</button>
			</form>
		</c:if>
		

		<table class="table">
		<thead>
			<tr>
				<th style="width: 20%;">작성자</th>
				<th>댓글</th>
				<th style="width: 20%;">작성일 </th>
				<th style="width: 20%;"> </th>
			</tr>
		</thead>
		
		<tbody>
		
		<c:forEach var="comment" items="${commentList}" varStatus="status">
			<tr>
				<td>		
					${comment.userId}
				</td>
				<td>
					${comment.contents}
				</td>
				<td>
					${comment.postDate}
				</td>
				<td>
 					<c:if test="${comment.userId == sessionScope.curUserId || curUserId == 'admin'}">
						<a role="button" class="btn  btn-default pull-right" href="<c:url value='/community/comment/update'>
								     	 <c:param name="postId" value="${comment.postId}" />
								     	 <c:param name="boardId" value="${param.boardId}"/>
								     	 <c:param name="commentId" value="${comment.commentId}"/>
							 	      </c:url>">수정 </a>
					</c:if>
					<c:if test="${comment.userId == sessionScope.curUserId || curUserId == 'admin'}">
			
						<a class="btn  btn-outline-dark pull-right "
							href="<c:url value='/community/comment/delete'>
					     	 <c:param name="postId" value="${post.postId}" />
					     	 <c:param name="boardId" value="${param.boardId}"/>
					     	  <c:param name="commentId" value="${comment.commentId}"/>
				 	      </c:url>"
							onclick="return userRemove();">삭제 </a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

	<!--  부트스트랩 js 사용 -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="/resource/js/bootstrap.js"></script>

</body>
</html>