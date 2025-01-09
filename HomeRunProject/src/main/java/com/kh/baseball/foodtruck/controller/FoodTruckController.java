package com.kh.baseball.foodtruck.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.common.ModelAndViewUtil;
import com.kh.baseball.foodtruck.model.service.FoodTruckService;
import com.kh.baseball.foodtruck.model.vo.FoodTruck;
import com.kh.baseball.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FoodTruckController {
	
	private final FoodTruckService foodTruckService;
	private final ModelAndViewUtil mv;
	
	@GetMapping("foodTruckBoards")
	public ModelAndView selectBoardsList(@RequestParam(value="page", defaultValue="1")int page) {
		
		Map<String, Object> map = foodTruckService.selectFoodTruckList(page);
		
		return mv.setViewNameAndData("foodtruck/foodTruck_list",map);
	}
	
	@GetMapping("foodTruckInsertForm")
	public ModelAndView insertForm() {
		
		Map<String, Object> map = foodTruckService.selectDomName();
		
			return mv.setViewNameAndData("foodtruck/foodTruck_insert_form", map);
    }
	
	@PostMapping("foodTruck")
	public ModelAndView insertFoodTruck(FoodTruck foodTruck, HttpSession session, MultipartFile[] upfile) {
		
		//log.info("푸드트럭 정보 :{}, 파일정보 :{}" ,foodTruck, upfile[0]);
		//log.info("{}",upfile[0]);
		//log.info("{}",upfile[1]);
		//log.info("{}",upfile[2]);
	
		foodTruckService.insertFoodTruck(foodTruck, upfile);
		
		session.setAttribute("alertMsg", "푸드트럭 신청 완료");
		return mv.setViewNameAndData("redirect:foodTruckBoards", null);
	}
	
	@ResponseBody
	@GetMapping("foodTruckNameCheck")
	public String foodTruckNameCheck(String foodTruckName) {
		return foodTruckService.foodTruckNameCheck(foodTruckName);
	}
	
	@GetMapping("foodTruckSelectDom")
	public ModelAndView foodTruckSelectDom(int domNo) {
		
		Map<String, Object> map = foodTruckService.foodTruckSelectDom(domNo);
		
		return mv.setViewNameAndData("foodtruck/foodTruck_list",map);
	}
	

}
