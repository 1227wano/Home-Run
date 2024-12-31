package com.kh.baseball.notice.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.baseball.notice.model.vo.Notice;

@Mapper
public interface NoticeMapper {

	List<Notice> selectAllNotices();
	
	Notice selectNoticeById(int noticeNo);
	
	void addNotice(Notice notice);
	
	void updateNotice(Notice notice);
	
	void deleteNotice(int noticeNo);
	
	Notice getNoticeById(int noticeNo);
}
