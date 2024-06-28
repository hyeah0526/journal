package com.gd.journal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gd.journal.mapper.MemberMapper;
import com.gd.journal.service.MemberService;
import com.gd.journal.utill.Debug;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Transactional
public class LoginRestController {
	@Autowired MemberService memberService;
	
	// 아이디 중복 확인
	@PostMapping("/public/signUp")
	public String idChk(@RequestParam(name="memberId") String memberId) {
		log.debug(Debug.PHA+"아이디 중복확인" + memberId +Debug.END);
			
		String idChk = memberService.getIdDoubleChk(memberId); 
		log.debug(Debug.PHA+"아이디 중복확인22" + idChk +Debug.END);
			
			
		return idChk;
	}
	
	/* 마이페이지 조회 */
	@PostMapping("/auth/header")
	public Map<String, Object> header(HttpSession session) {
		
		String memberId = (String)session.getAttribute("loginUser"); 
		log.debug(Debug.PHA + "memberId --> " + memberId + Debug.END);
		
		Map<String, Object> map = memberService.getMemberInfo(memberId);
		log.debug(Debug.PHA + "map --> " + map + Debug.END);
		
		return map;  
	}
	
	/* 회원탈퇴 */
	@PostMapping("/auth/removeMember")
	public int removeMember(HttpSession session
								, @RequestParam(name="checkOutPw") String checkOutPw) {
		
		log.debug(Debug.PHA + "checkOutPw --> " + checkOutPw + Debug.END);
		String memberId = (String)session.getAttribute("loginUser"); 
		
		// 비밀번호 검증
		int deleteRow = memberService.removeMember(memberId, checkOutPw);
		
		// 탈퇴 실패
		if(deleteRow == 0) { 
			return 0;
		}
		
		// 탈퇴 성공시 세션 죽이기
		session.invalidate();
		
		return 1;
	}
	
	

}
