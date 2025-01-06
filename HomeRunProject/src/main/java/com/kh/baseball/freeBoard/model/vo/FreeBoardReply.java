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
public class FreeBoardReply {

	private int replyNo;
	private int replyWriter;
	private Long refBno;
	private String replyContent;
	private String createDate;
	private String status;
	private String replyNickName;
	
}
