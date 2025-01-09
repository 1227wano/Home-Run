package com.kh.baseball.foodtruck.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodTruckFile {
	
	private int fileNo;
	private int refFtno;
	private String originName;
	private String changeName;
	private Date uploadDate;
	private int fileType;
	private String filePath;
	
	

}
