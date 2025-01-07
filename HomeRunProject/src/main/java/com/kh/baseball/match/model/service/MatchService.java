package com.kh.baseball.match.model.service;

import java.util.Map;

import com.kh.baseball.match.model.vo.Match;

public interface MatchService {
	
	Map<String, Object> selectMatchList(int currentPage);
	
	Match selectMatch();
	

}
