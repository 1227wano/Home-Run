package com.kh.baseball.team.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Team {
	
	private int teamNo;
	private String teamName;
	private int userNo; // 이 회원번호는 팀을 만든 유저의 번호
	private String teamGrade;
	private String teamIntro;
	private Date teamDate;
	private String status;
	private String originName;
	private String changeName;

}
