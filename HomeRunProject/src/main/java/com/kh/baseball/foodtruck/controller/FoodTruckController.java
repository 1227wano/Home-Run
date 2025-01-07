package com.kh.baseball.foodtruck.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.common.ModelAndViewUtil;
import com.kh.baseball.foodtruck.model.service.FoodTruckService;

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
	public String insertForm() {
			
			return "foodtruck/foodTruck_insert_form";
		}
	
	
	
	
	

}
