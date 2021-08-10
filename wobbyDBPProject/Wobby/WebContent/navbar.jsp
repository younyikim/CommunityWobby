<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<style type="text/css">
.navbar-inverse { background-color: #04ADBF}
.navbar-inverse .navbar-nav>.active>a:hover,.navbar-inverse .navbar-nav>li>a:hover, .navbar-inverse .navbar-nav>li>a:focus { background-color: #049DBF; pading:0px 10px 0px 10px;}
.navbar-inverse .navbar-nav>.active>a,.navbar-inverse .navbar-nav>.open>a,.navbar-inverse .navbar-nav>.open>a, .navbar-inverse .navbar-nav>.open>a:hover,.navbar-inverse .navbar-nav>.open>a, .navbar-inverse .navbar-nav>.open>a:hover, .navbar-inverse .navbar-nav>.open>a:focus { background-color: #049DBF; margin:10px 0px 10px 0px;}
.dropdown-menu { background-color:#D996B5; margin:5px 10px 5px 10px;}
.dropdown-menu>li>a:hover, .dropdown-menu>li>a:focus { background-color: #04ADBF}
.navbar-inverse { background-image: none; }
.dropdown-menu>li>a:hover, .dropdown-menu>li>a:focus { background-image: none; }
.navbar-inverse { border-color: #D996B5}
.navbar-inverse .navbar-brand { color: #FCFCFC}
.navbar-inverse .navbar-brand:hover { color: #FFFFFF}
.navbar-inverse .navbar-nav>li>a { color: #FFFFFF; pading:10px 10px 10px 10px;}
.navbar-inverse .navbar-nav>li>a:hover, .navbar-inverse .navbar-nav>li>a:focus { color: #FFFFFF}
.navbar-inverse .navbar-nav>.active>a,.navbar-inverse .navbar-nav>.open>a, .navbar-inverse .navbar-nav>.open>a:hover, .navbar-inverse .navbar-nav>.open>a:focus { color: #FFFFFF}
.navbar-inverse .navbar-nav>.active>a:hover, .navbar-inverse .navbar-nav>.active>a:focus { color: #FFFFFF}
.dropdown-menu>li>a { color: #FFFFFF;  pading:0px 10px 0px 10px;}
.dropdown-menu>li>a:hover, .dropdown-menu>li>a:focus { color: ##FFFFFF}
.navbar-inverse .navbar-nav>.dropdown>a .caret { border-top-color: #FFFFFF}
.navbar-inverse .navbar-nav>.dropdown>a:hover .caret { border-top-color: #FFFFFF}
.navbar-inverse .navbar-nav>.dropdown>a .caret { border-bottom-color: #FFFFFF}
.navbar-inverse .navbar-nav>.dropdown>a:hover .caret { border-bottom-color: #FFFFFF}

</style>
</head>
<!-- <header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav" role="banner"> -->
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
				<li class="nav-item"><a class="nav-link" href="<c:url value='/club/clubHome'/>">CLUB</a></li>	
				<li class="nav-item"><a class="nav-link" href="<c:url value='/matching/page'/>">MATCHING</a></li>	
				<li class="nav-item"><a class="nav-link" href="<c:url value='/search'/>">SEARCHING</a></li>
				<li class="dropdown">
					<a  class="nav-link dropdown-toggle" data-toggle="dropdown">MY</a>
			        <ul class="dropdown-menu">
			          <li class="nav-item"><a class="nav-link" href="<c:url value='/user/logout'/>">LOGOUT</a></li>
			          <li class="nav-item"><a class="nav-link" href="<c:url value='/user/myPage'/>">MY PAGE</a></li>
			          <li class="nav-item"><a class="nav-link" href="<c:url value='/club/myClub'/>">MY CLUB</a></li>
			        </ul>
		      	</li>
			 </ul>
			 </div>
		</div>
	</nav>
<!-- </header> -->