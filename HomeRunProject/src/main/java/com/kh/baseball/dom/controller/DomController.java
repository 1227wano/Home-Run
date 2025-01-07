package com.kh.baseball.dom.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.common.ModelAndViewUtil;
import com.kh.baseball.dom.model.service.DomService;
import com.kh.baseball.dom.model.vo.Dom;
import com.kh.baseball.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DomController {

	private final DomService domService;
	private final ModelAndViewUtil mv;
	
	@GetMapping("dom")
	public ModelAndView selectDomList(@RequestParam(value="page", defaultValue="1") int page) {
		
		Map<String, Object> map = domService.selectDomList(page);
		return mv.setViewNameAndData("dom/list", map);
	}
	
	@GetMapping("enrollForm")
	public String saveForm() {
		return "dom/enroll_form";
	}
	
	@PostMapping("dom")
	public ModelAndView insertDom(Dom dom, MultipartFile upfile, HttpSession session) {
		
		Member loginMember = (Member)session.getAttribute("loginUser");
		domService.insertDom(dom, upfile, loginMember);
		return mv.setViewNameAndData("redirect:/dom", null);
	}
	
	@GetMapping("dom/{id}")
	public ModelAndView detailForm(@PathVariable(name="id") Long id) {
		
		Map<String, Object> map = domService.selectId(id);
		return mv.setViewNameAndData("dom/detail_form", map);
	}
	
	@PostMapping("dom/updateForm")
	public ModelAndView updateForm(Long domNo) {
		
		Map<String, Object> map = domService.selectId(domNo);
		return mv.setViewNameAndData("dom/update_form", map);
	}
	
	@PostMapping("dom/update")
	public ModelAndView updateDom(Dom dom, MultipartFile upfile, HttpSession session) {
		
		Member loginMember = (Member)session.getAttribute("loginUser");
		domService.updateDom(dom, upfile, loginMember);
		return mv.setViewNameAndData("redirect:/dom/" + dom.getDomNo(), null);
	}
	
	@PostMapping("dom/delete.dom")
	public ModelAndView delete(Dom dom, MultipartFile upfile, HttpSession session) {
		
		Member loginMember = (Member)session.getAttribute("loginUser");
		domService.deleteDom(dom, upfile, loginMember);
		return mv.setViewNameAndData("redirect:/dom", null);
	}
}
