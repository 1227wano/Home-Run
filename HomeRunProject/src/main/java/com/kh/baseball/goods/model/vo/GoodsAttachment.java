package com.kh.baseball.goods.model.vo;

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
public class GoodsAttachment {
	
	private Long goodsAttachmentNo;
	private String originName;
	private String changeName;
	private String filePath;
	private String uploadDate;
	private Long goodsNo;
	
}
