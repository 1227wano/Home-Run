package com.kh.baseball.foodtruck.model.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.foodtruck.model.vo.FoodTruck;
import com.kh.baseball.member.model.vo.Member;


public interface FoodTruckService {

	
	Map<String, Object> selectFoodTruckList(int page);
	
	void insertFoodTruck(FoodTruck foodTruck, MultipartFile[] upfile);
	
	Map<String, Object> selectDomName();

	String foodTruckNameCheck(String foodTruckName);

	Map<String, Object> foodTruckSelectDom(int domNo);
	
	

	}
