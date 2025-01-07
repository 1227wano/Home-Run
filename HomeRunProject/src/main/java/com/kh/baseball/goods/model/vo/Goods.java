package com.kh.baseball.goods.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Goods {
	
	private Long goodsNo;
	private String goodsName;
	private double goodsPrice;
	private int goodsCount;
	private String startDate;
	private String endDate;
	private String description;
	private Long userNo;
	private String status;
	
	private String originName;
	private String changeName;
	
}
