package com.kh.baseball.notice.model.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.notice.model.vo.Notice;

public interface NoticeService {

	Map<String, Object> selectNoticeList(int currentPage);

	void addNotice(Notice notice, MultipartFile upfile);
	
	void updateNotice(Notice notice);
	
	void daleteNotice(int noticeNo);
	
	Notice getNoticeById(int noticeNo);

	void deleteNotice(int noticeNo);
}
