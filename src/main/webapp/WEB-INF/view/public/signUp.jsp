<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sign Up</title>
<!-- jQuery사용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<form action="">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td><input name="memberId" id="memberId" min="6"> </td>
				<td><button type="button" id="checkIdBtn">아이디 확인</button> </td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td colspan="2"><input type="password" id="memberPw"> </td>
			</tr>
			<tr>
				<th>이름</th>
				<td colspan="2"><input type="text" id="memberName"> </td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td colspan="2"><input type="date" id="memberBirth"> </td>
			</tr>
		</table>
		<button type="button" id="signinBtn">회원가입</button>
	</form>

<script>
	// 아이디 중복체크 검사
	$('#checkIdBtn').click(function(){
		// 기본 고유 동작 방지함
		let memberId = $('#memberId').val();
		let regExp = /[^0-9a-zA-Z]/g; // 영문과 숫자만 허용
		//console.log(memberId + " <-- memberId 체크"); 
		
		if(regExp.test(memberId)){
			alert("영어와 숫자만 입력 가능합니다!");
			$('#memberId').val("");
			$('#memberId').focus();
			return false;
		}else if(memberId.length <=6){
			alert("6자이상 아이디를 설정해주세요");
			$('#memberId').val("");
			$('#memberId').focus();
			return false;
			
		}else{
			
			$.ajax({
				url:'${pageContext.request.contextPath}/public/signUp',
				method:'post',
				data: {'memberId':memberId},
				success:function(json){
					
					if(json == memberId){
						alert('회원 가입 가능한 아이디 입니다.');
						$('#memberId').val($('#memberId').val());
						$("#memberId").attr("readonly",true); 
						
					}else{
						alert('사용중인 아이디 입니다. 다시 시도해주세요');
						$('#memberId').val("");
						$('#memberId').focus();
					}
					
				}
				
			});
		
		}
		
	});
	
	$('#signinBtn').click(function(){
		// 아이디 작성 확인
		let memberId = $('#memberId').val();
		if(memberId == ''){
			alert('아이디 입력해주세요');
			$('#memberId').focus();
			return;
		}
		
		// 비밀번호
		if($('#memberPw').val() == ''){
			alert('비밀번호를 입력해주세요');
			$('#memberPw').focus();
			return;
		}
		
		if($('#memberPw').val().length <=6){
			alert('비밀번호를 6자 이상 입력해주세요');
			$('#memberPw').focus();
			return;
		}
		
		// 이름
		let kor = /^[ㄱ-ㅎ가-힣]+$/; // 한글만 입력가능
		let name = $('#memberName').val();
		if(name == ''){
			alert('이름을 입력해주세요');
			$('#memberName').focus();
			return;
		}
		
		if(!kor.test(name) || name.length < 3){
			alert('이름을 3자 이상 한글로 입력해주세요');
		    $('#memberName').focus();
		    return;
		}
		
		// 생년월일
		if($('#memberBirth').val() == ''){
			alert('생년월일을 입력해주세요');
			$('#memberBirth').focus();
			return;
		}
	});
	
</script>
</body>
</html>