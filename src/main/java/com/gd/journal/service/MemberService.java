package com.gd.journal.service;

import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.journal.mapper.MemberMapper;
import com.gd.journal.utill.Debug;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MemberService {
	@Autowired MemberMapper memberMapper;
	
	public Map<String, String> getLoginMember(String memberId, String memberPw) {
		
		Map<String, String> login = memberMapper.selectLoginMember(memberId, memberPw);
		log.debug(Debug.PHA+"login--> "+login+Debug.END);
		
		return login;
	}
	
	public Map<String, String> getMemberInfo(String memberId) {
		
		Map<String, String> member = memberMapper.selectmyPage(memberId);
		log.debug(Debug.PHA+"member--> "+member+Debug.END);
		
		
		return member;
		
	}
	
	/* 아이디 중복확인 */
	public String getIdDoubleChk(String memberId) {
		
		String idChk = memberMapper.selectIdDoubleChk(memberId);
		if(idChk == null) { // 아이디 사용 가능
			idChk = memberId;
			
		}else { // 아이디 사용 불가
			idChk = "";
		}
				
		return idChk;
		
	}
	
}
