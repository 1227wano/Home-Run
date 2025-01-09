package com.kh.baseball.question.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {

	private Long questionNo;
	private String questionTitle;
	private String questionContent;
	private String questionWriter;
	private Date creationDate;
	private String status;
}
