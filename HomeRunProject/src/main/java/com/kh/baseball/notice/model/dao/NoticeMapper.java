package com.kh.baseball.notice.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.baseball.notice.model.vo.Notice;

@Mapper
public interface NoticeMapper {

	List<Notice> selectNoticeList(RowBounds rowBounds);
	
	int selectTotalCount();
	
	Notice selectNoticeById(int noticeNo);
	
	Notice getNoticeById(long noticeNo);
	
	void insertNotice(Notice notice); // 두개를 넘길 수 없음 하나만 넘길 수 있음
	
	void updateNotice(Notice notice);
	
	void deleteNotice(Long noticeNo);

	int getTotalNoticeCount();

	
}
