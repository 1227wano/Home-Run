package com.kh.baseball.notice.model.service;

import java.util.Map;

import com.kh.baseball.notice.model.vo.Notice;

public interface NoticeService {

	Map<String, Object> selectNoticeList(int currentPage);

	void addNotice(Notice notice);
	
	void updateNotice(Notice notice);
	
	void daleteNotice(int noticeNo);
	
	Notice getNoticeById(int noticeNo);

	void deleteNotice(int noticeNo);
}
