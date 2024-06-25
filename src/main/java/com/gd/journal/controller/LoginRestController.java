package com.gd.journal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gd.journal.mapper.MemberMapper;
import com.gd.journal.service.MemberService;
import com.gd.journal.utill.Debug;

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

}
