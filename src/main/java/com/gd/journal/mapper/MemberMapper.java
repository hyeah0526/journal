package com.gd.journal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.journal.dto.Member;

@Mapper
public interface MemberMapper {
	
	Map<String, String> selectLoginMember(String memberId, String memberPw); 
	
	Map<String, Object> selectmyPage(String memberId);
	
	// 아이디 중복체크
	String selectIdDoubleChk(String memberId);
	
	// 아이디 등록
	int insertMember(Member member);
	
	// 회원 탈퇴(아이디 비활성화)
	int updateMember(String memberId, String checkOutPw);
}
