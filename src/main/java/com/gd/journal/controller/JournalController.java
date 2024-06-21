package com.gd.journal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gd.journal.service.JournalService;
import com.gd.journal.utill.Debug;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@Controller
public class JournalController {
	
	@Autowired JournalService journalService;
	
	/* 메인페이지 */
	@GetMapping("/auth/home")
	public String home(Model model
						,@RequestParam(name="currentPage", defaultValue="1") int currentPage
						,@RequestParam(name="rowPerPage", defaultValue="3") int rowPerPage
						,@RequestParam(name="searchWord", defaultValue="") String searchWord) {
		// 디버깅
		//log.debug(Debug.PHA + "currentPage --> " + currentPage + Debug.END);
		//log.debug(Debug.PHA + "rowPerPage --> " + rowPerPage + Debug.END);
		//log.debug(Debug.PHA + "searchWord --> " + searchWord + Debug.END);
		
		// 전체 조회
		List<Map<String, Object>> list = journalService.getJournalList(currentPage, rowPerPage, searchWord);
		int lastPage = journalService.getLastPage(rowPerPage, searchWord);
		
		model.addAttribute("list", list);	// 전체 조회
		model.addAttribute("lastPage", lastPage);	// 마지막 페이지
		model.addAttribute("rowPerPage", rowPerPage);	// 한페이지당 보여줄 수
		model.addAttribute("currentPage", currentPage);	// 최근 페이지
		model.addAttribute("searchWord", searchWord);	// 검색어
		
		return "/auth/home";
	}
	
	
	/* 저널 상세보기 */
	@GetMapping("/auth/journalDetail")
	public String journalDetail(Model model
									,@RequestParam(name="journalNo") int journalNo
									,@RequestParam(name="journalEdit", defaultValue="") String journalEdit) {
		
		log.debug(Debug.PHA + "journalNo --> " + journalNo + Debug.END);
		log.debug(Debug.PHA + "journalEdit --> " + journalEdit + Debug.END);
		
		Map<String, Object> map = journalService.getjournalOne(journalNo);
		log.debug(Debug.PHA + "map --> " + map + Debug.END);
		
		model.addAttribute("map", map);	// 상세조회
		model.addAttribute("journalEdit", journalEdit); // 수정확인용
		
		return"/auth/journalDetail";
	}
	
	
	/* 본인이 작성한 저널 보기 */
	@GetMapping("/auth/myJournal")
	public String myJournal() {
		
		return"/auth/myJournal";
	}
	
	
	/* 전체 저널 조회 */
	@GetMapping("/auth/otherJournal")
	public String otherJournal() {
		
		return"/auth/otherJournal";
	}
}
