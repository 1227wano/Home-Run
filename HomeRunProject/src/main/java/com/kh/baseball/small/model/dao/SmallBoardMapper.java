package com.kh.baseball.small.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.baseball.small.model.vo.SmallBoard;
import com.kh.baseball.small.model.vo.SmallBoardUpfile;

@Mapper
public interface SmallBoardMapper {

	int selectTotalCount();
	
	List<SmallBoard> selectBoardList(RowBounds rowBounds);
	
	List<String> getTeamList();
	
	void insertBoard(SmallBoard smallBoard);
	
	void insertBoardFile(SmallBoardUpfile smallBoardUpfile);
	
	int selectAdminListCount();
	
}
