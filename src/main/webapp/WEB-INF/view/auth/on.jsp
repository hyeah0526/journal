<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>On</title>
</head>
<body style="margin: 100px; text-align: center;">
<!-- 로그인 한 사람만 들어올 수 있음 -->
	<h1>로그인 했을때</h1>
 	${loginUser}님 반갑습니다<br>
 	${loginUserName}님 반갑습니다
 	<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
</body>
</html>