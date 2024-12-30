package com.kh.baseball.notice.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.baseball.notice.model.vo.Notice;

@Mapper
public interface NoticeMapper {

	List<Notice> selectAllNotices();
	
	void insertNotice(Notice notice);
	
	void updateNotice(Notice notice);
	
	void deleteNotice(int noticeId);
	
	Notice selectNoticeById(int noticeId);
}
