<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" id="btn-modal">MyPage</a></li>
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4">Welcome, ${loginUserName} !</a></li>
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="${pageContext.request.contextPath}/logout">logout</a></li>
                </ul>
            </div>
        </div>
    </nav>
    
    <!-- 마이페이지 모달창 -->
    <div id="modal" class="modal-overlay">
        <div class="modal-window" style="z-index: 1050;">
            <div class="title" id="resultMemberID"></div>
            <div class="close-area">X</div>
            <div class="content" id="resultMember"></div>
            <div style="text-align: center;">
            	<button class="btn btn-light text-uppercase" id="memberRemoveBtn">회원탈퇴</button>
            </div>
        </div>
    </div>

    
<script>
	let modal = document.getElementById("modal");
            
	function modalOn() {
		// 모달창이 켜지면 ajax로 로그인 한 멤버 정보를 데이터 받아오기
		$.ajax({
			url:'${pageContext.request.contextPath}/auth/header',
			method: 'post',
			success:function(json) {
				console.log(json);
				$('#resultMember').html(
					'<p>아이디: '+json.memberId+'</p>'+
					'<p>생년월일: '+json.birth+'</p>'
				);
				
				$('#resultMemberID').html(
					'<h3>' + json.name + '님, 안녕하세요' + '</h3>'
				);
			}
		});
            	
		modal.style.display = "flex";
	};

	function isModalOn() {
		return modal.style.display === "flex";
	};

	function modalOff() {
		modal.style.display = "none";
	};

	let btnModal = document.getElementById("btn-modal");
	let closeBtn = document.querySelector("#modal .close-area");

	btnModal.addEventListener("click", e => {
		modalOn();
	});

	closeBtn.addEventListener("click", e => {
		modalOff();
	});

	// 모달창 외부 클릭 시 닫기
	modal.addEventListener("click", e => {
		if (e.target === modal) {
 			modalOff();
		};
		
		
	// 회원 탈퇴 버튼을 눌렀을 때 알라트 보여주기 
	$('#memberRemoveBtn').click(function(){
		let checkOutPw = prompt('회원탈퇴를 원할 경우 비밀번호를 입력해주세요');
		
		// 비밀번호 입력을 했으면 보내기
		if(checkOutPw){
			console.log(checkOutPw);
			console.log('비밀번호 입력 후 확인누름');
			
			$.ajax({
				url: '${pageContext.request.contextPath}/auth/removeMember',
				method: 'post',
				data: {'checkOutPw':checkOutPw},
				success:function(json) {
					console.log(json+' < -----jsonn');
					if(json == 0){
						alert('탈퇴 처리에 실패하였습니다.');
					}
					if(json == 1){
						alert('탈퇴 처리가 완료되었습니다.');
						location.href = '${pageContext.request.contextPath}/public/login';
					}
				},
				error : function(){
					alert('비밀번호가 틀렸습니다. 다시 시도해주세요.');
				}
					
			});
			
		};
		
		// 취소했을 경우
		if(checkOut == null){
			console.log('취소누름');
			return false;
		};
		
	});
            
});
            
</script>