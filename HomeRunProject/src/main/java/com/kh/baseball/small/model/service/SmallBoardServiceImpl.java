package com.kh.baseball.small.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.common.PageInfo;
import com.kh.baseball.exception.BoardNotFoundException;
import com.kh.baseball.exception.FailToBanParticipant;
import com.kh.baseball.exception.FailToBoardInsertException;
import com.kh.baseball.exception.FailToFileUploadException;
import com.kh.baseball.exception.FailToReplyInsertException;
import com.kh.baseball.exception.NeedToLoginException;
import com.kh.baseball.exception.NoReadyInsertBoardException;
import com.kh.baseball.exception.ParticipantNotAllowException;
import com.kh.baseball.member.model.vo.Member;
import com.kh.baseball.small.model.dao.SmallBoardMapper;
import com.kh.baseball.small.model.vo.SmallBoard;
import com.kh.baseball.small.model.vo.SmallBoardList;
import com.kh.baseball.small.model.vo.SmallBoardReply;
import com.kh.baseball.small.model.vo.SmallBoardUpfile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor

public class SmallBoardServiceImpl implements SmallBoardService {
	private final SmallBoardMapper mapper;
	private final SmallBoardValidator validator;
	private final ServletContext context;
	
	@Override
	public Map<String, Object> selectBoardList(int Page, int boardLimit) {
		
		int totalCount = validator.getTotalCount();
		
		PageInfo pi = validator.getPageInfo(totalCount, Page, boardLimit);
		
		List<SmallBoard> boards = validator.getBoardList(pi);
		
		Map<String, Object> map = new HashMap();
		map.put("smallBoard", boards);
		map.put("pageInfo", pi);
		map.put("boardLimit", boardLimit);
		return map;
	}

	@Override
	public List<String> selectTeamList() {
		
		List<String> teams = mapper.getTeamList();
		if(teams.isEmpty()) {
			throw new NoReadyInsertBoardException("팀이 조회되지 않습니다.");
		}
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
		SmallBoard validateAdminDetail = validator.convertOriginLine(adminDetail);
		
		Map<String, Object> map = new HashMap();
		map.put("adminDetail", validateAdminDetail);
		map.put("file", upfile);
		
		return map;
	}

	@Override
	public void adminPermit(Long boardNo) {

		int num = mapper.adminPermit(boardNo);
		
		if(num <= 0) {
			throw new BoardNotFoundException("게시글을 허가하지 못했습니다.");
		}
		SmallBoard smallBoard = mapper.selectBoardByBoardNo(boardNo);
		mapper.insertWriterAllow(smallBoard);
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
	public Map<String, Object> selectDetailByBoardNo(Long boardNo, HttpSession session) {

		SmallBoard smallBoard = validator.selectBoardByBoardNo(boardNo);
		Member member = (Member)session.getAttribute("loginUser");
		if(member == null) {
			throw new NeedToLoginException("로그인 안하고 게시물 열람시도");
		}
		int loginUserNo = member.getUserNo();
		smallBoard.setLoginUserNo(loginUserNo);
		
		validator.checkWriterPermission(smallBoard);
		
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

	@Override
	public void update(SmallBoard smallBoard, MultipartFile upfile, SmallBoardUpfile file) {

		validator.validateBoard(smallBoard);
		validator.selectBoardByBoardNo(smallBoard.getBoardNo());
		
		int boardResult = mapper.updateBoard(smallBoard);
		validator.validateUpdateBoard(boardResult);
		
		if(!!!("").equals(upfile.getOriginalFilename())) {
			if(file.getChangeName() != null) {
				new File(context.getRealPath(file.getChangeName())).delete();
			}
			SmallBoardUpfile smallBoardUpfile = validator.handleFileUpload(upfile);
			smallBoardUpfile.setRefBno(smallBoard.getBoardNo());
			int result = mapper.updateBoardUpfile(smallBoardUpfile);
			if(result < 1) {
				throw new FailToFileUploadException("파일업로드를 실패했습니다.");
			}
		}
	}

	@Override
	public Map<String, Object> selectParticipantList(Long boardNo, int Page, int boardLimit) {

		int totalCount = validator.getParticipantListCount(boardNo);
		
		PageInfo pi = validator.getPageInfo(totalCount, Page);
		
		List<SmallBoardList> lists = validator.getParticipantList(pi, boardNo);
		Map<String, Object> map = new HashMap();
		map.put("participantList", lists);
		map.put("pageInfo", pi);
		return map;
	}

	@Override
	public void writerPermission(int listNo) {

		int allowResult = mapper.writerAllow(listNo);
		if(allowResult < 1) {
			throw new ParticipantNotAllowException("참가자를 수락이 불가합니다..");
		}
	}

	@Override
	public void updateBanReason(SmallBoardList smallBoardList) {
		
		validator.validateListNo(smallBoardList.getListNo());
		
		SmallBoardList updateSmallBoardList = validator.validateBanReason(smallBoardList);
		
		int updateResult = mapper.updateBanReason(updateSmallBoardList);
		
		if(updateResult < 1) {
			throw new FailToBanParticipant("참여자를 강퇴하지 못했습니다.");
		}
	}
	
	public SmallBoardList validateParticipateForm(Long boardNo, Member member) {
		if(member == null) {
			throw new NeedToLoginException("로그인해주셔야 합니다.");
		}
		
		SmallBoardList smallBoardList = new SmallBoardList();
		smallBoardList.setRefBno(boardNo);
		smallBoardList.setLoginUserNo(member.getUserNo());
		
		validator.validateParticipateForm(smallBoardList);
		return smallBoardList;
	}
	
	public void insertParticipate(SmallBoardList smallBoardList) {
		validator.validateParticipateForm(smallBoardList);
		
		SmallBoardList updateSmallBoardList = validator.validateParticipationContent(smallBoardList);
		
		int insertResult = mapper.insertSmallBoardList(updateSmallBoardList);
		
		if(insertResult < 1) {
			throw new FailToBoardInsertException("참가신청을 할 수 없습니다.");
		}
		
	}

	@Override
	public Map<String, Object> searchList(Map<String, Object> map) {

		int result = mapper.searchListCount(map);

		PageInfo pi = validator.getPageInfo(result, (int)map.get("page"), (int)map.get("boardLimit"));
		RowBounds rowBounds = validator.getRowBounds(pi);
		
		List<SmallBoard> boards = mapper.searchList(map, rowBounds);
		
		map.put("smallBoard", boards);
		map.put("pageInfo", pi);
		
		return map;
	}

	@Override
	public int insertReply(SmallBoardReply reply) {

		int result = mapper.insertReply(reply);
		if(result < 1) {
			throw new FailToReplyInsertException("댓글작성을 실패했습니다.");
		}
		
		return result;
	}
	
	@Override
	public List<SmallBoardReply> selectReply(Long boardNo){
		
		return mapper.selectReply(boardNo);
		
	}

	@Override
	public int deleteChat(int replyNo, HttpSession session) {
		
		SmallBoardReply reply = mapper.selectReplyById(replyNo);
		Member member = (Member)session.getAttribute("loginUser");
		int result = 0;
		
		if((member!=null && reply.getReplyNickName().equals(member.getNickName())) || (member != null && "admin".equals(member.getUserId()))) {
			result = mapper.deleteChat(replyNo);
		}
		
		return result;
	}

}
