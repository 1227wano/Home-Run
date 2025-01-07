package com.kh.baseball.foodtruck.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FoodTruck {
	
	
	private int foodTruckNo;
	private String foodTruckUser;
	private int domNo;
	private String domName;
	private String foodTruckName;
	private String foodTruckStartTime;
	private String foodTruckEndTime;
	private String foodTruckDay;
	private String foodTruckContent;
	private String enrollDate;
	private String modifyDate;
	private int foodTruckCount;
	private String status;

}
