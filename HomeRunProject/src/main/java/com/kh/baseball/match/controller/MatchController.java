package com.kh.baseball.match.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.common.ModelAndViewUtil;
import com.kh.baseball.match.model.service.MatchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MatchController {
	
	private final MatchService matchService;
	private final ModelAndViewUtil mv;
	
	@GetMapping("match")
	public ModelAndView selectMatchList() {
		
		Map<String, Object> map = new HashMap<>();
		map.put("matchList", matchService.selectMatchList());
		return mv.setViewNameAndData("match/list", map);
	}
	
	@PostMapping("match")
	public ModelAndView insertMatch() {
		
		
		return mv.setViewNameAndData(null, null);
	}
	

}
