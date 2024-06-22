package com.gd.journal.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalPost {
	private String memberId;
	private String title;
	private String type;
	private String content;
	private MultipartFile journalFile;
}
