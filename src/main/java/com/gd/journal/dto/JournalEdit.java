package com.gd.journal.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JournalEdit {
	// 수정을 위한 dto
	private int journalNo;
	private int fileNo;
	private String memberId;
	private String title;
	private String type;
	private String content;
	private String oldFileName;
	private MultipartFile newFile;
}
