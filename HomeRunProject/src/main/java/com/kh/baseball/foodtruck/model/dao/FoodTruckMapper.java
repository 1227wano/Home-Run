package com.kh.baseball.foodtruck.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.baseball.foodtruck.model.vo.FoodTruck;


@Mapper
public interface FoodTruckMapper {

	int selectTotalCount();

	List<FoodTruck> selectFoodTruckList(RowBounds rowBounds);

	List<FoodTruck> selectDom();
	
	

}
