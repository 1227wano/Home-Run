package com.kh.baseball.member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@PostMapping("login.me")
	public ModelAndView login(Member member, HttpSession session) {
		
		Member loginMember = memberService.login(member);
		
		//log.info("{}" , member);
		//log.info("{}" , loginMember);
		
		//return null;
		session.setAttribute("loginUser", loginMember);
		session.setAttribute("alertMsg","로그인 완료");
		return mv.setViewNameAndData("redirect:/", null);
		
		
	}
	
	
	@GetMapping("logout.me")
	public String logout(HttpSession session) {
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	
	@GetMapping("enrollform.me")
	public String enrollFrom(Member member) {
		return "member/enroll_form";
	}
	
	@PostMapping("sign_up.me")
	public ModelAndView signUp(Member member, HttpSession session) {
		memberService.join(member);
		session.setAttribute("alert", "회원가입 완료");
		return mv.setViewNameAndData("redirect:/", null);
	}
	
	@ResponseBody
	@GetMapping("idcheck")
	public String checkId(String userId) {
		return memberService.checkId(userId);
	}
	
	@ResponseBody
	@GetMapping("nicknamecheck")
	public String checkNickName(String nickName) {
		return memberService.checkNickName(nickName);
	}
	
	@GetMapping("searchId")
	public String searchId(Member member) {
		return "member/searchId";
	}
	
	@PostMapping("findById.me")
	public ModelAndView findById(Member member) {
		log.info("{}" , member);
		Map<String, Object> successId = memberService.searchId(member);
		
		return mv.setViewNameAndData("member/searchIdSuccess", successId);
	}
	
	
	@GetMapping("mypage.me")
	public String mypage() {
		
		return "member/my_page";
	}
	
	@PostMapping("update.me")
	public ModelAndView updateMember(Member member, HttpSession session) {
		
		log.info("{}", member);
		
		// session.setAttribute("loginUser", memberService.login(member));
		memberService.updateMember(member,session);
		session.setAttribute("alert", "정보 수정 완료");
		return mv.setViewNameAndData("redirect:mypage.me", null);
		
	}

}
