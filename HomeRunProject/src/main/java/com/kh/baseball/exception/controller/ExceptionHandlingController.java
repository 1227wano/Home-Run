package com.kh.baseball.exception.controller;

<<<<<<< HEAD
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.exception.UserIdNotFoundException;

=======
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

>>>>>>> 5e96254919d7136ec1774e5eee6d2c0c620be3d1
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {
	
	private ModelAndView createErrorResponse(String errorMsg, Exception e) {
<<<<<<< HEAD
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

=======
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", errorMsg).setViewName("common/error_page");
		log.info("발생 예외 : {} , {}", e.getMessage(), e);
		
		return mv;
	}
	
	
	
	
>>>>>>> 5e96254919d7136ec1774e5eee6d2c0c620be3d1
}
