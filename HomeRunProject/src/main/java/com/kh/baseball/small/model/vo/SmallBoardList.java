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
public class SmallBoardList {

	private int listNo;
	private Long refBno;
	private int participantNo;
	private String writerPermission;
	private String banReason;
	private String userEscape;
	private String participationContent;
	private String participationStatus;
	private String participationDate;
	private String nickName;
	private int loginUserNo;
}
