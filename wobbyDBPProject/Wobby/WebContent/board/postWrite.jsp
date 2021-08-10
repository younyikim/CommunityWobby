<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.*, persistence.dao.impl.PostDAOImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 부트스트랩 css 사용 -->
    
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 부트스트랩 css 사용 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	
<title>게시글 입력</title>

<script>
	function createPost() {
		if(writerAction.title.value == "") {
			alert("Enter the title!");
			writerAction.bdTitle.focus();
			return false;
		}
		
		writerAction.submit();
	}
</script>
</head>

<body>

<%@include file="/board/navbar.jsp" %>
<div class="container">
	<div class="row">
	<form name="writerAction" method="post" action="<c:url value='/community/post/create' />">
	
	<table class="table table-striped"
					style="text-align: center; border: 1px solid #dddddd">
		<thead>
			<tr>
				<th colspan="2" style="background-color: #eeeeee; text-align: center;">게시글 작성</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td> <input type="text" name="title" class="form-control mt-4 mb-2"
	 					placeholder="제목을 입력해주세요." required>	
	 			</td>
			</tr>	
			<tr>
				<td>
					<div class="form-group row">  
				        <label for="commId" class="col-lg-2 col-form-label">게시판 </label>
				        <!-- 화면 로드 시 서버로부터 커뮤니티 목록을 가져와 commSelect 메뉴 생성  -->
				        <div class="col-lg-10">
				        	<select id="commSelect" name="boardId" class="form-control"> 
								<option value="${param.board_Id }">선택한 게시판 : ${param.board_Id} 번</option>
								
							</select>
				        </div>
				  	  </div> 
				</td>
			</tr>
			<tr>
				<td>
					<textarea class="form-control" placeholder="글 내용" name="contents" maxlength="2048" style="height: 350px;"></textarea>
				</td>
			</tr>

		</tbody>
	</table>
	
	<input type="button"  class="btn btn-primary pull-right" onClick="createPost()" value="글쓰기">
	<a class="btn btn-primary" 
		href="<c:url value='/community/board' >
				<c:param name="boardId" value="${param.board_Id}"/> 
			  </c:url>">
	목록 </a>
		
	</form>

	</div>
</div>
<!--  부트스트랩 js 사용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>

</body>
</html>