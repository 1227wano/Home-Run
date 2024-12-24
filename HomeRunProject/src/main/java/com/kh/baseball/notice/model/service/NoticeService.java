package com.kh.baseball.notice.model.service;

import java.util.Map;

public interface NoticeService {

	// 공지사항 목록 조회
	Map<String, Object> selectNoticeList(int currentPage);
}
