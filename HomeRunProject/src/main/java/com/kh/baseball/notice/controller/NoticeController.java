package com.kh.baseball.notice.controller;

import java.util.Map;

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
		Map<String, Object> map = noticeService.selectAllNotices(page);
		return mv.setViewNameAndData("notice/list", map);
	}
	
	@GetMapping("insertForm")
	public String insertForm() {
		return "notice/insert_form";
	}
	
	@PostMapping("notices")
	public String insertNotice(Notice notice, MultipartFile upfile) {
		log.info("게시글 정보 : {}, 파일정보 : {}", notice, upfile );
		noticeService.addNotice(notice, upfile);
		return "redirect:notices";
	}
	
	@GetMapping("notices/{id}")
	public ModelAndView selectNoticeById(@PathVariable(name="id") int id) {
		Map<String, Object> responseData = noticeService.selectNoticeById(id);
		return mv.setViewNameAndData("notice/detail", responseData);
	}
	
	@PostMapping("notices/delete")
	public String delteNotice(@RequestParam int noticeNo, @RequestParam(required = true) String attachMent) {
		log.info("삭제할 공지사항 번호 : {}, 첨부파일 : {}", noticeNo, attachMent);
		noticeService.deleteNotice(noticeNo);
		return "redirect:notices";
	}
	
	
	
	
}