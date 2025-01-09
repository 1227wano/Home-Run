package com.kh.baseball.foodtruck.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.baseball.foodtruck.model.vo.FoodTruck;
import com.kh.baseball.foodtruck.model.vo.FoodTruckFile;
import com.kh.baseball.member.model.vo.Member;


@Mapper
public interface FoodTruckMapper {

	int selectTotalCount();

	List<FoodTruck> selectFoodTruckList(RowBounds rowBounds);

	List<FoodTruck> selectDom();

	void insertFoodTruck(FoodTruck foodTruck);

	void insertFoodTruckFile(FoodTruckFile foodTruckFile);

	int foodTruckNameCheck(String foodTruckName);

	List<FoodTruckFile> selectFoodTruckFile();

	List<FoodTruck> foodTruckSelectDom(int domNo);

	
	
	

}
