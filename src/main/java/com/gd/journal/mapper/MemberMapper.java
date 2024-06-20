package com.gd.journal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.journal.dto.Member;

@Mapper
public interface MemberMapper {
	
	String selectLoginMember(String memberId, String memberPw); 
}
