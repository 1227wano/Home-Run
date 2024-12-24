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
public class DomAttachment {
	
	private Long domAttachmentNo;
	private Long refDno;
	private String originName;
	private String changeName;
	private String filePath;
	private String createDate;
	private String status;
}
