package com.kh.baseball.notice.model.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {

	int selectTotalCount();
}
