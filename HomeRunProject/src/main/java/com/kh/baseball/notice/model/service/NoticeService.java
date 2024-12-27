package com.kh.baseball.notice.model.service;

import java.util.Map;

import com.kh.baseball.notice.model.vo.Notice;

public interface NoticeService {

	Map<String, Object> selectNoticeList(int currentPage);

	void insertNotice(Notice notice);

	Notice selectNotice(int noticeNo);

	void updateNotice(Notice notice);

	void delecteNotice(int noticeNo);
}
