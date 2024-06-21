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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class JournalController {
	
	@Autowired JournalService journalService;

	@GetMapping("/home")
	public String home(Model model
						,@RequestParam(name="currentPage", defaultValue="1") int currentPage
						,@RequestParam(name="rowPerPage", defaultValue="3") int rowPerPage
						,@RequestParam(name="searchWord", defaultValue="") String searchWord) {
		
		
		List<Map<String, Object>> list = journalService.getJournalList(currentPage, rowPerPage, searchWord);
		int lastPage = journalService.getLastPage(rowPerPage, searchWord);
		
		model.addAttribute("list", list);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		
		return "home";
	}
}
