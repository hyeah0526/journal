package com.gd.journal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.journal.dto.Journal;
import com.gd.journal.dto.JournalFile;

@Mapper
public interface JournalMapper {
	
	// 전체 조회
	List<Map<String, Object>> selectJournalList(int beginRow, int rowPerPage, String searchWord);
	
	// 전체 조회 총 카운트
	int selectJournalTotalCnt(String searchWord);
	
	// 상세조회
	Map<String, Object> selectJournalOne(int journalNo);
	
	// 저널 등록
	int insertJournal(Journal journal);
	
	// 저널 파일 등록
	int insertJournalFile(JournalFile journalFile);
	
}
