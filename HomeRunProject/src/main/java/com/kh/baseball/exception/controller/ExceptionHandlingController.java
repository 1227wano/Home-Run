package com.kh.baseball.exception.controller;


import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.exception.ComparePasswordException;
import com.kh.baseball.exception.NoticeNotFoundException;
import com.kh.baseball.exception.PlayerNotFoundException;
import com.kh.baseball.exception.IdNotFoundException;
import com.kh.baseball.exception.UserIdNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {
	
	private ModelAndView createErrorResponse(String errorMsg, Exception e) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", errorMsg).setViewName("common/error_page");
		log.info("발생 예외 : {}", e.getMessage(), e);
		return mv;
	}
	
	//-----Member Exception
	@ExceptionHandler(UserIdNotFoundException.class)
	protected ModelAndView NoSuchUserIdError(DuplicateKeyException e) {
		return createErrorResponse("아이디가 존재하지 않습니다.", e);
	}
	
	@ExceptionHandler(ComparePasswordException.class)
	protected ModelAndView NotMatchingPasswordError(ComparePasswordException e) { 
		return createErrorResponse("비밀번호가 일치하지 않습니다.", e);
	}
	
	// ----Notice Exception
	@ExceptionHandler(NoticeNotFoundException.class)
	protected ModelAndView NoSuchNoticeError(NoticeNotFoundException e) {
		return createErrorResponse("게시글이 존재하지 않습니다.", e);
	}
	//-----Player Exception
	@ExceptionHandler(PlayerNotFoundException.class)
	protected ModelAndView NoSuchBoardError(PlayerNotFoundException e) {
		return createErrorResponse("선수가 존재하지 않습니다.", e);
	}

	@ExceptionHandler(IdNotFoundException.class)
	protected ModelAndView NoSuchIdError(IdNotFoundException e) {
		return createErrorResponse("아이디를 찾을 수 없습니다.", e);
	}
	
}
