package com.kh.baseball.notice.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.baseball.notice.model.dao.NoticeMapper;
import com.kh.baseball.notice.model.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private final NoticeMapper mapper;
	
	@Override
	public Map<String, Object> selectNoticeList(int currentPage) {
		log.info("요청페이지 : {}", currentPage);
		
		Map<String, Object> result = new HashMap<String, Object>();
		int pageSize = 10;
		
		List<Notice> notices = mapper.selectAllNotices();
		result.put("notices", notices);
		result.put("currentPage", currentPage);
		result.put("pageSize", pageSize);
		
		log.info("Fatched {} notices for page : {}", notices.size(), currentPage);
		return result;
	}

	@Override
	@Transactional
	public void addNotice(Notice notice) {
		log.info("Adding new notice : {}", notice);
		mapper.insertNotice(notice);
	}

	@Override
	@Transactional
	public void updateNotice(Notice notice) {
		 log.info("Updating notice: {}", notice);
	     mapper.updateNotice(notice);
	}

	@Override
	@Transactional
	public void deleteNotice(int noticeNo) {
		log.info("Deleting notice with ID : {}", noticeNo);
		mapper.deleteNotice(noticeNo);
	}

	@Override
	public Notice getNoticeById(int noticeNo) {
		log.info("Fatching notice with ID : {}", noticeNo);
		return mapper.selectNoticeById(noticeNo);
	}

	@Override
	public void daleteNotice(int noticeNo) {
		
	}

}

