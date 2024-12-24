package com.kh.baseball.player.model.vo;

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
public class Player {
	
	private int userNo;
	private int grade;
	private int playerPosition;
	private Date playerDate;
	private String playerIntro;
	private int playerSalary;
	private int playerNo;
	private String playerStatus;
	private String playerTeam;
	
	
	/* 
	 * 입력받을시
	 * userNo - 시퀀스
	 * grade - (1=프로, 2=아마추어, 3=일반)  <input type="radio" name="grade" value="3.일반" checked>
	 * playerPosition - (1=감독, 2=투수, 3=포수, 4=내야수, 5=외야수)
	 * <select name="position"><option>감독</option><option value="투수">투수</option></select>
	 * playerDate - sysdate
	 * playerIntro - <input type="text" placeholder="1000자 이내로 시합성적 및 자신소개글을 입력하세요." required> 
	 * playerSalary - 희망연봉 -> <input type="number">
	 * playerNo - 희망 등번호 -> 아이디중복처럼 겹치면 다른 번호를 입력하라고 표시?
	 * playerStatus - default 'N' (사용자에겐 안보임)
	 * playerTeam - 선수팀 -> 선수등록신청시는 당연히 없으므로 default '없음' (사용자에겐 안보임)
	 */
	
	 
	
	
	
	
	
}
