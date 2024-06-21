package com.gd.journal.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
}
