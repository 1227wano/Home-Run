package com.kh.baseball.small.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.common.PageInfo;
import com.kh.baseball.common.Pagination;
import com.kh.baseball.exception.BoardNoValueException;
import com.kh.baseball.exception.BoardNotFoundException;
import com.kh.baseball.exception.ExistParticipateListException;
import com.kh.baseball.exception.FailToBoardDetailException;
import com.kh.baseball.exception.FailToBoardUpdateException;
import com.kh.baseball.exception.FailToFileUploadException;
import com.kh.baseball.exception.FileNotFoundException;
import com.kh.baseball.exception.InvalidParameterException;
import com.kh.baseball.exception.NotFoundExtensionException;
import com.kh.baseball.exception.NotFoundListNoException;
import com.kh.baseball.exception.SmallBoardListNotFoundException;
import com.kh.baseball.exception.TooLargeValueException;
import com.kh.baseball.small.model.dao.SmallBoardMapper;
import com.kh.baseball.small.model.vo.SmallBoard;
import com.kh.baseball.small.model.vo.SmallBoardList;
import com.kh.baseball.small.model.vo.SmallBoardUpfile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class SmallBoardValidator {

	private final SmallBoardMapper mapper;
	private final ServletContext context;
	
	public int getTotalCount() {
		
		return mapper.selectTotalCount();
	}
	
	public int getAdminTotalCount() {
		
		return mapper.selectAdminListCount();
	}
	
	public PageInfo getPageInfo(int totalCount, int page, int boardLimit) {
		
		return Pagination.getPageInfo(totalCount, page, boardLimit, 5);
		
	}
	
	public PageInfo getPageInfo(int totalCount, int page) {
		
		return Pagination.getPageInfo(totalCount, page, 5, 5);
		
	}
	
	public List<SmallBoard> getBoardList(PageInfo pi){
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return mapper.selectBoardList(rowBounds);
	}
	
	public List<SmallBoard> getadminBoardList(PageInfo pi){
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return mapper.selectAdminList(rowBounds);
	}
	
	public List<SmallBoard> getBoardList(PageInfo pi, int boardWriter ){
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return mapper.selectMyBoardList(rowBounds, boardWriter);
	}
	
	public void validateBoard(SmallBoard smallBoard) {
		
		if(smallBoard == null || smallBoard.getBoardTitle() == null || smallBoard.getBoardTitle().trim().isEmpty() ||
								 smallBoard.getBoardContent() == null || smallBoard.getBoardContent().trim().isEmpty() ||
								 smallBoard.getBoardWriter() == 0) {
			
			throw new BoardNoValueException("부적절한 입력값");
		}
		
		String boardTitle = escapeHtml(smallBoard.getBoardTitle());
		String boardContent = escapeHtml(smallBoard.getBoardContent());
		
		smallBoard.setBoardTitle(convertNewLineToBr(boardTitle));
		smallBoard.setBoardContent(convertNewLineToBr(boardContent));
		
		if(smallBoard.getBoardTitle().length() > 20) {
			
			throw new TooLargeValueException("20자 이내로 작성해주시면 감사하겠습니다.");
		}
		
		if(smallBoard.getBoardContent().length() > 300) {
			
			throw new TooLargeValueException("300자 이내로 작성해주시면 감사하겠습니다.");
		}
	}
	
	public SmallBoard convertOriginLine(SmallBoard smallBoard) {
		
		smallBoard.setBoardTitle(convertOriginLineToN(smallBoard.getBoardTitle()));
		smallBoard.setBoardContent(convertOriginLineToN(smallBoard.getBoardContent()));
		
		return smallBoard;
	}
	
	public String escapeHtml(String value) {
		
		return value.replaceAll("<", "&lt;").replaceAll(">","&gt;");
	}
	
	public String convertNewLineToBr(String value) {
		
		return value.replaceAll("\n","<br>");
	}
	
	public String convertOriginLineToN(String value) {
		
		return value.replaceAll("<br>", "\n");
	}
	
	public SmallBoardUpfile handleFileUpload(MultipartFile upfile) {
		
		String fileName = upfile.getOriginalFilename();
		if(fileName.lastIndexOf(".") == -1) {
			throw new NotFoundExtensionException("확장자가 없는 파일은 등록할 수 없습니다.");
		}
		String ext = fileName.substring(fileName.lastIndexOf("."));
		int randomNo = (int)(Math.random() * 90000) + 10000;
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date());
		String changeName = currentTime + randomNo + ext;
		
		String savePath = context.getRealPath("/resources/upload_files/");
		
		SmallBoardUpfile smallBoardUpfile = SmallBoardUpfile.builder().originName(fileName)
															.changeName(changeName)
															.filePath(savePath)
															.build();
		
		try {
			upfile.transferTo(new File(savePath + changeName));
		} catch(IllegalStateException | IOException e) {
			throw new FailToFileUploadException("파일 이상");
		}
		
		return smallBoardUpfile;
	}
		
	public SmallBoard selectBoardByBoardNo(Long boardNo) {
		
		SmallBoard smallBoard = mapper.selectBoardByBoardNo(boardNo);
		
		if(smallBoard == null) {
			throw new BoardNotFoundException("게시글을 찾을 수 없습니다.");
		}
		return convertOriginLine(smallBoard);
	}
	
	public void checkWriterPermission(SmallBoard smallBoard) {
		
		int result = mapper.selectPosssibleDetail(smallBoard);

		if(result < 1 && smallBoard.getLoginUserNo() != 1) {
			throw new FailToBoardDetailException("게시물 참가신청 후 조회하실 수 있습니다.");
		}
	}
	
	public void incrementViewCount(Long boardNo) {
		
		int result = mapper.increaseCount(boardNo);
		if(result < 0) {
			throw new BoardNotFoundException("게시글을 찾을 수 없습니다.");
		}
	}
	
	public void validateBoardNo(Long boardNo) {
		
		if(boardNo == null || boardNo <= 0) {
			throw new InvalidParameterException("게시물을 찾을 수 없습니다.");
		}
	}
	
	public SmallBoardUpfile selectFileByBoardNo(Long boardNo) {
		
		return mapper.selectUpfileByBoardNo(boardNo);
	}
	
	public void deleteFile(SmallBoardUpfile deleteFile) {
		
		try {
			new File(context.getRealPath(deleteFile.getChangeName())).delete();
		} catch(RuntimeException e) {
			throw new FileNotFoundException("파일을 삭제하지 못하였습니다.");
		}
	}
	
	public void validateUpdateBoard(int boardUpdateResult) {
		
		if(boardUpdateResult < 1) {
			throw new FailToBoardUpdateException("게시글을 수정하지 못하였습니다.");
		}
	}
	
	// --------- 나의 게시글 관리 ---------------------
	
	public int getParticipantListCount(Long boardNo) {
		
		return mapper.selectMyParticipantListCount(boardNo);
	}
	
	public SmallBoardList convertOriginLine(SmallBoardList smallBoardList) {
		
		smallBoardList.setBanReason(convertOriginLineToN(smallBoardList.getBanReason()));
		smallBoardList.setParticipationContent(convertOriginLineToN(smallBoardList.getParticipationContent()));
		
		return smallBoardList;
	}
	
	public List<SmallBoardList> getParticipantList(PageInfo pi, Long boardNo){
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		List<SmallBoardList> list = mapper.selectParticipantList(boardNo, rowBounds);
		if(list.isEmpty()) {
			
			throw new SmallBoardListNotFoundException("조회되는 내역이 없습니다.");
		}
		return list;
	}
	
	public void validateListNo(int listNo) {
		
		if(listNo < 1) {
			throw new NotFoundListNoException("리스트 번호를 찾을 수 없습니다.");
		}
		
		SmallBoardList smallBoardList = mapper.selectListByListNo(listNo);
		if(smallBoardList.getListNo() == 0) {
			throw new NotFoundListNoException("리스트 번호를 찾을 수 없습니다.");
		}
	}
	
	public SmallBoardList validateBanReason(SmallBoardList smallBoardList) {
		
		if(smallBoardList == null || smallBoardList.getBanReason() == null || smallBoardList.getBanReason().trim().isEmpty()) {
			throw new BoardNoValueException("부적절한 입력값");
		}
		
		String banReason = escapeHtml(smallBoardList.getBanReason());
		
		smallBoardList.setBanReason(convertNewLineToBr(banReason));
		
		if(smallBoardList.getBanReason().length() > 30) {
			throw new TooLargeValueException("30자 이내로 작성해주시면 감사하겠습니다.");
		}
		
		return smallBoardList;
	}
	
	public void validateParticipateForm(SmallBoardList smallBoardList) {
		
		if(smallBoardList.getRefBno() == 0) {
			throw new BoardNotFoundException("게시물을 찾을 수 없습니다.");
		}
		
		int result = mapper.validateParticipateForm(smallBoardList);
		if(result == 1) {
			throw new ExistParticipateListException("이미 해당 게시글 참가 요청이 있습니다.");
		}
	}
	
	public SmallBoardList validateParticipationContent(SmallBoardList smallBoardList) {
		
		if(smallBoardList == null || smallBoardList.getParticipationContent() == null || smallBoardList.getParticipationContent().trim().isEmpty() 
																					  || smallBoardList.getParticipantNo() == 0) {
		throw new BoardNoValueException("부적절한 입력값");
		}
		
		String boardContent = escapeHtml(smallBoardList.getParticipationContent());
		
		smallBoardList.setParticipationContent(convertNewLineToBr(boardContent));
		
		if(smallBoardList.getParticipationContent().length() > 30 ) {
			throw new TooLargeValueException("30자 이내로 작성해주시면 감사하겠습니다.");
		}
		
		return smallBoardList;
	}
	
	public RowBounds getRowBounds(PageInfo pi){
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return rowBounds;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
