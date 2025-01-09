package com.kh.baseball.notice.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.common.PageInfo;
import com.kh.baseball.common.Pagination;
import com.kh.baseball.exception.NoticeNotFoundException;
import com.kh.baseball.notice.model.dao.NoticeMapper;
import com.kh.baseball.notice.model.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService {
	
	private final NoticeMapper mapper;
	private final ServletContext context;
	
	public int getTotalCount() {
		int totalCount = mapper.selectTotalCount();
		
		if(totalCount == 0) {
			throw new NoticeNotFoundException("없어~~~");
		}
		return totalCount;
	}
	
	private PageInfo getPageInfo(int totalCount, int page) {
		return Pagination.getPageInfo(totalCount, page, 5, 5);
	}
	
	private List<Notice> getNoticeList(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return mapper.selectNoticeList(rowBounds);
	}
	
	@Override
	public Map<String, Object> selectNoticeList(int currentPage) {
		 
		int totalCount = getTotalCount();
		log.info("{}", totalCount);
		PageInfo pi = getPageInfo(totalCount, currentPage);
		
		List<Notice> notices = getNoticeList(pi);
		log.info("{}", notices);
		Map<String, Object> map = new HashMap();
		map.put("notices", notices);
		map.put("pageInfo", pi);
		
		return map;
	}
	
	@Override
	public void insertNotice(Notice notice, MultipartFile upfile) {

		if(!("".equals(upfile.getOriginalFilename()))) {
			String fileName = upfile.getOriginalFilename();
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			int randomNum = (int)(Math.random() * 9000) + 1000;
			String changeName = currentTime + randomNum + ext;
			
			String savePath = context.getRealPath("/resources/upload_files/");
			
				try {
					upfile.transferTo(new File(savePath + changeName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				notice.setAttachMent("/baseball/resources/upload_files/" + changeName);
			
		}
		
		mapper.insertNotice(notice);
	}
	
	@Override
	public Map<String, Object> selectNoticeById(Long noticeNo) {
		Notice notice = mapper.getNoticeById(noticeNo);
		Map<String, Object> result = new HashMap<>();
		result.put("notice", notice);
		return result;
	}
	
	@Override
	public void updateNotice(Notice notice, MultipartFile upfile) {
		 log.info("Updating notice: {}", notice);
	     mapper.updateNotice(notice);
	}

	@Override
	public void deleteNotice(Long noticeNo, String attachMent) {
		
		if(attachMent != null && !attachMent.isEmpty()) {
			File file = new File("파일 경로" + attachMent);
			if (file.exists()) {
				file.delete();
			}
		}
		mapper.deleteNotice(noticeNo);
	}


	@Override
	public Notice getNoticeById(Long noticeNo) {
		return mapper.getNoticeById(noticeNo);
	}

	

	

	

	

	

	

	

}

