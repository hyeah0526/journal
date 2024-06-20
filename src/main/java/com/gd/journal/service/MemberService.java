package com.gd.journal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.journal.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MemberService {
	@Autowired MemberMapper memberMapper;
	
	public String getLoginMember(String memberId, String memberPw) {
		
		String login = memberMapper.selectLoginMember(memberId, memberPw);
		
		return login;
	}
	
}
