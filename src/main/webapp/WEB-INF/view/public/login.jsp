<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body style="margin: 100px; text-align: center;">
	<%-- <h1>현재 접속자: ${currentCnt}</h1> --%>
	<h1>로그인 안 했을때</h1>
	
	<form method="post" action="${pageContext.request.contextPath}/public/login">
		<div>
			id : <input type="text" name="memberId">
		</div>
		<div>
			pw : <input type="password" name="memberPw">
		</div>
		<button>로그인</button>
	</form>
</body>
</html>