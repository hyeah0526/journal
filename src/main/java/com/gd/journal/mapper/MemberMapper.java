package com.gd.journal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.journal.dto.Member;

@Mapper
public interface MemberMapper {
	
	Map<String, String> selectLoginMember(String memberId, String memberPw); 
	
	Map<String, String> selectmyPage(String memberId);
	
	// 아이디 중복체크
	String selectIdDoubleChk(String memberId);
	
	// 아이디 등록
	int insertMember(Member member);
}
