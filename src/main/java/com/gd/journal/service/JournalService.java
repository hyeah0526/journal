package com.gd.journal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.journal.mapper.JournalMapper;
import com.gd.journal.utill.Debug;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class JournalService {
	@Autowired JournalMapper journalMapper;
	
	public List<Map<String, Object>> getJournalList(int currentPage, int rowPerPage, String searchWord) {
		
		// 받아온 값 디버깅
		log.debug(Debug.PHA + "currentPage--> " + currentPage + Debug.END);
		log.debug(Debug.PHA + "rowPerPage--> " + rowPerPage + Debug.END);
		log.debug(Debug.PHA + "searchWord--> " + searchWord + Debug.END);
		
		int beginRow = (currentPage-1)*rowPerPage;
		log.debug(Debug.PHA + "beginRow--> " + beginRow + Debug.END);
		
		List<Map<String, Object>> list = new ArrayList<>();
		list = journalMapper.selectJournalList(beginRow, rowPerPage, searchWord);
		
		
		log.debug(Debug.PHA + "list--> " + list + Debug.END);
		
		
		return list;
	}
	
	
	/* 페이징 - 라이트페이지 */
	public int getLastPage(int rowPerPage, String searchWord) {
		
		// 총 행의 개수구하기
		int totalCnt = journalMapper.selectJournalTotalCnt(searchWord);
		log.debug(Debug.PHA + "totalCnt --> " + totalCnt + Debug.END);
						
		// 총행의 개수로 나머지 계산하기
		int lastPage = totalCnt / rowPerPage;
		if(totalCnt%rowPerPage != 0) {
			lastPage = lastPage+1;
		}
		log.debug(Debug.PHA + "lastPage --> " + lastPage + Debug.END);

		return lastPage;
	}
	
}
