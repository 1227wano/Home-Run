package com.kh.baseball.match.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.baseball.match.model.vo.Match;

@Mapper
public interface MatchMapper {

	List<Match> selectMatchList();
	
	Match selectMatch();
	
	
}
