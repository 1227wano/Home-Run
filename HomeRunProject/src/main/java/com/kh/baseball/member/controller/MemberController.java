package com.kh.baseball.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	private final ModelAndView mn;
	
	
	
	
	@GetMapping("enrollform.me")
	public String enrollForm() {
	
		return "member/enroll_form";
	}
	
	
	

}
