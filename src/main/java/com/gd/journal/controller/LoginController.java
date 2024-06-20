package com.gd.journal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.journal.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	@Autowired MemberService memberService;
	
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
		
		String loginChk = memberService.getLoginMember(memberId, memberPw);
		if(loginChk != null) {
			log.debug("로그인 성공");
			session.setAttribute("loginUser", memberId);
		}
		
		/*
		final String onId = "goodee"; 
		final String onPw = "1234";
		if(id.equals(onId) && pw.equals(onPw)) {
			log.debug("로그인 성공");
			session.setAttribute("loginUser", id);
		}
		*/
		
		
		
		return "redirect:/auth/on";
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
	
	
	
}
