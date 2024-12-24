package com.kh.baseball.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.common.ModelAndViewUtil;
import com.kh.baseball.member.model.service.MemberService;
import com.kh.baseball.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	private final ModelAndViewUtil mv;
	
	@PostMapping
	public ModelAndView login(Member member, HttpSession session) {
		
		Member loginMember = memberService.login(member);
		
		session.setAttribute("loginUser", loginMember);
		session.setAttribute("alertMsg","로그인 완료");
		return mv.setViewNameAndData("redirect:/", null);
		
		
	}
	
	

}
