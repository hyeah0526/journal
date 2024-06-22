<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>journalPost</title>
	<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
	<!-- Font Awesome icons (free version)-->
	<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
	<!-- Google fonts-->
	<link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
	<!-- Core theme CSS (includes Bootstrap)-->
	<link href="../resources/css/styles.css" rel="stylesheet" />
	<!-- 개인 적용 CSS-->
	<link href="../resources/css/styles_2.css" rel="stylesheet" />
</head>
<body>
	<!-- 내비게이션 Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/auth/home">Journal For You</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
				data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
					<i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto py-4 py-lg-0">
					<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="${pageContext.request.contextPath}/auth/home">Home</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="${pageContext.request.contextPath}/auth/myJournal">My Journal</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="${pageContext.request.contextPath}/auth/otherJournal">Other journal</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="contact.html">MyPage</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4">Welcome, ${loginUserName} !</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="${pageContext.request.contextPath}/logout">logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
        
        
	<!-- 헤더 Page Header-->
	<header class="masthead" style="background-image: url('../resources/assets/img/about-bg.jpg')">
		<div class="container position-relative px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-md-10 col-lg-8 col-xl-7">
					<div class="site-heading">
						<h1>journal Post</h1>
						<span class="subheading">저널을 등록하세요</span>
					</div>
				</div>
			</div>
		</div>
	</header>
        
        
	<!-- 메인 Main Content-->
	<div class="container px-4 px-lg-5">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<!-- 저널 등록 폼 -->
				<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/auth/journalPost">
					<table border="1">
						<tr>
							<th>Title</th>
							<td><input type="text" name="title"></td>
						</tr>
						<tr>
							<th>Journal</th>
							<td>
								<input type="radio" name="type" value="movie" checked="checked">Movie &nbsp;
								<input type="radio" name="type" value="book">Book &nbsp;
								<input type="radio" name="type" value="etc">etc
							</td>
						</tr>
						<tr>
							<th>File</th>
							<td><input type="file" name="journalFile"></td>
						</tr>
						<tr>
							<th>Content</th>
							<td><textarea name="content"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">
                				<button type="submit" class="btn btn-primary text-uppercase">Post</button>
							</td>
						</tr>
					</table>
					<input type="hidden" name="memberId" value="${loginUser}">
				</form><br><br>
				
				
				<!-- 뒤로가기 -->
				<span class="justify-content-start mb-4" style="float: inline-start;">
					<a class="btn btn-primary text-uppercase" onclick="history.back()">← Back</a>
		        </span>
				
			</div>
		</div>
	</div>
        
        
	<!-- Footer-->
	<footer class="border-top">
		<div class="container px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-md-10 col-lg-8 col-xl-7">
					<ul class="list-inline text-center">
						<li class="list-inline-item">
							<a href="#!">
								<span class="fa-stack fa-lg">
								<i class="fas fa-circle fa-stack-2x"></i>
								<i class="fab fa-twitter fa-stack-1x fa-inverse"></i>
								</span>
							</a>
						</li>
						<li class="list-inline-item">
							<a href="#!">
								<span class="fa-stack fa-lg">
									<i class="fas fa-circle fa-stack-2x"></i>
									<i class="fab fa-facebook-f fa-stack-1x fa-inverse"></i>
								</span>
							</a>
						</li>
						<li class="list-inline-item">
							<a href="https://github.com/hyeah0526/journal">
								<span class="fa-stack fa-lg">
									<i class="fas fa-circle fa-stack-2x"></i>
									<i class="fab fa-github fa-stack-1x fa-inverse"></i>
								</span>
							</a>
						</li>
					</ul>
					<div class="small text-center text-muted fst-italic">Copyright &copy; Journal For You 2024</div>
				</div>
			</div>
		</div>
	</footer>
        
	<!-- Bootstrap core JS-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>
</html>

