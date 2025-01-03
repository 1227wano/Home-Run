package com.kh.baseball.notice.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.common.ModelAndViewUtil;
import com.kh.baseball.notice.model.service.NoticeService;
import com.kh.baseball.notice.model.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

	private final NoticeService noticeService;
	private final ModelAndViewUtil mv;
	
	@GetMapping("notices")
	public ModelAndView selectNoticeList(@RequestParam(value="page", defaultValue="1") int page) {
	    int totalCount = noticeService.getTotalNoticeCount(); 
	    int pageSize = 10; 
	    int totalPage = (int) Math.ceil((double) totalCount / pageSize); 

	    Map<String, Object> params = new HashMap<>();
	    params.put("page", page);
	    params.put("rowBounds", new RowBounds((page - 1) * pageSize, pageSize));

	    List<Notice> noticeList = noticeService.selectAllNotices(params); 

	    ModelAndView mv = new ModelAndView("notice/list");
	    mv.addObject("noticeList", noticeList); 
	    mv.addObject("totalCount", totalCount); 
	    mv.addObject("totalPage", totalPage); 

	    return mv;
	}

	
	@GetMapping("insertForm")
	public String insertForm() {
		return "notice/insert_form";
	}
	
	@PostMapping("notices")
	public String insertNotice(Notice notice, MultipartFile upfile) {
		log.info("공지사항 정보 : {}", notice);
		noticeService.addNotice(notice, upfile);
		return "redirect:notices";
	}
	
	@GetMapping("notices/{id}")
	public ModelAndView selectNoticeById(@PathVariable(name="id") long id) {
		Map<String, Object> responseData = noticeService.selectNoticeById((long) id);
		return mv.setViewNameAndData("notice/detail", responseData);
	}
	
	@PostMapping("notices/delete")
	public String delteNotice(Long noticeNo, String attachMent) {
		noticeService.deleteNotice(noticeNo);
		return "redirect:notices";
	}
	
	@PostMapping("notices/update-form")
	public ModelAndView updateForm(Long noticeNo) {
		Map<String, Object> responseData = noticeService.selectNoticeById(noticeNo);
		return mv.setViewNameAndData("notice/update", responseData);
	}
	
	


	
}