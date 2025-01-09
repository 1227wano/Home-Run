package com.kh.baseball.match.model.vo;

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
public class Match {
	
	private Long gameNo;
	private Long refDno;
	private String matchDate;
	private String firstTeam;
	private String secondTeam;
	private String domName;
	private String domAddr;
	
}
