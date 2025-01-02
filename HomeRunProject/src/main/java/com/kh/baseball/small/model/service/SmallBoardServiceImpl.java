package com.kh.baseball.small.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.common.PageInfo;
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
		
		List<SmallBoard> boards = validator.getBoardList(pi);
		
		Map<String, Object> map = new HashMap();
		map.put("adminList", boards);
		map.put("pageInfo", pi);
		
		return map;
	}

}
