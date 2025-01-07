package com.kh.baseball.match.model.service;

import java.util.List;

import com.kh.baseball.match.model.vo.Match;

public interface MatchService {
	
	List<Match> selectMatchList();
	
	Match selectMatch();
	

}
