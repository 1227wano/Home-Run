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

	// 메인게시글
	Map<String, Object> selectBoardList(int Page, int boardLimit);
	
	List<String> selectTeamList();
	
	void insertBoard(SmallBoard smallBoard, MultipartFile upfile);
	
	
	// 관리자 허가게시글
	Map<String, Object> selectAdminList(int page);
	
	Map<String, Object> adminListDetail(Long boardNo);
	
	void adminPermit(Long boardNo);
	
	
	// 작성자 기준 본인 게시글
	Map<String, Object> selectMyBoardList(int page, int loginUserNo);
	
	Map<String, Object> selectDetailByBoardNo(Long boardNo, HttpSession session);
	
	
	// 작성자, 관리자 기준 게시글 삭제
	void deleteBoard(Long boardNo);
	
	// 작성자 기준 게시글 수정
	Map<String, Object> selectUpdateByBoardNo(Long boardNo);
	
	void update(SmallBoard smallBoard, MultipartFile upfile, SmallBoardUpfile file);
	
	// 본인 게시글 참가자 리스트
	Map<String, Object> selectParticipantList(Long boardNo, int Page, int boardLimit);
	
	void writerPermission(int listNo);
	
	void updateBanReason(SmallBoardList smallBoardList);
	
	// 로그인 유저 참가신청
	SmallBoardList validateParticipateForm(Long boardNo, Member member);
	
	void insertParticipate(SmallBoardList smallBoardList);
	
	// 메인 게시글 키워드를 통해 전체 조회
	Map<String, Object> searchList(Map<String, Object> map);
	
	// 댓글기능
	int insertReply(SmallBoardReply reply);
	
	List<SmallBoardReply> selectReply(Long boardNo);
	
	int deleteChat(int replyNo, HttpSession session);
}
