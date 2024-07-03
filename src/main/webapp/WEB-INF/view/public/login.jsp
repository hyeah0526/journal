<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<style>
	#loginBody{
		margin: 100px; 
		text-align: center;
		background: url('../resources/assets/img/home-bg.jpg');
		background-size: cover;
	}
	
	#loginDiv{
		margin: auto;
		text-align: center;
		width: 500px;
		background: rgba(0, 133, 161, 0.5);
		border-radius: 10px;
		padding: 30px;
		color: white;
	}
	
	#loginDiv table{
		text-align: center;
		margin: auto;
	}
	
	#loginDiv th{
		width: 150px;
		text-align: center;
		height: 50px;
	}
	
	#loginDiv input{
		width: 150px;
		text-align: center;
		height: 30px;
	}
	
	#loginBody button{
		width: 100px;
		height: 50px;
		border: none;
		border-radius: 10px;
		background: rgba(255, 255, 255, 0.5);
		color: white;
	}
	
	#loginBody button:hover {
		background: rgba(0, 133, 161, 0.9);
	}
</style>
</head>
<body id="loginBody">
	<div id="loginDiv">
		<h1>로그인</h1>
		<form method="post" action="${pageContext.request.contextPath}/public/login">
			<table>
				<tr>
					<th>ID</th>
					<td><input type="text" name="memberId" value="goodee"></td>
				</tr>
				<tr>
					<th>PW</th>
					<td><input type="password" name="memberPw" value="1234"></td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;"><button>로그인</button></th>
				</tr>
			</table><br>
		</form>
		
		<a href="${pageContext.request.contextPath}/public/signUp"><button>회원가입</button></a>
	</div>
</body>
</html>