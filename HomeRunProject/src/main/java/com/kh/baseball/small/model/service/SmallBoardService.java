package com.kh.baseball.small.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.member.model.vo.Member;
import com.kh.baseball.small.model.vo.SmallBoard;
import com.kh.baseball.small.model.vo.SmallBoardList;
import com.kh.baseball.small.model.vo.SmallBoardReply;
import com.kh.baseball.small.model.vo.SmallBoardUpfile;

public interface SmallBoardService {

	Map<String, Object> selectBoardList(int Page, int boardLimit);
	
	List<String> selectTeamList();
	
	void insertBoard(SmallBoard smallBoard, MultipartFile upfile);
	
	Map<String, Object> selectAdminList(int page);
	
	Map<String, Object> adminListDetail(Long boardNo);
	
	void adminPermit(Long boardNo);
	
	Map<String, Object> selectMyBoardList(int page, int loginUserNo);
	
	Map<String, Object> selectDetailByBoardNo(Long boardNo, HttpSession session);
	
	void deleteBoard(Long boardNo);
	
	Map<String, Object> selectUpdateByBoardNo(Long boardNo);
	
	void update(SmallBoard smallBoard, MultipartFile upfile, SmallBoardUpfile file);
	
	Map<String, Object> selectParticipantList(Long boardNo, int Page, int boardLimit);
	
	void writerPermission(int listNo);
	
	void updateBanReason(SmallBoardList smallBoardList);
	
	SmallBoardList validateParticipateForm(Long boardNo, Member member);
	
	void insertParticipate(SmallBoardList smallBoardList);
	
	Map<String, Object> searchList(Map<String, Object> map);
	
	// 댓글기능
	
	int insertReply(SmallBoardReply reply);
	
	List<SmallBoardReply> selectReply(Long boardNo);
	
	int deleteChat(int replyNo, HttpSession session);
}
