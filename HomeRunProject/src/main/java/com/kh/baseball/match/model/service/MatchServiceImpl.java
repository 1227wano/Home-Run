package com.kh.baseball.match.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.kh.baseball.common.PageInfo;
import com.kh.baseball.common.Pagination;
import com.kh.baseball.exception.NotFoundException;
import com.kh.baseball.match.model.dao.MatchMapper;
import com.kh.baseball.match.model.vo.Match;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableTransactionManagement
public class MatchServiceImpl implements MatchService {
	
	private final MatchMapper matchMapper;
	
	
	private int getTotalCount() {
		
		int totalCount = matchMapper.selectTotalCount();
		
		if(totalCount == 0) {
			throw new NotFoundException("경기 일정을 찾지 못했습니다.");
		}
		
		return totalCount;
	}
	
	private PageInfo getPageInfo(int currentPage) {
		
		int totalCount = getTotalCount();
		
		PageInfo pi = Pagination.getPageInfo(totalCount, currentPage, 3, 3);
		
		return pi;
	}
	
	@Override
	public Map<String, Object> selectMatchList(int currentPage) {
		
		Map<String, Object> map = new HashMap<>();
		
		PageInfo pi = getPageInfo(currentPage);
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		map.put("matchList", matchMapper.selectMatchList(rowBounds));
		
		map.put("pageInfo", pi);
		
		return map;
	}

	@Override
	public Match selectMatch() {
		return matchMapper.selectMatch();
	}

	
	
}
