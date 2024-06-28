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
	<title>Journal Detail</title>
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
    <header class="masthead" style="background-image: url('../resources/assets/img/journal_detail.jpg')">
        <div class="container position-relative px-4 px-lg-5">
            <div class="row gx-4 gx-lg-5 justify-content-center">
                <div class="col-md-10 col-lg-8 col-xl-7">
                    <div class="site-heading">
                        <h1>Journal Detail</h1>
                        <span class="subheading">저널을 확인하세요</span>
                    </div>
                </div>
            </div>
        </div>
    </header>
        
	<!-- 메인 Main Content-->
	<div class="container px-4 px-lg-5">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<!-- 상세조회 출력 부분 -->
				<c:if test="${journalEdit ne 'edit' }">
					<h2 class="post-title">${map.title}</h2>
					<h3 class="post-title">${map.type}</h3>
					<span class="post-meta">작성자: ${map.name}</span><br>
					<span class="post-meta">최종 작성일: ${map.updateDate}</span><br>
					<span class="post-meta">파일 이름: ${map.originalName}</span>
					
					<hr class="my-4" />
					
					<c:if test="${map.fileName eq 'noCover.png'}"><img src="../resources/inc/${map.fileName}"></c:if>
					<c:if test="${map.fileName ne 'noCover.png'}"><img src="/journal/img/${map.fileName}"></c:if>
					<br><br>${map.content}<br><br>
				</c:if>
				
				<!-- 수정요청이 들어올 경우 수정폼 출력 -->
				<c:if test="${journalEdit eq 'edit' }">
					<form method="post" enctype="multipart/form-data" id="editForm" action="${pageContext.request.contextPath}/auth/journalDetail">
						<h2 class="post-title"><input type="text" name="title" value="${map.title}"></h2>
						<h4>
							<input type="radio" name="type" id="typeMovie" value="movie">Movie &nbsp;
							<input type="radio" name="type" id="typeBook" value="book">Book &nbsp;
							<input type="radio" name="type" id="typeEtc" value="etc">etc
						</h4>
						<span class="post-meta">작성자: ${map.name}</span><br>
						<span class="post-meta">최종 작성일: ${map.updateDate}</span><br>
						<span class="post-meta">파일 이름: ${map.originalName}</span><br>
						<span class="post-meta">
							수정 파일: <input type="File" name="newFile">
						</span>
					
						<hr class="my-4" />
					
						<c:if test="${map.fileName eq 'noCover.png'}"><img src="../resources/inc/${map.fileName}"></c:if>
						<c:if test="${map.fileName ne 'noCover.png'}"><img src="/journal/img/${map.fileName}"></c:if>
						<br><br><textarea name="content" style="width: 100%;">${map.content}</textarea> <br><br>
						
						<input type="hidden" name="fileNo" value="${map.fileNo}">
						<input type="hidden" name="oldFileName" value="${map.fileName}">
						<input type="hidden" name="memberId" value="${loginUser}">
						<input type="hidden" name="journalNo" value="${map.journalNo}">
						<input type="hidden" name="typeRadioChk" value="${map.type}">
					</form>
				</c:if>
				
				<div>
					<!-- 뒤로가기 -->
					<span class="justify-content-start mb-4" style="float: inline-start;">
						<a class="btn btn-primary text-uppercase" onclick="history.back()">← Back</a>
		            </span>
					
					
	                <!-- 수정 하기, 수정완료 버튼 활성화 분기 -->
	                <c:if test="${loginUser eq map.memberId && journalEdit ne 'edit' }">
			            <span class="justify-content-end mb-4" style="float: inline-end;">
			                <a class="btn btn-primary text-uppercase" id="EditBtn" 
			                		href="${pageContext.request.contextPath}/auth/journalDetail?journalNo=${map.journalNo}&journalEdit=edit">
			               		Edit →
			               	</a>
			               	 <a class="btn btn-primary text-uppercase" 
			               	 		href="${pageContext.request.contextPath}/auth/journalDelete?journalNo=${map.journalNo}&fileName=${map.fileName}">
			               		Delete →
			               	</a>
			            </span>
		            </c:if>
	                <c:if test="${journalEdit eq 'edit' }">
			            <span class="justify-content-end mb-4" style="float: inline-end;">
			                <a class="btn btn-primary text-uppercase" id="EditBtn">Done →</a>
			            </span>
		            </c:if>
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
	
<!-- 백엔드 구현용 JS -->
<script>
	$(document).ready(function() {
		// 수정 폼 제출하기
		$("#EditBtn").click(function() {
	    	$("#editForm").submit();
		});
		 	
		 	
		// 수정 폼 radio 값 비교후 checked 추가
		let type = $('input[name="typeRadioChk"]').val();
		console.log(type);
		 	
		$('input[name="type"]').each(function(){
			
			if(type == "Book"){
				$("#typeBook").prop("checked", true);
			}
		 		
		 	if(type == "Movie"){
		 		$("#typeMovie").prop("checked", true);
		 	}
		 		
		 	if(type == "etc"){
		 		$("#typeEtc").prop("checked", true);
		 	}
		 })
		 	
		 	
		 	
	});
</script>
</body>
</html>

