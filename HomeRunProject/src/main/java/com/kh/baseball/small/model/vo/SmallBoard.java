package com.kh.baseball.small.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SmallBoard {

	private Long boardNo;
	private String teamName;
	private int boardWriter;
	private String boardTitle;
	private String createDate;
	private String boardContent;
	private int selectCount;
	private String status;
	private String adminStatus;
	private String nickName;
	private String writerPermission;
	private int loginUserNo;
}
