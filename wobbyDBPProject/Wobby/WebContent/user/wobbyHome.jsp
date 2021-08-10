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
<style type="text/css">
.navbar-inverse { background-color: #04ADBF}
.navbar-inverse .navbar-nav>.active>a:hover,.navbar-inverse .navbar-nav>li>a:hover, .navbar-inverse .navbar-nav>li>a:focus { background-color: #049DBF; pading:0px 10px 0px 10px;}
.navbar-inverse .navbar-nav>.active>a,.navbar-inverse .navbar-nav>.open>a,.navbar-inverse .navbar-nav>.open>a, .navbar-inverse .navbar-nav>.open>a:hover,.navbar-inverse .navbar-nav>.open>a, .navbar-inverse .navbar-nav>.open>a:hover, .navbar-inverse .navbar-nav>.open>a:focus { background-color: #049DBF; margin:10px 0px 10px 0px;}
.navbar-inverse { background-image: none; }
.navbar-inverse { border-color: #D996B5}
.navbar-inverse .navbar-brand { color: #FCFCFC}
.navbar-inverse .navbar-brand:hover { color: #FFFFFF}
.navbar-inverse .navbar-nav>li>a { color: #FFFFFF; pading:10px 10px 10px 10px;}
.navbar-inverse .navbar-nav>li>a:hover, .navbar-inverse .navbar-nav>li>a:focus { color: #FFFFFF}
.navbar-inverse .navbar-nav>.active>a,.navbar-inverse .navbar-nav>.open>a, .navbar-inverse .navbar-nav>.open>a:hover, .navbar-inverse .navbar-nav>.open>a:focus { color: #FFFFFF}
.navbar-inverse .navbar-nav>.active>a:hover, .navbar-inverse .navbar-nav>.active>a:focus { color: #FFFFFF}

</style>
</head>
<body class="pt-5">
   <!-- Navigation -->
   <nav class="navbar navbar-expand-lg navbar-inverse fixed-top">
		<div class="container">
		<a class="navbar-brand" href="<c:url value='/home' />">WOBBY</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
		    <ul class="navbar-nav ml-auto">	
				<li class="nav-item"><a class="nav-link" href="<c:url value='/user/login/form'/>">LOGIN</a></li>	
			 </ul>
			 </div>
		</div>
	</nav>


   <!-- Page Content -->
   <div class="container" style="padding:100px 0px 200px 0px;">

      <!-- Jumbotron Header -->
      <header class=" my-4 text-center">
         <h6>여성들을 위한 취미 공유 플랫폼</h6>
         <h1 class="display-3">WOBBY</h1>
         <div class="row my-5">
         <div class="col-md-5">&nbsp;</div>
         <div class="col-md-2">
            <a href="<c:url value='/user/login/form'/>" class="btn btn-outline-primary my-1 btn-block">로그인</a>
            <a href="<c:url value='/user/signUp/form'/>" class="btn btn-outline-info my-1 btn-block">가입하기</a>
         </div>
         </div>
      </header>
   </div>
   <!-- /.container -->

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