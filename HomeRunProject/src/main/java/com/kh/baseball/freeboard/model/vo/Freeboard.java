package com.kh.baseball.freeboard.model.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class Freeboard {

	private Long boardNo;
	private Long boardWriter;
	private String boardTitle;
	private String boardContent;
	private Date createDate;
	private Long selectCount;
	private String status;
	
}
