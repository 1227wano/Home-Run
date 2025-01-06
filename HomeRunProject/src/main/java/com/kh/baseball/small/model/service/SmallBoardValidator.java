package com.kh.baseball.small.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.kh.baseball.exception.FailToFileUploadException;
import com.kh.baseball.exception.FileNotFoundException;
import com.kh.baseball.exception.InvalidParameterException;
import com.kh.baseball.small.model.dao.SmallBoardMapper;
import com.kh.baseball.small.model.vo.SmallBoard;
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
		
		return Pagination.getPageInfo(totalCount, page, 10, 5);
		
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
		
		convertNewLineToBr(boardTitle);
		convertNewLineToBr(boardContent);
		
		smallBoard.setBoardTitle(boardTitle);
		smallBoard.setBoardContent(boardContent);
	}
	
	public String escapeHtml(String value) {
		return value.replaceAll("<", "&lt;").replaceAll(">","&gt;");
	}
	
	public String convertNewLineToBr(String value) {
		return value.replaceAll("\n","<br>");
	}
	
	public SmallBoardUpfile handleFileUpload(MultipartFile upfile) {
		
		String fileName = upfile.getOriginalFilename();
		
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
		
		return smallBoard;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
