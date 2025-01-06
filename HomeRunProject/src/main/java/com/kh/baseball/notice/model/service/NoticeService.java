package com.kh.baseball.notice.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.notice.model.vo.Notice;

public interface NoticeService {

	Map<String, Object> selectNoticeList(int currentPage);
	
	void insertNotice(Notice notice, MultipartFile upfile);
	
	Map<String, Object> selectNoticeById(Long noticeNo);
	
	void updateNotice(Notice notice, MultipartFile upfile);
	
	void deleteNotice(Long noticeNo, String attachMent);

	Notice getNoticeById(long noticeNo);

	int getTotalCount();
	



	

	
}
