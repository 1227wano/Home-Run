package com.kh.baseball.notice.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.baseball.notice.model.dao.NoticeMapper;
import com.kh.baseball.notice.model.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService {

	private final NoticeMapper mapper;
	
	@Override
	public Map<String, Object> selectNoticeList(int currentPage) {
		
		int totalCount = mapper.selectTotalCount();
		
		log.info("게시글 개수 : {}", totalCount);
		
		return null;
	}

	@Override
	public void insertNotice(Notice notice) {

	}

	@Override
	public Notice selectNotice(int noticeNo) {
		return null;
	}

	@Override
	public void updateNotice(Notice notice) {

	}

	@Override
	public void delecteNotice(int noticeNo) {

	}

}

