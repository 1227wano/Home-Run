package com.kh.baseball.exception.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.exception.BoardNoValueException;
import com.kh.baseball.exception.BoardNotFoundException;
import com.kh.baseball.exception.ComparePasswordException;
import com.kh.baseball.exception.ExistParticipateListException;
import com.kh.baseball.exception.FailToBanParticipant;
import com.kh.baseball.exception.FailToBoardDetailException;
import com.kh.baseball.exception.FailToBoardInsertException;
import com.kh.baseball.exception.FailToBoardUpdateException;
import com.kh.baseball.exception.FailToFileUploadException;
import com.kh.baseball.exception.FailToReplyDeleteException;
import com.kh.baseball.exception.FailToReplyInsertException;
import com.kh.baseball.exception.FileNotFoundException;
import com.kh.baseball.exception.FoodTruckNoValueException;
import com.kh.baseball.exception.FoodTruckNotFoundException;
import com.kh.baseball.exception.IdNotFoundException;
import com.kh.baseball.exception.InvalidParameterException;
import com.kh.baseball.exception.NeedToLoginException;
import com.kh.baseball.exception.NoReadyInsertBoardException;
import com.kh.baseball.exception.NotFoundException;
import com.kh.baseball.exception.NotFoundExtensionException;
import com.kh.baseball.exception.NotFoundListNoException;
import com.kh.baseball.exception.NoticeNotFoundException;
import com.kh.baseball.exception.ParticipantNotAllowException;
import com.kh.baseball.exception.PlayerNotFoundException;
import com.kh.baseball.exception.RequestFailedException;
import com.kh.baseball.exception.SmallBoardListNotFoundException;
import com.kh.baseball.exception.TooLargeValueException;
import com.kh.baseball.exception.TooSmallValueException;
import com.kh.baseball.exception.UserFoundException;
import com.kh.baseball.exception.UserIdNotFoundException;
import com.kh.baseball.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {
	
	private ModelAndView createErrorResponse(String errorMsg, Exception e) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", errorMsg).setViewName("common/error_page");
		log.info("발생 예외 : {}", e.getMessage(), e);
		// 글로벌 핸들러 익셉션
		return mv;
	}
	
	private ModelAndView createAlertResponse(String alertMsg, Exception e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("alertMsg", alertMsg).setViewName("home");
		log.info("발생 예외 : {}", e.getMessage(), e);
		return mv;
	}
	
	//-----Member Exception
	@ExceptionHandler(UserIdNotFoundException.class)
	protected ModelAndView NoSuchUserIdError(UserIdNotFoundException e) {
		return createErrorResponse("로그인 실패", e);
	}//--로그인 오류(존재하지 않는 사용자)
	
	@ExceptionHandler(ComparePasswordException.class)
	protected ModelAndView NotMatchingPasswordError(ComparePasswordException e) { 
		return createErrorResponse("비밀번호가 일치하지 않습니다.", e);
	}
	
	@ExceptionHandler(UserFoundException.class)
	protected ModelAndView userDuplicateError(UserFoundException e) {
		return createErrorResponse("회원이 존재합니다.", e);
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
	
	//-----FreeBoard Exception
	@ExceptionHandler(BoardNotFoundException.class)
	protected ModelAndView boardNotFoundError(BoardNotFoundException e) {
		return createErrorResponse("자유게시판에는 그런 글이 존재하지 않습니다.", e);
	}
	
	@ExceptionHandler(BoardNoValueException.class)
	protected ModelAndView boardNoValueError(BoardNoValueException e) {
		return createErrorResponse("게시판의 글을 입력하기엔 부족한 정보입니다.", e);
	}
	
	@ExceptionHandler(FailToFileUploadException.class)
	protected ModelAndView failToFileUploadError(FailToFileUploadException e) {
		return createErrorResponse("파일이 업로드 되지 않습니다.", e);
	}
	
	@ExceptionHandler(InvalidParameterException.class)
	protected ModelAndView invalidParameterException(InvalidParameterException e) {
		return createErrorResponse("잘못된 경로로 접근하였습니다.", e);
	}
	
	@ExceptionHandler(FileNotFoundException.class)
	protected ModelAndView fileNotFoundException(FileNotFoundException e) {
		return createErrorResponse("파일을 찾을 수 없습니다.", e);
	}
	
	@ExceptionHandler(FailToReplyInsertException.class)
	protected ModelAndView failToReplyInsertException(FailToReplyInsertException e) {
		return createErrorResponse("댓글 작성을 실패했습니다.", e);
	}
	
	@ExceptionHandler(FailToReplyDeleteException.class)
	protected ModelAndView failToReplyDeleteException(FailToReplyDeleteException e) {
		return createErrorResponse("댓글 삭제에 실패했습니다.", e);
	}
	
	@ExceptionHandler(TooLargeValueException.class)
	protected ModelAndView valueLengthOverErr(TooLargeValueException e) {
		return createErrorResponse("유효하지 않은 길이의 값을 입력하였습니다.", e);
	}
	
	@ExceptionHandler(RequestFailedException.class)
	protected ModelAndView requestFailErr(RequestFailedException e) {
		return createErrorResponse("요청 처리에 실패했습니다.", e);
	}
	
	@ExceptionHandler(NotFoundException.class)
	protected ModelAndView noSuchDataErr(NotFoundException e) {
		return createAlertResponse("요청에 필요한 데이터를 찾지 못했습니다.", e);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	protected ModelAndView noLoginUser(UserNotFoundException e) {
		return createAlertResponse("로그인 후 이용 가능합니다.", e);
	}
	
	//--- FoodTruck Exception
	@ExceptionHandler(FoodTruckNotFoundException.class)
	protected ModelAndView FoodTruckNotFoundError(FoodTruckNotFoundException e) {
		return createErrorResponse("푸드트럭 게시물이 존재하지 않습니다.", e);
	}
	
	@ExceptionHandler(FoodTruckNoValueException.class)
	protected ModelAndView foodTruckNoValueError(FoodTruckNoValueException e) {
		return createErrorResponse("푸드트럭 게시물에 잘못된 입력입니다.", e);
	}
	
	//--- Member
	
	@ExceptionHandler(TooSmallValueException.class)
	protected ModelAndView tooSmallValueError(TooSmallValueException e) {
		return createErrorResponse("비밀번호 조건에 맞지 않습니다.", e);
	}
	
	@ExceptionHandler(FailToBoardUpdateException.class)
	protected ModelAndView failToBoardUpdateException(FailToBoardUpdateException e) {
		return createErrorResponse("게시물을 수정하지 못했습니다.", e);
	}
	
	//-----SmallBoard Exception
	
	@ExceptionHandler(ParticipantNotAllowException.class)
	protected ModelAndView participantNotAllowException(ParticipantNotAllowException e) {
		return createErrorResponse("리스트 허가를 실패했습니다.", e);
	}
	
	@ExceptionHandler(NotFoundListNoException.class)
	protected ModelAndView notFoundListNoException(NotFoundListNoException e) {
		return createErrorResponse("리스트 번호를 조회하지 못했습니다.", e);
	}
	
	@ExceptionHandler(FailToBanParticipant.class)
	protected ModelAndView failToBanParticipant(FailToBanParticipant e) {
		return createErrorResponse("참여자를 강퇴하는데 실패했습니다.", e);
	}
	
	@ExceptionHandler(FailToBoardDetailException.class)
	protected ModelAndView failToBoardDetailException(FailToBoardDetailException e) {
		return createErrorResponse("해당게시물에 참여권한이 없습니다.", e);
	}
	
	@ExceptionHandler(NeedToLoginException.class)
	protected ModelAndView needToLoginException(NeedToLoginException e) {
		return createErrorResponse("로그인하셔야 사용이 가능합니다.", e);
	}
	
	@ExceptionHandler(ExistParticipateListException.class)
	protected ModelAndView existParticipateListException(ExistParticipateListException e) {
		return createErrorResponse("이미 해당 게시글 참가 요청이 있습니다.", e);
	}
	
	@ExceptionHandler(FailToBoardInsertException.class)
	protected ModelAndView failToBoardInsertException(FailToBoardInsertException e) {
		return createErrorResponse("주어진 정보가 부족해 게시물을 입력할 수 없습니다.", e);
	}
	
	@ExceptionHandler(SmallBoardListNotFoundException.class)
	protected ModelAndView smallBoardListNotFoundException(SmallBoardListNotFoundException e) {
		return createErrorResponse("해당 게시글에서 참여자리스트가 존재하지 않습니다.", e);
	}
	
	@ExceptionHandler(NoReadyInsertBoardException.class)
	protected ModelAndView noReadyInsertBoardException(NoReadyInsertBoardException e) {
		return createErrorResponse("현재 게시글을 작성할 수 없습니다.", e);
	}
	
	@ExceptionHandler(NotFoundExtensionException.class)
	protected ModelAndView noSuchExtendsionError(NotFoundExtensionException e) {
		return createErrorResponse("확장자가 없어 파일등록에 실패하였습니다.", e);
	}
	
}

