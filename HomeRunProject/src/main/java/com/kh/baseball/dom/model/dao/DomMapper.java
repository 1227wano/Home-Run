package com.kh.baseball.dom.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.baseball.dom.model.vo.Dom;
import com.kh.baseball.dom.model.vo.DomAttachment;

@Mapper
public interface DomMapper {
	
	// 현재 등록된 구장의 총 개수
	int selectTotalCount();
	// 등록된 구장 조회
	List<Dom> selectDomList(RowBounds rowBounds);
	List<DomAttachment> selectAttachmentList();
	// 구장 세부 조회
	Dom selectId(Long domNo);
	// 구장 등록
	int insertDom(Dom dom);
	// 구장 파일 등록
	int insertDomFile(DomAttachment domAtt);
	// 구장 정보 업데이트
	int updateDom(Dom dom);
	int updateDomFile(DomAttachment domAtt);
	

}
