package com.kh.baseball.freeBoard.model.vo;

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
public class FreeBoard {

	private Long boardNo;
	private int boardWriter;
	private String nickName;
	private String boardTitle;
	private String boardContent;
	private String createDate;
	private int selectCount;
	private String status;
}
