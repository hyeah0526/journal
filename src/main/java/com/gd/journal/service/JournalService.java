package com.gd.journal.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gd.journal.dto.Journal;
import com.gd.journal.dto.JournalEdit;
import com.gd.journal.dto.JournalFile;
import com.gd.journal.dto.JournalPost;
import com.gd.journal.mapper.JournalMapper;
import com.gd.journal.utill.Debug;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class JournalService {
	@Autowired JournalMapper journalMapper;
	
	/* 전체 조회 */
	public List<Map<String, Object>> getJournalList(int currentPage, int rowPerPage, String searchWord) {
		
		// 받아온 값 디버깅
		//log.debug(Debug.PHA + "currentPage--> " + currentPage + Debug.END);
		//log.debug(Debug.PHA + "rowPerPage--> " + rowPerPage + Debug.END);
		//log.debug(Debug.PHA + "searchWord--> " + searchWord + Debug.END);
		
		int beginRow = (currentPage-1)*rowPerPage;
		//log.debug(Debug.PHA + "beginRow--> " + beginRow + Debug.END);
		
		List<Map<String, Object>> list = new ArrayList<>();
		list = journalMapper.selectJournalList(beginRow, rowPerPage, searchWord);
		
		//log.debug(Debug.PHA + "list--> " + list + Debug.END);
		
		
		return list;
	}
	
	/* 내가 작성한 저널 조회 */
	public List<Map<String, Object>> getMyJournal(int currentPage, int rowPerPage, String searchWord, String memberId){
		
		// beginRow구하기
		int beginRow = (currentPage-1)*rowPerPage;
		
		// list에 담기
		List<Map<String, Object>> list = new ArrayList<>();
		list = journalMapper.selectMyJournalList(beginRow, rowPerPage, searchWord, memberId);
		log.debug(Debug.PHA + "list--> " + list + Debug.END);
		
		return list;
	}
	
	
	/* 조회 페이징 - 라이트페이지 */
	public int getLastPage(int rowPerPage, String searchWord, String MemberId) {
		
		log.debug(Debug.PHA + "MemberId --> " + MemberId + Debug.END);
		
		// 총 행의 개수구하기
		int totalCnt = journalMapper.selectJournalTotalCnt(searchWord, MemberId);
		log.debug(Debug.PHA + "totalCnt --> " + totalCnt + Debug.END);
						
		// 총행의 개수로 나머지 계산하기
		int lastPage = totalCnt / rowPerPage;
		if(totalCnt%rowPerPage != 0) {
			lastPage = lastPage+1;
		}
		log.debug(Debug.PHA + "lastPage --> " + lastPage + Debug.END);

		return lastPage;
	}
	
	
	
	
	/* 상세조회 */
	public Map<String, Object> getjournalOne(int journalNo){
		log.debug(Debug.PHA + "journalNo --> " + journalNo + Debug.END);
		
		// 상세조회
		Map<String, Object> map = journalMapper.selectJournalOne(journalNo);
		
		log.debug(Debug.PHA + "map --> " + map + Debug.END);
		
		
		return map;
	}
	
	
	/* 저널등록 */
	public void postJournal(JournalPost journalPost) {
		
		// 1. journal 테이블에 insert하기 전 Journal DTO에 값 세팅
		Journal journal = new Journal();
		journal.setMemberId(journalPost.getMemberId());
		journal.setTitle(journalPost.getTitle());
		journal.setType(journalPost.getType());
		journal.setContent(journalPost.getContent());
		
		// 2. journal테이블 insert하기
		int insertJournal = journalMapper.insertJournal(journal);
		if(insertJournal != 1) {
			// 등록 실패시 예외 발생시키기
			log.debug(Debug.PHA + "insertJournal에서 RuntimeException 발생! " + Debug.END);
			throw new RuntimeException();
		}
		
		// 3. journal_no 제대로 들어갔는지 확인하기
		log.debug(Debug.PHA + "journalNo -> " + journal.getJournalNo() + Debug.END);
		
		// 4. journalNo를 가지고 journal_file테이블에 등록하기
		MultipartFile mf = journalPost.getJournalFile(); // file 가져오기
		JournalFile file = new JournalFile(); // file dto생성 후 값 저장
		file.setJournalNo(journal.getJournalNo());
		file.setOriginalName(mf.getOriginalFilename());
		file.setFileType(mf.getContentType());
		file.setFileSize(mf.getSize());
		
		// 4-1. file name 세팅
		// prefix에 uuid를 사용해서 랜덤이름을 생성(-는 제거)
		String prefix = UUID.randomUUID().toString().replace("-", "");
		// 파일타입전(.의 위치)까지 길이를 담고
		int p = mf.getOriginalFilename().lastIndexOf(".");
		// 구해준 .의 위치부터 자른 후 suffix에 담아주기
		String suffix = mf.getOriginalFilename().substring(p);
		// uuid와 더해주고 file name 세팅하기
		file.setFileName(prefix+suffix);
		
		// 5. journal_file테이블 insert 하기
		int insertJournalPost = journalMapper.insertJournalFile(file);
		if(insertJournalPost != 1) {
			log.debug(Debug.PHA + "insertJournalPost에서 RuntimeException 발생! " + Debug.END);
			// 등록 실패시 예외 발생시키기
			throw new RuntimeException();
		}
		
		// 6. 파일을 c드라이브 upload밑의 파일에 저장
		File emptyFile = new File("c:/upload/"+prefix+suffix);
		try {
			// mf안에 있는 getinputStream을 가져와서 비어있는 emptyFile로 복사를 함 
			mf.transferTo(emptyFile);
		} catch (Exception e) {
			log.debug(Debug.PHA + "emptyFile 파일저장에서 Exception 발생! " + Debug.END);
			e.printStackTrace(); // 예외나면 전부 취소
			throw new RuntimeException(); // 일부러 예외를 발생시켜서 위에도 했던 insert명령도 전부 취소
		}
	}
	
	
	
	/* 저널 수정 */
	public void editJournal(JournalEdit journalEdit) {
		
		// journal 테이블 수정값 담기
		Journal journal = new Journal();
		journal.setJournalNo(journalEdit.getJournalNo());
		journal.setMemberId(journalEdit.getMemberId());
		journal.setTitle(journalEdit.getTitle());
		journal.setType(journalEdit.getType());
		journal.setContent(journalEdit.getContent());
		
		// journal 테이블 수정
		int updateJournal = journalMapper.updateJournal(journal);
		if(updateJournal != 1) {
			// 등록 실패시 예외 발생시키기
			throw new RuntimeException();
		}
		
		// journal_file 테이블 수정
		MultipartFile mf = journalEdit.getNewFile(); // file 가져오기
		JournalFile file = new JournalFile(); // file dto생성 후 값 저장
		file.setJournalNo(journalEdit.getJournalNo());
		file.setFileNo(journalEdit.getFileNo());
		file.setOriginalName(mf.getOriginalFilename());
		file.setFileType(mf.getContentType());
		file.setFileSize(mf.getSize());
		
		String prefix = UUID.randomUUID().toString().replace("-", "");
		int p = mf.getOriginalFilename().lastIndexOf(".");
		String suffix = mf.getOriginalFilename().substring(p);
		file.setFileName(prefix+suffix);
		
		int updateJournalFile = journalMapper.updateJournalFile(file);
		if(updateJournalFile != 1) {
			// 수정 실패시 예외 발생시키기
			throw new RuntimeException();
		}
		
		// 폴더에 파일저장
		File emptyFile = new File("c:/upload/"+prefix+suffix);
		try {
			// mf안에 있는 getinputStream을 가져와서 비어있는 emptyFile로 복사를 함 
			mf.transferTo(emptyFile);
			
			// 기존 파일 삭제
			String filePath = "c:/upload/" + journalEdit.getOldFileName();
		    File fileDel = new File(filePath);
		    fileDel.delete();
		    
		} catch (Exception e) {
			e.printStackTrace(); // 예외나면 전부 취소
			throw new RuntimeException(); // 일부러 예외를 발생시켜서 위에도 했던 insert명령도 전부 취소
		}
		
		
	}
	
	
	/* 저널 삭제 */
	public void deleteJournal(int journalNo, String fileName) {
		
		// 1. 저널 파일 먼저 삭제후
		int deleteJournalFile = journalMapper.deleteJournalFile(journalNo);
		if(deleteJournalFile != 1) {
			// 수정 실패시 예외 발생시키기
			throw new RuntimeException();
		}
		
		
		// 2. 저널 삭제
		int deleteJournal = journalMapper.deleteJournal(journalNo);
		if(deleteJournal != 1) {
			// 수정 실패시 예외 발생시키기
			throw new RuntimeException();
		}
		
		
		// 3. 폴더에서 이미지 삭제
		try {
			// 폴더 이미지에서 삭제
			String filePath = "c:/upload/" + fileName;
		    File fileDel = new File(filePath);
		    fileDel.delete();
		}catch (Exception e) {
			e.printStackTrace(); // 예외나면 전부 취소
			throw new RuntimeException(); // 일부러 예외를 발생시켜서 위에도 했던 delete명령도 전부 취소
		}
		
		
	}
	
	
	
	
}
