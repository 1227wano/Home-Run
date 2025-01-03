package com.kh.baseball.notice.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.notice.model.vo.Notice;

public interface NoticeService {

	List<Notice> selectAllNotices(Map<String, Object> params);
	
	Map<String, Object> selectNoticeById(Long noticeNo);
	
	void addNotice(Notice notice, MultipartFile upfile);
	
	void updateNotice(Notice notice);
	
	void deleteNotice(Long noticeNo);

	Notice getNoticeById(long noticeNo);

	int getTotalNoticeCount();
	



	

	
}
