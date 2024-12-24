package com.kh.baseball.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ResponseData {
	
	private String status; // 응답 코드
	private String message; // 응답 메세지
	private Object data; // 응답 데이터 (모든 데이터를 담을 수 있는 타입 Object)
	
}
