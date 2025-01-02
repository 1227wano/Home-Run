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
public class SmallBoardUpfile {
	
	private int fileNo;
	private Long refBno;
	private String originName;
	private String changeName;
	private String uploadDate;
	private String filePath;
}
