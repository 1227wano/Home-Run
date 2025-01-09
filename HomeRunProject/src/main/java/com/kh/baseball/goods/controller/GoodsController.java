package com.kh.baseball.goods.controller;

import java.util.HashMap;
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
import com.kh.baseball.goods.model.service.GoodsService;
import com.kh.baseball.goods.model.vo.Goods;
import com.kh.baseball.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GoodsController {

	private final ModelAndViewUtil mv;
	private final GoodsService goodsService;
	
	@GetMapping("goods")
	public ModelAndView selectGoods(@RequestParam(value="page", defaultValue="1") int page) {
		
		Map<String, Object> responseData = goodsService.selectGoods(page);
		
		return mv.setViewNameAndData("goods/list", responseData);
	}
	
	@GetMapping("goods/{goodsNo}")
	public ModelAndView detailGoods(@PathVariable(name="goodsNo") Long goodsNo) {
		log.info("넘어온 굿즈 번호 : {}", goodsNo);
		
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("goods", goodsService.selectGoodsByNo(goodsNo)); 
		log.info("선택된 굿즈 정보 : {}", responseData.get("goods"));
		
		return mv.setViewNameAndData("goods/detail_form", responseData);
	}
	
	@GetMapping("enrollForm.goods")
	public String enrollForm() {
		return "goods/enroll_form";
	}
	
	@PostMapping("goods")
	public ModelAndView insertGoods(Goods goods, MultipartFile upfile, HttpSession session) {
//		log.info("굿즈 등록 정보 : {}", goods);
//		for(MultipartFile file : upfile) {
//			log.info("파일 정보 : {}", file.getOriginalFilename());
//		}
//		log.info("로그인 사용자 정보 : {}", session.getAttribute("loginUser"));
		Member loginMember = (Member)session.getAttribute("loginUser");
		goodsService.insertGoods(goods, upfile, loginMember);
		
		return mv.setViewNameAndData("redirect:/goods", null);
	}
}
