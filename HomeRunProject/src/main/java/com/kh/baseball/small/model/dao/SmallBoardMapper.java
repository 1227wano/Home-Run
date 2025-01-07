package com.kh.baseball.small.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.baseball.small.model.vo.SmallBoard;
import com.kh.baseball.small.model.vo.SmallBoardList;
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
	
	List<SmallBoardList> selectParticipantList(RowBounds rowBounds, Long boardNo);
	
	int writerAllow(int listNo);
	
	SmallBoardList selectListByListNo(int listNo);
	
	int updateBanReason(SmallBoardList updateSmallBoardList);
	
	int selectPosssibleDetail(SmallBoard smallBoard);
	
	void insertWriterAllow(SmallBoard smallBoard);
	
	int validateParticipateForm(SmallBoardList smallBoardList);
}
