package com.kh.baseball.match.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.baseball.match.model.vo.Match;

@Mapper
public interface MatchMapper {

	List<Match> selectMatchList(RowBounds rowBounds);
	
	Match selectMatch();

	int selectTotalCount();
	
	
}
