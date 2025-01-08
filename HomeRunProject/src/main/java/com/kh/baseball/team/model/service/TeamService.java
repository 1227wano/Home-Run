package com.kh.baseball.team.model.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.team.model.vo.Team;

public interface TeamService {

	// 팀 창설 신청
	void insertTeam(Team team, MultipartFile upfile);

	// 전체팀정보 조회
	Map<String, Object> selectAllTeam(int page);

	
	
	// 팀 상세보기
	Map<String, Object> selectTeam(int teamNo);

	// 팀정보 수정 신청
	void updateTeam(Team team, MultipartFile upfile);

	// 팀정보 삭제
	void deleteTeam(int teamNo);
	
	
	
	
}
