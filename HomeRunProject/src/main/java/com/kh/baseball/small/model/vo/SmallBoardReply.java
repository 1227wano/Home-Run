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
public class SmallBoardReply {

	private int replyNo;
	private Long refNo;
	private int replyWriter;
	private String content;
	private String createDate;
	private String status;
}
