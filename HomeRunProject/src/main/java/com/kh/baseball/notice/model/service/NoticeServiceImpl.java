package com.kh.baseball.notice.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	
	@Override
	public Map<String, Object> selectNoticeList(int currentPage) {
		log.info("요청페이지 : {}", currentPage);
		
		Map<String, Object> result = new HashMap<String, Object>();
		int pageSize = 10;
		
		List<Notice> notices = mapper.selectAllNotices();
		result.put("notices", notices);
		result.put("currentPage", currentPage);
		result.put("pageSize", pageSize);
		
		log.info("Fatched {} notices for page : {}", notices.size(), currentPage);
		return result;
	}

	@Override
	@Transactional
	public void addNotice(Notice notice, MultipartFile upfile) {
		log.info("Adding new notice : {}", notice);
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
				
				notice.setAttachMent("resorces/upload_files" + changeName);
			
		}
		mapper.addNotice(notice);
	}

	@Override
	@Transactional
	public void updateNotice(Notice notice) {
		 log.info("Updating notice: {}", notice);
	     mapper.updateNotice(notice);
	}

	@Override
	@Transactional
	public void deleteNotice(int noticeNo) {
		log.info("Deleting notice with ID : {}", noticeNo);
		mapper.deleteNotice(noticeNo);
	}

	@Override
	public Notice getNoticeById(int noticeNo) {
		log.info("Fatching notice with ID : {}", noticeNo);
		return mapper.selectNoticeById(noticeNo);
	}

	@Override
	public void daleteNotice(int noticeNo) {
		
	}

}

