package com.kh.baseball.freeboard.model.vo;

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
public class FreeBoardFile {

	private int fileNo;
	private int fileType;
	private Long refBno;
	private String originName;
	private String changeName;
	private String uploadDate;
	private String filePath;
}
