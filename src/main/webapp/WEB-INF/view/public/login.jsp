<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body style="margin: 100px; text-align: center;">
	<h1>로그인</h1>
	
	<form method="post" action="${pageContext.request.contextPath}/public/login">
		<div>
			id : <input type="text" name="memberId">
		</div>
		<div>
			pw : <input type="password" name="memberPw">
		</div>
		<button>로그인</button>
	</form><br>
	
	<a href="${pageContext.request.contextPath}/public/signUp"><button>회원가입</button></a>
</body>
</html>