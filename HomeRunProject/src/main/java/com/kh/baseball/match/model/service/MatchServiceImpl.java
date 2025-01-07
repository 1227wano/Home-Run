package com.kh.baseball.match.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
	
	@Override
	public List<Match> selectMatchList() {
		return matchMapper.selectMatchList();
	}

	@Override
	public Match selectMatch() {
		return matchMapper.selectMatch();
	}

	
	
}
