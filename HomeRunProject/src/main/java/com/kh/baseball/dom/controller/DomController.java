package com.kh.baseball.dom.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.common.ModelAndViewUtil;
import com.kh.baseball.dom.model.service.DomService;

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
		
		Map<String, Object> map = domService.findAll(page);
		log.info("구장 리스트 정보 : {}, {}", map, map.get("domList"));
		return mv.setViewNameAndData("dom/list", map);
	}
	
	
	
}
