<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sign Up</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

		<table border="1">
			<tr>
				<th>아이디</th>
				<td><input name="memberId" id="memberId"> </td>
				<td><button id="checkIdBtn">아이디 확인</button> </td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td colspan="2"><input> </td>
			</tr>
			<tr>
				<th>이름</th>
				<td colspan="2"><input> </td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td colspan="2"><input type="date"> </td>
			</tr>
		</table>
		<button>회원가입</button>
		
		<div>eee ${idChk}</div>

<script>
	$(document).ready(function(){
		$('#checkIdBtn').click(function(e){
			// 기본 고유 동작 방지함
			e.preventDefault(); 
			let memberId = $('#memberId').val();
			//console.log(memberId); 
			if(memberId != ''){
				//console.log(memberId+" <--member Id chk"); 
				
				$.ajax({
					url: '${pageContext.request.contextPath}/public/idChk',
					type: 'GET',
					data: {memberId: memberId},
					success: function(data){
						// 
					}
				})
			}
		})
	})
</script>
</body>
</html>