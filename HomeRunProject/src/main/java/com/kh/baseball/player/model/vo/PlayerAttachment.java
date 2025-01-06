package com.kh.baseball.player.model.vo;

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
public class PlayerAttachment {
	
	private int playerAttachmentNo;
	private String originName;
	private String changeName;
	private String filePath;
	private String uploadDate;
	private int playerNo;

}
