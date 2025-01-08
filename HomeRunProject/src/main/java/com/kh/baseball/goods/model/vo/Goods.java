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
	
	private Long userNo;
	private String userName;
    private String nickname;
    private String phone;
	
	private Long goodsNo;
	private String goodsName;
	private Long goodsPrice;
	private int goodsCount;
	private String startDate;
	private String endDate;
	private String description;
	private String status;
	
	private String originName;
	private String changeName;
	private String filePath;
	
}
