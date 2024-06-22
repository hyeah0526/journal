package com.gd.journal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gd.journal.dto.Journal;
import com.gd.journal.dto.JournalEdit;
import com.gd.journal.dto.JournalFile;
import com.gd.journal.dto.JournalPost;
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
		
		//log.debug(Debug.PHA + "journalNo --> " + journalNo + Debug.END);
		//log.debug(Debug.PHA + "journalEdit --> " + journalEdit + Debug.END);
		
		Map<String, Object> map = journalService.getjournalOne(journalNo);
		//log.debug(Debug.PHA + "map --> " + map + Debug.END);
		
		model.addAttribute("map", map);	// 상세조회
		model.addAttribute("journalEdit", journalEdit); // 수정확인용
		
		return"/auth/journalDetail";
	}
	
	/* 저널 수정하기 */
	@PostMapping("/auth/journalDetail")
	public String journalDetail(JournalEdit journalEdit) {
		
		// 디버깅
		//log.debug(Debug.PHA + "journalNo --> " + journalEdit.getJournalNo() + Debug.END);
		//log.debug(Debug.PHA + "getFileNo --> " + journalEdit.getFileNo() + Debug.END);
		//log.debug(Debug.PHA + "getMemberId --> " + journalEdit.getMemberId()+ Debug.END);
		//log.debug(Debug.PHA + "getType --> " + journalEdit.getType()+ Debug.END);
		//log.debug(Debug.PHA + "getTitle --> " + journalEdit.getTitle()+ Debug.END);
		//log.debug(Debug.PHA + "getContent --> " + journalEdit.getContent()+ Debug.END);
		//log.debug(Debug.PHA + "getOldFileName --> " + journalEdit.getOldFileName()+ Debug.END);
		//log.debug(Debug.PHA + "getNewFile --> " + journalEdit.getNewFile().getOriginalFilename()+ Debug.END);
		
		
		// 수정
		journalService.editJournal(journalEdit);
		 
		
		return"redirect:/auth/journalDetail?journalNo="+journalEdit.getJournalNo(); // 리다이렉트
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
	
	/* 저널 등록하기 */
	@GetMapping("/auth/journalPost")
	public String journalPost() {
		
		return"/auth/journalPost";
	}
	
	@PostMapping("/auth/journalPost")
	public String journalPost(JournalPost journalpost) {
		
		// 디버깅
		//log.debug(Debug.PHA + "journal getMemberId --> " + journalpost.getMemberId() + Debug.END);
		//log.debug(Debug.PHA + "journal getTitle --> " + journalpost.getTitle() + Debug.END);
		//log.debug(Debug.PHA + "journal getType --> " + journalpost.getType() + Debug.END);
		//log.debug(Debug.PHA + "journal getContent --> " + journalpost.getContent() + Debug.END);
		//log.debug(Debug.PHA + "journal getJournalFile --> " + journalpost.getJournalFile().getOriginalFilename() + Debug.END);
		
		// 저널등록 (journal / journal_file 테이블)
		journalService.postJournal(journalpost);
		
		
		return"redirect:/auth/myJournal"; // myJournal로 리다이렉트
	}
	
	
	/* 저널 삭제 */
	@GetMapping("/auth/journalDelete")
	public String journalDelete(@RequestParam(name="journalNo") int journalNo
								,@RequestParam(name="fileName") String fileName) {
		
		//log.debug(Debug.PHA + "journalNo --> " + journalNo + Debug.END);
		//log.debug(Debug.PHA + "fileName --> " + fileName + Debug.END);
		
		// 저널 삭제
		journalService.deleteJournal(journalNo, fileName);
		
		return "redirect:/auth/home";
	}
	
}
