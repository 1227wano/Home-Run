package com.kh.baseball.notice.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.baseball.notice.model.vo.Notice;

@Mapper
public interface NoticeMapper {

	int selectTotalCount();
	
	List<Notice> selectNoticeList(RowBounds rowBounds);
}
