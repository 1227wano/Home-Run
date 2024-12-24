package com.kh.baseball.dom.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.baseball.dom.model.vo.Dom;

@Mapper
public interface DomMapper {
	
	// 현재 등록된 구장의 총 개수
	int findTotalCount();
	// 등록된 구장 조회
	List<Dom> findAll(RowBounds rowBounds);

	
	

}
