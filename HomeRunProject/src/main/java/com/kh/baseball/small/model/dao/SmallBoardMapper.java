package com.kh.baseball.small.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.baseball.small.model.vo.SmallBoard;
import com.kh.baseball.small.model.vo.SmallBoardList;
import com.kh.baseball.small.model.vo.SmallBoardReply;
import com.kh.baseball.small.model.vo.SmallBoardUpfile;

@Mapper
public interface SmallBoardMapper {

	int selectTotalCount();
	
	List<SmallBoard> selectBoardList(RowBounds rowBounds);
	
	List<SmallBoard> selectAdminList(RowBounds rowBounds);
	
	List<String> getTeamList();
	
	void insertBoard(SmallBoard smallBoard);
	
	void insertBoardFile(SmallBoardUpfile smallBoardUpfile);
	
	int selectAdminListCount();
	
	SmallBoard adminBoardDetail(Long boardNo);
	
	SmallBoardUpfile adminUpfileDetail(Long boardNo);
	
	int adminPermit(Long boardNo);
	
	int selectMyBoardListCount(int loginUserNo);
	
	List<SmallBoard> selectMyBoardList(RowBounds rowBounds, int loginUserNo);
	
	SmallBoard selectBoardByBoardNo(Long boardNo);
	
	SmallBoardUpfile selectUpfileByBoardNo(Long boardNo);
	
	int increaseCount(Long boardNo);
	
	int deleteBoard(Long boardNo);
	
	int updateBoard(SmallBoard smallBoard);
	
	int updateBoardUpfile(SmallBoardUpfile smallBoardUpfile);
	
	// 작성자 참가 리스트 관리 부분
	
	int selectMyParticipantListCount(Long boardNo);
	
	List<SmallBoardList> selectParticipantList(Long boardNo, RowBounds rowBounds);
	
	int writerAllow(int listNo);
	
	SmallBoardList selectListByListNo(int listNo);
	
	int updateBanReason(SmallBoardList updateSmallBoardList);
	
	int selectPosssibleDetail(SmallBoard smallBoard);
	
	void insertWriterAllow(SmallBoard smallBoard);
	
	int validateParticipateForm(SmallBoardList smallBoardList);
	
	int insertSmallBoardList(SmallBoardList updateSmallBoardList);
	
	// 전체조회 게시글 검색기능
	
	int searchListCount(Map<String, Object> map);
	
	List<SmallBoard> searchList(Map<String, Object> map, RowBounds rowBounds);
	
	// 댓글 기능
	
	int insertReply(SmallBoardReply reply);
	
	List<SmallBoardReply> selectReply(Long boardNo);
	
	SmallBoardReply selectReplyById(int replyNo);
	
	int deleteChat(int replyNo);
}
