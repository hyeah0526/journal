package com.gd.journal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.journal.dto.Member;

@Mapper
public interface MemberMapper {
	
	Map<String, String> selectLoginMember(String memberId, String memberPw); 
}
