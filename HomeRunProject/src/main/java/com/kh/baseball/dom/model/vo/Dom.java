package com.kh.baseball.dom.model.vo;

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
@ToString
@Builder
public class Dom {
	
	private Long domNo;
	private String domName;
	private String domContent;
	private String domAddr;
	private String status;
	
}
