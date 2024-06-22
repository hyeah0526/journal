package com.gd.journal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JournalFile {
	private int fileNo;
	private int journalNo;
	private String fileName;
	private String originalName;
	private String fileType;
	private long fileSize;
	private String updateDate;
	private String crateDate;

}
