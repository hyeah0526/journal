package com.gd.journal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Journal {
	private int journalNo;
	private String memberId;
	private String title;
	private String type;
	private String content;
	private String updateDate;
	private String createDate;
}
