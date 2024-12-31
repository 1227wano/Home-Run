package com.kh.baseball.notice.model.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.notice.model.vo.Notice;

public interface NoticeService {

	Map<String, Object> selectAllNotices(int currentPage);
	
	Map<String, Object> selectNoticeById(int noticeNo);
	
	void addNotice(Notice notice, MultipartFile upfile);
	
	void updateNotice(Notice notice);
	
	void deleteNotice(int noticeNo);
	

	
}
