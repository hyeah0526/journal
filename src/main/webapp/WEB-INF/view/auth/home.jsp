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
	<title>Journal Home</title>
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
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<!-- 내비게이션 Navigation-->
	<jsp:include page="/WEB-INF/view/auth/header.jsp"></jsp:include>
	
	<!-- 헤더 Page Header-->
    <header class="masthead" style="background-image: url('../resources/assets/img/home.jpg')">
        <div class="container position-relative px-4 px-lg-5">
            <div class="row gx-4 gx-lg-5 justify-content-center">
                <div class="col-md-10 col-lg-8 col-xl-7">
                    <div class="site-heading">
                        <h1>Journal For You</h1>
                        <span class="subheading">모든 저널을 확인하세요</span>
                    </div>
                </div>
            </div>
        </div>
    </header>
        
	<!-- 메인 Main Content-->
	<div class="container px-4 px-lg-5">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<!-- 전체조회 출력 부분 -->
				<c:forEach var="b" items="${list}">
					<div class="post-preview">
						<a href="${pageContext.request.contextPath}/auth/journalDetail?journalNo=${b.journalNo}">
							<h2 class="post-title">${b.title}</h2>
                            <h5 class="post-subtitle">
								<c:if test="${b.fileName eq 'noCover.png'}"><img src="../resources/inc/${b.fileName}"></c:if>
								<c:if test="${b.fileName ne 'noCover.png'}"><img src="/journal/img/${b.fileName}"></c:if>
								${b.content}......
							</h5>
						</a>
						<p class="post-meta">
							Posted by
							<a href="#!">${b.name}(${b.memberId})</a>
							on ${b.updateDate}
						</p>
                    </div>
					<!-- <hr>구분 부분 Divider-->
					<hr class="my-4" />
				</c:forEach>
				
				<!-- 제목으로 검색하기 -->
				<div class="homeSearchDiv">
					<form method="get" action="${pageContext.request.contextPath}/auth/home">
						<input name="searchWord">
						<button class="btn btn-primary text-uppercase" type="submit">Title Search</button>
					</form>
				</div>
				 
				<!-- 페이징 -->
				<!-- 이전페이지 -->
				<div class="pagingDiv">
					<c:choose>
						<c:when test="${currentPage > 1}">
							<a class="paging" href="${pageContext.request.contextPath}/auth/home?currentPage=${currentPage-1}&searchWord=${searchWord}">◀</a>
						</c:when>
						<c:otherwise>
							<a class="paging">◀</a>
						</c:otherwise>
					</c:choose>
					
					<!-- 첫페이지 고정 -->
					<a class="pagingHome" href="${pageContext.request.contextPath}/auth/home">F i r s t&nbsp;&nbsp;&nbsp;P a g e</a>
					
					<!-- 다음페이지 -->
					<c:choose>
						<c:when test="${currentPage < lastPage}">
							<a class="paging" href="${pageContext.request.contextPath}/auth/home?currentPage=${currentPage+1}&searchWord=${searchWord}">▶</a>
						</c:when>
						<c:otherwise>
							<a class="paging">▶</a>
	    				</c:otherwise>
					</c:choose>
				</div>
				
				
                <!-- 전체목록으로 이동 -->
                <div class="d-flex justify-content-end mb-4">
                	<a class="btn btn-primary text-uppercase" href="${pageContext.request.contextPath}/auth/journalPost">Journal Post →</a>
                </div>
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
							<a href="https://github.com/hyeah0526/journal" target='_blank'>
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

