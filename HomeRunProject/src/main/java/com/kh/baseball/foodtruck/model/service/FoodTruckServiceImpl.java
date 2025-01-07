package com.kh.baseball.foodtruck.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.baseball.common.PageInfo;
import com.kh.baseball.foodtruck.model.dao.FoodTruckMapper;
import com.kh.baseball.foodtruck.model.vo.FoodTruck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor

public class FoodTruckServiceImpl implements FoodTruckService {

	private final FoodTruckMapper mapper;
	private final FoodTruckValidator validator;

	@Override
	public Map<String, Object> selectFoodTruckList(int currentPage) {
		
		int totalCount = validator.getTotalCount();
		
		log.info("게시글 개수 : {}" , totalCount);
		
		PageInfo pi = validator.getPageInfo(totalCount, currentPage);
		
		List<FoodTruck> foodTruck = validator.getFoodTruckList(pi);
		
		List<FoodTruck> domName = mapper.selectDom();
		
		log.info("{}",domName);
		
		Map<String, Object> map = new HashMap();
		map.put("domName", domName);
		map.put("foodTruck", foodTruck);
		map.put("pageInfo", pi);
		
		return map;
	}

	@Override
	public void insertFoodTruck(FoodTruck foodTruck) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FoodTruck selectById(int foodTruckNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
