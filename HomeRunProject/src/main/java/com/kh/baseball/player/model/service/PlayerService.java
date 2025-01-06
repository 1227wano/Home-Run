package com.kh.baseball.player.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.player.model.vo.Player;


public interface PlayerService {
	
	// 선수 등록 신청
	void savePlayer(Player player, MultipartFile upfile);

	// 선수 정보 일람 (가나다 순)
	Map<String, Object> findAllPlayerKorean(int currentPage);	
	
	// 선수 더보기
	List<Player> findMorePlayer(int page);
	
	/* 선수 정보 일람 (조회수 순) => 보류
	Map<String, Object> findAllPlayerCount(int currentPage);			
	*/

	// 선수 정보 상세조회
	Map<String, Object> selectPlayer(int playerNo);
	
	
	// 선수 정보 수정
	int updatePlayer(Player player);
	
	// 선수 정보 삭제
	int deletePlayer(int userNo);
	
	

	

	
	
	

}
