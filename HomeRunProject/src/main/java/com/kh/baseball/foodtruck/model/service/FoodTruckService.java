package com.kh.baseball.foodtruck.model.service;

import java.util.Map;

import com.kh.baseball.foodtruck.model.vo.FoodTruck;


public interface FoodTruckService {

	
	Map<String, Object> selectFoodTruckList(int page);
	
	void insertFoodTruck(FoodTruck foodTruck);
	
	FoodTruck selectById(int foodTruckNo);
	
	

	}
