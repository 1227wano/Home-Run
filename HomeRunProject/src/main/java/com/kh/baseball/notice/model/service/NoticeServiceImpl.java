package com.kh.baseball.notice.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.kh.baseball.common.PageInfo;
import com.kh.baseball.common.Pagination;
import com.kh.baseball.exception.NoticeNotFoundException;
import com.kh.baseball.notice.model.dao.NoticeMapper;
import com.kh.baseball.notice.model.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService {

	private final NoticeMapper mapper;
	
	private int getTotalCount() {
		int totalCount = mapper.selectTotalCount();
		if(totalCount == 0) {
			throw new NoticeNotFoundException("게시글이 없습니다.");
		}
		return totalCount;
	}
	
	private PageInfo getPageInfo(int totalCount, int page) {
		return Pagination.getPageInfo(totalCount, page, 5, 5);
	}
	
	private List<Notice> getNoticeList(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getPageLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getPageLimit());
		return mapper.selectNoticeList(rowBounds);
	}
	
	@Override
	public Map<String, Object> selectNoticeList(int currentPage) {
		/*
		int totalCount = mapper.selectTotalCount();
		
		if(totalCount == 0) {
			throw new NoticeNotFoundException("게시글이 없습니다.");
		}
		*/
		int totalCount = getTotalCount();
		
		// log.info("게시글 개수 : {}", totalCount);
		// log.info("요청 페이지 : {}", currentPage);
		// PageInfo pi = Pagination.getPageInfo(totalCount, currentPage, currentPage, 5);
		PageInfo pi = getPageInfo(totalCount, currentPage);
		/*
		int offset = (pi.getCurrentPage() - 1) * pi.getPageLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getPageLimit());
		List<Notice> notices = mapper.selectNoticeList(rowBounds);
		*/
		
		List<Notice> notices = getNoticeList(pi);
		// log.info("게시글목록 : {}", notices);
		
		Map<String, Object> map = new HashMap();
		map.put("notices", notices);
		map.put("pageInfo", pi);
		
		return map;
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

