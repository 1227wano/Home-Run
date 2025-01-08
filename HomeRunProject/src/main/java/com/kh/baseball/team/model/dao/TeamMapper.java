package com.kh.baseball.team.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kh.baseball.team.model.vo.Team;

public interface TeamMapper {

	void insertTeam(Team team);

	int getTotalCount();

	List<Team> selectAllTeam(RowBounds rowbounds);

	Team selectTeamDetail(int teamNo);

	Team mypageTeamDetail(int userNo);

	
	
}
