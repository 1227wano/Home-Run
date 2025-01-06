package com.kh.baseball.small.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.small.model.vo.SmallBoard;

public interface SmallBoardService {

	Map<String, Object> selectBoardList(int Page, int boardLimit);
	
	List<String> selectTeamList();
	
	void insertBoard(SmallBoard smallBoard, MultipartFile upfile);
	
	Map<String, Object> selectAdminList(int page);
	
	Map<String, Object> adminListDetail(Long boardNo);
	
	void adminPermit(Long boardNo);
	
	Map<String, Object> selectMyBoardList(int page, int loginUserNo);
	
	Map<String, Object> selectDetailByBoardNo(Long boardNo);
	
	void deleteBoard(Long boardNo);
	
	Map<String, Object> selectUpdateByBoardNo(Long boardNo);
}
