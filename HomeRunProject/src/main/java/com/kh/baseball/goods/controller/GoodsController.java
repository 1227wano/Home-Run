package com.kh.baseball.goods.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.common.ModelAndViewUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GoodsController {

	private final ModelAndViewUtil mv;
	
	@GetMapping("goods")
	public ModelAndView selectGoods() {
		
		return mv.setViewNameAndData("goods/list", null);
	}
	
}
