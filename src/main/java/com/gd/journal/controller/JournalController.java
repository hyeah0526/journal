package com.gd.journal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class JournalController {

	
	@GetMapping("/test")
	public String test() {
		
		return "test";
	}
}
