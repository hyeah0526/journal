package com.gd.journal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.journal.dto.Member;
import com.gd.journal.service.MemberService;
import com.gd.journal.utill.Debug;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	@Autowired MemberService memberService;
	
	// 테스트
	@GetMapping("/test")
	public String test() { 
		return "/test";
	}
	
	// 로그인 폼
	@GetMapping("/public/login")
	public String login() { 
		return "/public/login";
	}
	
	// 로그인 액션
	@PostMapping("/public/login")
	public String login(HttpSession session, 
							@RequestParam(name="memberId") String memberId, 
							@RequestParam(name="memberPw") String memberPw) {
		log.debug(memberId+"<--memberId");
		log.debug(memberPw+"<--memberPw");
		
		Map<String, String> loginChk = memberService.getLoginMember(memberId, memberPw);
		log.debug(Debug.PHA+"loginChk--> "+loginChk+Debug.END);
		if(loginChk != null) {
			log.debug(Debug.PHA+"로그인 성공"+Debug.END);
			session.setAttribute("loginUser", loginChk.get("memberId"));
			session.setAttribute("loginUserName", loginChk.get("name"));
		}
		
		/*
		final String onId = "goodee"; 
		final String onPw = "1234";
		if(id.equals(onId) && pw.equals(onPw)) {
			log.debug("로그인 성공");
			session.setAttribute("loginUser", id);
		}
		*/
		
		
		
		return "redirect:/auth/home";
	}
	
	
	// 로그인 폼
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/public/login";
	}
	
	
	// /auth/** 요청은 로그인 되었을때...
	@GetMapping("/auth/on")
	public String on() {
		return "/auth/on";
	}
	
	
	// 회원가입 폼
	@GetMapping("/public/signUp")
	public String signUp() { 
		return "/public/signUp";
	}
	
	// 회원가입하기
	@PostMapping("public/signUpForm")
	public String signUp(Member member) {
		log.debug(Debug.PHA+"member getMemberId--> "+ member.getMemberId() +Debug.END);
		log.debug(Debug.PHA+"member getName--> "+ member.getName() +Debug.END);
		log.debug(Debug.PHA+"member getMemberPw--> "+ member.getMemberPw() +Debug.END);
		log.debug(Debug.PHA+"member getBirth--> "+ member.getBirth() +Debug.END);
		
		memberService.setMember(member);
		
		 
		return "redirect:/public/login";
	}
	
	
	
}
