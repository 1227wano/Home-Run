package com.kh.baseball.small.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.common.PageInfo;
import com.kh.baseball.exception.BoardNotFoundException;
import com.kh.baseball.small.model.dao.SmallBoardMapper;
import com.kh.baseball.small.model.vo.SmallBoard;
import com.kh.baseball.small.model.vo.SmallBoardUpfile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmallBoardServiceImpl implements SmallBoardService {

	private final SmallBoardMapper mapper;
	private final SmallBoardValidator validator;
	
	@Override
	public Map<String, Object> selectBoardList(int Page, int boardLimit) {
		
		int totalCount = validator.getTotalCount();
		
		PageInfo pi = validator.getPageInfo(totalCount, Page, boardLimit);
		
		List<SmallBoard> boards = validator.getBoardList(pi);
		
		Map<String, Object> map = new HashMap();
		map.put("smallBoard", boards);
		map.put("pageInfo", pi);
		
		return map;
	}

	@Override
	public List<String> selectTeamList() {
		
		List<String> teams = mapper.getTeamList();
		
		return teams;
	}

	@Override
	@Transactional
	public void insertBoard(SmallBoard smallBoard, MultipartFile upfile) {
		validator.validateBoard(smallBoard);
		
		mapper.insertBoard(smallBoard);
		
		if(!!!("".equals(upfile.getOriginalFilename()))) {
			SmallBoardUpfile smallBoardUpfile = validator.handleFileUpload(upfile);
			mapper.insertBoardFile(smallBoardUpfile);
		}
	}

	@Override
	public Map<String, Object> selectAdminList(int page) {

		int totalCount = validator.getAdminTotalCount();
		
		PageInfo pi = validator.getPageInfo(totalCount, page);
		
		List<SmallBoard> boards = validator.getadminBoardList(pi);
		
		Map<String, Object> map = new HashMap();
		map.put("adminList", boards);
		map.put("pageInfo", pi);
		
		return map;
	}

	@Override
	public Map<String, Object> adminListDetail(Long boardNo) {

		SmallBoard adminDetail = mapper.adminBoardDetail(boardNo);
		SmallBoardUpfile upfile = mapper.adminUpfileDetail(boardNo);
		
		
		Map<String, Object> map = new HashMap();
		map.put("adminDetail", adminDetail);
		map.put("file", upfile);
		
		return map;
	}

	@Override
	public void adminPermit(Long boardNo) {

		int num = mapper.adminPermit(boardNo);
		
		if(num <= 0) {
			throw new BoardNotFoundException("게시글을 허가하지 못했습니다.");
		}
	}

	@Override
	public Map<String, Object> selectMyBoardList(int page, int loginUserNo) {

		int totalCount = mapper.selectMyBoardListCount(loginUserNo);
		
		PageInfo pi = validator.getPageInfo(totalCount, page);
		
		List<SmallBoard> boards = validator.getBoardList(pi, loginUserNo);
		
		Map<String, Object> map = new HashMap();
		map.put("myBoards", boards);
		map.put("pageInfo", pi);
		
		return map;
	}

	@Override
	@Transactional
	public Map<String, Object> selectDetailByBoardNo(Long boardNo) {

		SmallBoard smallBoard = validator.selectBoardByBoardNo(boardNo);
		
		validator.incrementViewCount(boardNo);
		
		SmallBoardUpfile smallBoardUpfile = mapper.selectUpfileByBoardNo(boardNo);
		
		Map<String, Object> map = new HashMap();
		map.put("smallBoard", smallBoard);
		if(smallBoardUpfile != null) {
			map.put("file", smallBoardUpfile);
		}
		
		return map;
	}

	@Override
	@Transactional
	public void deleteBoard(Long boardNo) {

		validator.validateBoardNo(boardNo);
		
		int result = mapper.deleteBoard(boardNo);
		
		if(result <= 0) {
			throw new BoardNotFoundException("게시글 삭제 실패");
		}
		
		SmallBoardUpfile deleteBoard = validator.selectFileByBoardNo(boardNo);
		
		if(deleteBoard != null) {
			validator.deleteFile(deleteBoard);
		}
	}

	@Override
	public Map<String, Object> selectUpdateByBoardNo(Long boardNo) {

		Map<String, Object> map = new HashMap();
		
		SmallBoard smallBoard = validator.selectBoardByBoardNo(boardNo);
		map.put("smallBoard", smallBoard);
		SmallBoardUpfile file = validator.selectFileByBoardNo(boardNo);
		if(file != null) {
			map.put("file", file);
		}
		
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	

}
