package com.kh.baseball.small.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.baseball.common.model.vo.ResponseData;
import com.kh.baseball.small.model.service.SmallBoardService;
import com.kh.baseball.small.model.vo.SmallBoardReply;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("reply.small")
@RequiredArgsConstructor
public class SmallBoardApiController {

	private final SmallBoardService smallBoardService;
	
	@PostMapping
	public ResponseEntity<ResponseData> ajaxInsertReply(SmallBoardReply reply){
		int result = smallBoardService.insertReply(reply);
		
		if(result < 1) {
			ResponseData response = ResponseData.builder().message("댓글등록 실패")
					.status(HttpStatus.FORBIDDEN.toString())
					.data(result)
					.build();
			return new ResponseEntity<ResponseData>(response, HttpStatus.FORBIDDEN);
		} else {
			ResponseData response = ResponseData.builder().message("댓글등록 성공")
					.status(HttpStatus.OK.toString())
					.data(result)
					.build();
			return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
		}
	}
	
	@GetMapping
	public ResponseEntity<ResponseData> ajaxSelectReply(Long boardNo){
		List<SmallBoardReply> list = smallBoardService.selectReply(boardNo);
		
		ResponseData response = ResponseData.builder().message("댓글 조회 성공")
													  .status(HttpStatus.OK.toString())
													  .data(list)
													  .build();
		return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("deleteChat")
	public ResponseEntity<ResponseData> ajaxDeleteChat(int replyNo, HttpSession session) {
		
		int result = smallBoardService.deleteChat(replyNo, session);
		
		if(result < 1) {
			ResponseData response = ResponseData.builder().message("댓글삭제 실패")
					  .status(HttpStatus.FORBIDDEN.toString())
					  .data(result)
					  .build();
			
			return new ResponseEntity<ResponseData>(response, HttpStatus.FORBIDDEN);
		} else {
			ResponseData response = ResponseData.builder().message("댓글삭제 성공")
					  .status(HttpStatus.OK.toString())
					  .data(result)
					  .build();
			
			return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
		}
	}
}
