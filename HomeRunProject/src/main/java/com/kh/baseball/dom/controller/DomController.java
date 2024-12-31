package com.kh.baseball.dom.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.common.ModelAndViewUtil;
import com.kh.baseball.dom.model.service.DomService;
import com.kh.baseball.dom.model.vo.Dom;

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
		log.info("구장 리스트 정보 : {}, {}", map, map.get("domList"));
		return mv.setViewNameAndData("dom/list", map);
	}
	
	
	@GetMapping("saveForm")
	public String saveForm() {
		return "dom/enroll_form";
	}
	
	@PostMapping("dom")
	public ModelAndView save(Dom dom, MultipartFile upfile, HttpSession session) {
		
		log.info("구장 정보 : {}", dom);
		log.info("파일 정보 : {}", upfile);
		
		domService.insertDom(dom, upfile);
		return mv.setViewNameAndData("redirect:/dom", null);
	}
	
	@GetMapping("dom/{id}")
	public ModelAndView detailForm(@PathVariable(name="id") Long id) {
		
		log.info("넘어온 구장 식별 번호 : {}", id);
		
		Map<String, Object> map = domService.selectId(id);
		log.info("가져온 구장 정보 : {}", map.get("dom"));
		
		return mv.setViewNameAndData("dom/detail_form", map);
	}
	
	@PostMapping("dom/updateForm")
	public ModelAndView updateForm(Long domNo) {
		
		Map<String, Object> map = domService.selectId(domNo);
		
		return mv.setViewNameAndData("dom/update_form", map);
	}
	
	@PostMapping("dom/update")
	public ModelAndView updateDom(Dom dom, MultipartFile upfile) {
		
		log.info("수정할 구장 정보 : {}", dom);
		log.info("파일 정보 : {}", upfile);
		
		domService.updateDom(dom, upfile);
		
		return mv.setViewNameAndData("redirect:/dom/" + dom.getDomNo(), null);
	}
	
	
	@GetMapping("delete.dom")
	public ModelAndView delete(Dom dom, Long userId) {
		
		log.info("삭제할 구장 정보 : {}", dom);
		
		return mv.setViewNameAndData("redirect:/dom", null);
	}
	
	
}
