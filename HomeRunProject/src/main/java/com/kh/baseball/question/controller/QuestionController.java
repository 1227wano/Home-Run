package com.kh.baseball.question.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.common.ModelAndViewUtil;
import com.kh.baseball.question.model.service.QuestionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

	private final QuestionService questionService;
	private final ModelAndViewUtil mv;
	
	@GetMapping("question")
	public ModelAndView selectQuestionList(@RequestParam(value="page", defaultValue="1") int page) {
		
		Map<String, Object> map = questionService.selectQuestionList(page);
		
		return mv.setViewNameAndData(null, map);
	}
	
	
}
